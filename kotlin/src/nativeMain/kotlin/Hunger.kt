import wrapper.Farmer
import wrapper.Game1
import wrapper.Item
import wrapper.ModHelper
import kotlin.math.min

object Hunger {
	private const val FARMER_MAX_HUNGER = 150.0
	private var farmerHunger = 0.0

	fun init(helper: ModHelper) {
		EatFood.init(helper)

		helper.events.gameLoop.gameLaunched += {
			val timeApi = helper.modRegistry.getApi<TimeApi>("Yurin.MinuteTimeHelper")!!
			timeApi.onUpdate += { time -> update(time) }
			timeApi.onSync += { time, delta -> sync(time, delta) }
		}

		helper.events.gameLoop.saveLoaded += { load(helper) }
		helper.events.gameLoop.saving += { save(helper) }
	}

	private fun update(time: Long) {
		TimeCycle.update(time)
	}

	private fun sync(time: Long, delta: Long) {
		TimeCycle.sync(time, delta)
	}

	private fun load(helper: ModHelper) {
		val data = helper.data.readSaveData<Data>("DontStarve.Hunger")
		farmerHunger = data?.hunger ?: FARMER_MAX_HUNGER
		TimeCycle.load(helper)
	}

	private fun save( helper: ModHelper) {
		helper.data.writeSaveData("DontStarve.Hunger", Data(farmerHunger))
		TimeCycle.save(helper)
	}

	fun Farmer.getMaxHunger() = FARMER_MAX_HUNGER

	fun Farmer.getHunger() = farmerHunger

	fun Farmer.setHunger(value: Double) {
		farmerHunger = when {
			value < 0 -> 0.0
			value > getMaxHunger() -> getMaxHunger()
			else -> value
		}
	}

	data class Data(val hunger: Double?)

	object EatFood {
		lateinit var foodHunger: MutableMap<String, Double>
			private set
		private var lastFood: Item? = null
		private var lastEating = false

		fun init(helper: ModHelper) {
			foodHunger = helper.modContent.load<Map<String, Double>>("assets/hunger/food.json").toMutableMap()
			helper.events.gameLoop.updateTicking += { update() }
		}

		private fun update() {
			val player = Game1.player
			val isEating = player.isEating
			if (!isEating && lastEating) {
				val sanity = foodHunger[lastFood!!.itemId] ?: 0.0
				player.setHunger(player.getHunger() + sanity)
			}

			lastFood = player.itemToEat
			lastEating = player.isEating
		}
	}

	object TimeCycle {
		private var lastHasHunger = false
		private var lastTime = 0L
		private var wait = 0L

		fun update(time: Long) {
			if (wait > 0) {
				wait--
				return
			}

			val player = Game1.player

			if (player.getHunger() > 0) {
				player.setHunger(player.getHunger() - 0.052)
				lastHasHunger = true
			} else {
				if (lastHasHunger) {
					if (player.health < player.maxHealth) {
						player.health -= min(4, player.health)
					}
				}

				val delta = time - lastTime
				if (delta >= 3) {
					if (player.health < player.maxHealth) {
						player.health -= min(4, player.health)
					}

					lastTime = time
				}

				lastHasHunger = false
			}
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
			val data = helper.data.readSaveData<Data>("DontStarve.Sanity.Night")
			lastHasHunger = data?.lastHasHunger ?: false
			lastTime = data?.lastTime ?: 0
			wait = data?.wait ?: 0
		}

		fun save(helper: ModHelper) {
			helper.data.writeSaveData("DontStarve.Sanity.Night", Data(
				lastHasHunger = lastHasHunger,
				lastTime = lastTime,
				wait = wait
			))
		}

		data class Data(
			val lastHasHunger: Boolean,
			val lastTime: Long,
			val wait: Long
		)
	}
}