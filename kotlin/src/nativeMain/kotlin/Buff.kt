import Sanity.getSanity
import Sanity.setSanity
import wrapper.Game1
import wrapper.ModHelper
import kotlin.math.min

object Buff {
	fun init(helper: ModHelper) {
		Electric.init(helper)

		helper.events.gameLoop.gameLaunched += {
			val timeApi = helper.modRegistry.getApi<TimeApi>("Yurin.MinuteTimeHelper")!!
			timeApi.onUpdate += { time -> update(time)}
			timeApi.onSync += { time, delta -> sync(time, delta) }
		}

		helper.events.gameLoop.saveLoaded += { load(helper) }
		helper.events.gameLoop.saving += { save(helper) }
	}

	private fun update(time: Long) {
		Health.update(time)
		Stamina.update(time)
		Sanity.update(time)
	}

	private fun sync(time: Long, delta: Long) {
		Health.sync(time, delta)
		Stamina.sync(time, delta)
		Sanity.sync(time, delta)
	}

	private fun load(helper: ModHelper) {
		Health.load(helper)
		Stamina.load(helper)
		Sanity.load(helper)
	}

	private fun save(helper: ModHelper) {
		Health.save(helper)
		Stamina.save(helper)
		Sanity.save(helper)
	}

	object Electric {
		private const val BUFF = "DS_Electric"
		private var lastHasBuff = false
		private var lastHasWeatherAddition = false

		fun init(helper: ModHelper) {
			helper.events.gameLoop.updateTicking += { update() }
		}

		private fun update() {
			val player = Game1.player
			val hasBuff = player.hasBuff(BUFF)
			if (hasBuff != lastHasBuff) {
				if (hasBuff) {
					player.buffs.GetValues().AttackMultiplier.Set(player.buffs.AttackMultiplier + 0.5F)
				} else {
					player.buffs.GetValues().AttackMultiplier.Set(player.buffs.AttackMultiplier - 0.5F)
					if (lastHasWeatherAddition) {
						player.buffs.GetValues().AttackMultiplier.Set(player.buffs.AttackMultiplier - 1F)
						lastHasWeatherAddition = false
					}
				}
			}

			if (hasBuff) {
				val hasWeatherBuff = Game1.isRaining || Game1.isGreenRain || Game1.isLightning || Game1.isSnowing
				if (hasWeatherBuff != lastHasWeatherAddition) {
					if (hasWeatherBuff) {
						player.buffs.GetValues().AttackMultiplier.Set(player.buffs.AttackMultiplier + 1F)
					} else {
						player.buffs.GetValues().AttackMultiplier.Set(player.buffs.AttackMultiplier - 1F)
					}
				}

				lastHasWeatherAddition = hasWeatherBuff
			}

			lastHasBuff = hasBuff
		}
	}

	object Health {
		private const val BUFF = "DS_Heal_Health"
		private var lastHasBuff = false
		private var lastTime = 0L
		private var wait = 0L

		fun update(time: Long) {
			if (wait > 0) {
				wait--
				return
			}

			val player = Game1.player
			val hasBuff = player.hasBuff(BUFF)

			if (hasBuff && !lastHasBuff) {
				lastTime = time
				wait = 0
			}

			if (lastHasBuff) {
				val delta = time - lastTime
				if (delta >= 3) {
					if (player.health < player.maxHealth) {
						player.health += min(2, player.maxHealth - player.health)
					}

					lastTime = time
				}
			}

			lastHasBuff = hasBuff
		}

		fun sync(time: Long, delta: Long) {
			if (delta < 0) {
				wait += -delta
			} else {
				for (i in 0 .. delta) {
					update(time)
				}
			}
		}

		fun load(helper: ModHelper) {
			val data = helper.data.readSaveData<Data>("DontStarve.Buff.Health")
			lastHasBuff = data?.lastHasBuff ?: false
			lastTime = data?.lastTime ?: 0
			wait = data?.wait ?: 0
		}

		fun save(helper: ModHelper) {
			helper.data.writeSaveData("DontStarve.Buff.Health", Data(
				lastHasBuff = lastHasBuff,
				lastTime = lastTime,
				wait = wait
			))
		}

		data class Data(
			val lastHasBuff: Boolean,
			val lastTime: Long,
			val wait: Long
		)
	}

	object Sanity {
		private const val BUFF = "DS_Heal_Sanity"
		private var lastHasBuff = false
		private var lastTime = 0L
		private var wait = 0L

		fun update(time: Long) {
			if (wait > 0) {
				wait--
				return
			}

			val player = Game1.player
			val hasBuff = player.hasBuff(BUFF)

			if (hasBuff && !lastHasBuff) {
				lastTime = time
				wait = 0
			}

			if (lastHasBuff) {
				val delta = time - lastTime
				if (delta >= 3) {
					player.setSanity(player.getSanity() + 1)
					lastTime = time
				}
			}

			lastHasBuff = hasBuff
		}

		fun sync(time: Long, delta: Long) {
			if (delta < 0) {
				wait += -delta
			} else {
				for (i in 0 .. delta) {
					update(time)
				}
			}
		}

		fun load(helper: ModHelper) {
			val data = helper.data.readSaveData<Data>("DontStarve.Buff.Stamina")
			lastHasBuff = data?.lastHasBuff ?: false
			lastTime = data?.lastTime ?: 0
			wait = data?.wait ?: 0
		}

		fun save(helper: ModHelper) {
			helper.data.writeSaveData("DontStarve.Buff.Stamina", Data(
				lastHasBuff = lastHasBuff,
				lastTime = lastTime,
				wait = wait
			))
		}

		data class Data(
			val lastHasBuff: Boolean,
			val lastTime: Long,
			val wait: Long
		)
	}

	object Stamina {
		private const val BUFF: String = "DS_Heal_Stamina"
		private var lastHasBuff = false
		private var lastTime = 0L
		private var wait = 0L

		fun update(time: Long) {
			if (wait > 0) {
				wait--
				return
			}

			val player = Game1.player
			val hasBuff = player.hasBuff(BUFF)

			if (hasBuff && !lastHasBuff) {
				lastTime = time
				wait = 0
			}

			if (lastHasBuff) {
				val delta = time - lastTime
				if (delta >= 3) {
					if (player.stamina < player.maxStamina) {
						player.stamina += min(1F, player.maxStamina - player.stamina)
					}

					lastTime = time
				}
			}

			lastHasBuff = hasBuff
		}

		fun sync(time: Long, delta: Long) {
			if (delta < 0) {
				wait += -delta
			} else {
				for (i in 0 .. delta) {
					update(time)
				}
			}
		}

		fun load(helper: ModHelper) {
			val data = helper.data.readSaveData<Data>("DontStarve.Buff.Stamina")
			lastHasBuff = data?.lastHasBuff ?: false
			lastTime = data?.lastTime ?: 0
			wait = data?.wait ?: 0
		}

		fun save(helper: ModHelper) {
			helper.data.writeSaveData("DontStarve.Buff.Stamina", Data(
				lastHasBuff = lastHasBuff,
				lastTime = lastTime,
				wait = wait
			))
		}

		data class Data(
			val lastHasBuff: Boolean,
			val lastTime: Long,
			val wait: Long
		)
	}
}