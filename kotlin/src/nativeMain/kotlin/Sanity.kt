import wrapper.*
import wrapper.event.TimeChangedEventArgs
import kotlin.random.Random

object Sanity {
	private const val FARMER_MAX_SANITY = 150.0
	private var farmerSanity = 0.0

	fun init(helper: ModHelper) {
		EatFood.init(helper)
		NearMonster.init(helper)
		Wearing.init(helper)

		helper.events.gameLoop.gameLaunched += {
			val timeApi = helper.modRegistry.getApi<TimeApi>("Yurin.MinuteTimeHelper")!!
			timeApi.onUpdate += { time -> update(time) }
			timeApi.onSync += { time, delta -> sync(time, delta) }
		}

		helper.events.gameLoop.saveLoaded += { load(helper) }
		helper.events.gameLoop.saving += { save(helper) }
		helper.events.gameLoop.timeChanged += { timeChange(it) }
		helper.events.gameLoop.dayEnding += { dayEnding() }
	}

	private fun update(time: Long) {
		NearMonster.update(time)
		Night.update(time)
		Wearing.update(time)
		NearNpc.update(time)
		MineShaft.update(time)
		SpawnMrSkitts.update(time)
		SpawnDarkHand.update(time)
		SpawnDarkWatcher.update(time)
		SpawnEye.update(time)
		SpawnCreeperFear.update(time)
		SpawnTerrifyingSharpBeak.update(time)
	}

	private fun sync(time: Long, delta: Long) {
		NearMonster.sync(time, delta)
		Night.sync(time, delta)
		Wearing.sync(time, delta)
		NearNpc.sync(time, delta)
		MineShaft.sync(time, delta)
		SpawnMrSkitts.sync(time, delta)
		SpawnDarkHand.sync(time, delta)
		SpawnDarkWatcher.sync(time, delta)
		SpawnEye.sync(time, delta)
		SpawnCreeperFear.sync(time, delta)
		SpawnTerrifyingSharpBeak.sync(time, delta)
	}

	private fun load(helper: ModHelper) {
		val data = helper.data.readSaveData<Data>("DontStarve.Sanity")
		farmerSanity = data?.sanity ?: FARMER_MAX_SANITY
		NearMonster.load(helper)
		Night.load(helper)
		Wearing.load(helper)
		NearNpc.load(helper)
		MineShaft.load(helper)
		SpawnMrSkitts.load(helper)
		SpawnDarkHand.load(helper)
		SpawnDarkWatcher.load(helper)
		SpawnEye.load(helper)
		SpawnCreeperFear.load(helper)
		SpawnTerrifyingSharpBeak.load(helper)
	}

	private fun save(helper: ModHelper) {
		helper.data.writeSaveData("DontStarve.Sanity", Data(farmerSanity))
		NearMonster.save(helper)
		Night.save(helper)
		Wearing.save(helper)
		NearNpc.save(helper)
		MineShaft.save(helper)
		SpawnMrSkitts.save(helper)
		SpawnDarkHand.save(helper)
		SpawnDarkWatcher.save(helper)
		SpawnEye.save(helper)
		SpawnCreeperFear.save(helper)
		SpawnTerrifyingSharpBeak.save(helper)
	}

	private fun timeChange(e: TimeChangedEventArgs) {
		Sleep.timeChange(e)
	}

	private fun dayEnding() {
		Sleep.dayEnding()
	}

	fun Farmer.getMaxSanity() = FARMER_MAX_SANITY

	fun Farmer.getSanity() = farmerSanity

	fun Farmer.setSanity(value: Double) {
		farmerSanity = when {
			value < 0 -> 0.0
			value > getMaxSanity() -> getMaxSanity()
			else -> value
		}
	}

	data class Data(val sanity: Double?)

	object EatFood {
		lateinit var foodSanity: MutableMap<String, Double>
			private set
		private var lastFood: Item? = null
		private var lastEating = false

		fun init(helper: ModHelper) {
			foodSanity = helper.modContent.load<Map<String, Double>>("assets/sanity/food.json").toMutableMap()
			helper.events.gameLoop.updateTicking += { update() }
		}

		private fun update() {
			val player = Game1.player
			val isEating = player.isEating
			if (!isEating && lastEating) {
				val sanity = foodSanity[lastFood!!.itemId] ?: 0.0
				player.setSanity(player.getSanity() + sanity)
			}

			lastFood = player.itemToEat
			lastEating = player.isEating
		}
	}

	object NearMonster {
		private lateinit var monsterSanity: MutableMap<String, Double>
		private var wait: Long = 0L

		fun init(helper: ModHelper) {
			monsterSanity = helper.modContent.load<Map<String, Double>>("assets/sanity/monster.json").toMutableMap()
		}

		fun update(time: Long) {
			if (wait > 0) {
				wait--
				return
			}

			val player = Game1.player
			val location = Game1.currentLocation
			val playerPosition = player.tile
			var value = 0.0
			for (monster in location.characters.filter { it is Monsters.Monster }) {
				val monsterPosition = monster.tile
				val distance = distance(playerPosition, monsterPosition)
				val percentage = 1 - distance / 10
				if (percentage > 0) {
					value += (monsterSanity[monster.name] ?: 0.0) * percentage
				}
			}

			if (value > 0) {
				player.setSanity(player.getSanity() - value)
			}
		}

		fun sync(time: Long, delta: Long) {
			if (delta < 0) {
				wait += -delta
			} else {
				for (i in 0..delta) {
					update(time)
				}
			}
		}

		fun load(helper: ModHelper) {
			val data = helper.data.readSaveData<Data>("DontStarve.Sanity.NearMonster")
			wait = data?.wait ?: 0
		}

		fun save(helper: ModHelper) {
			helper.data.writeSaveData("DontStarve.Sanity.NearMonster", Data(wait))
		}

		data class Data(val wait: Long)
	}

	object Wearing {
		private lateinit var hatSanity: MutableMap<String, Double>
		private lateinit var shirtSanity: MutableMap<String, Double>
		private lateinit var pantsSanity: MutableMap<String, Double>
		private lateinit var bootsSanity: MutableMap<String, Double>
		private lateinit var ringSanity: MutableMap<String, Double>
		private lateinit var trinketSanity: MutableMap<String, Double>
		private var wait = 0L

		fun init(helper: ModHelper) {
			hatSanity = helper.modContent.load<Map<String, Double>>("assets/sanity/hat.json").toMutableMap()
			bootsSanity = helper.modContent.load<Map<String, Double>>("assets/sanity/boots.json").toMutableMap()
			ringSanity = helper.modContent.load<Map<String, Double>>("assets/sanity/ring.json").toMutableMap()
			shirtSanity = helper.modContent.load<Map<String, Double>>("assets/sanity/shirt.json").toMutableMap()
			pantsSanity = helper.modContent.load<Map<String, Double>>("assets/sanity/pants.json").toMutableMap()
			trinketSanity = helper.modContent.load<Map<String, Double>>("assets/sanity/trinket.json").toMutableMap()
		}

		fun update(time: Long) {
			if (wait > 0) {
				wait--
				return
			}

			val player = Game1.player
			var sanity = 0.0

			val hat = player.hat.Value
			if (hat != null) {
				sanity += hatSanity[hat.ItemId] ?: 0.0
			}

			val shirt = player.shirtItem.Value
			if (shirt != null) {
				sanity += shirtSanity[shirt.ItemId] ?: 0.0
			}

			val pants = player.pantsItem.Value
			if (pants != null) {
				sanity += pantsSanity[pants.ItemId] ?: 0.0
			}

			val boots = player.boots.Value
			if (boots != null) {
				sanity += bootsSanity[boots.ItemId] ?: 0.0
			}

			val leftRing = player.leftRing.Value
			if (leftRing != null) {
				sanity += ringSanity[leftRing.ItemId] ?: 0.0
			}

			val rightRing = player.rightRing.Value
			if (rightRing != null) {
				sanity += ringSanity[rightRing.ItemId] ?: 0.0
			}

			val trinket = player.trinketItems.FirstOrDefault()
			if (trinket != null) {
				sanity += trinketSanity[trinket.ItemId] ?: 0.0
			}

			player.setSanity(player.getSanity() + sanity)
		}

		fun sync(time: Long, delta: Long) {
			if (delta < 0) {
				wait += -delta
			} else {
				for (i in 0..delta) {
					update(time)
				}
			}
		}

		fun load(helper: ModHelper) {
			val data = helper.data.readSaveData<Data>("DontStarve.Sanity.Wearing")
			wait = data?.wait ?: 0
		}

		fun save(helper: ModHelper) {
			helper.data.writeSaveData("DontStarve.Sanity.Wearing", Data(wait))
		}

		data class Data(val wait: Long)
	}

	object Night {
		private var wait = 0L

		fun update(time: Long) {
			if (wait > 0) {
				wait--
				return
			}

			val player = Game1.player
			val timeOfDay = time % (60 * 24)
			val lastTimeOfDay = (time - 1) % (60 * 24)
			var nightfallTime = 0L
			var midnightTime = 0L

			val nightfallStart = when (Game1.season) {
				Season.Spring -> 20
				Season.Summer -> 20
				Season.Fall -> 19
				Season.Winter -> 18
			} * 60

			val midnightEnd = 6 * 60

			// 当前时间是白天
			if (timeOfDay in midnightEnd..<nightfallStart) {
				// 上次时间是凌晨
				if (lastTimeOfDay < midnightEnd) {
					midnightTime = midnightEnd - lastTimeOfDay
				}
			}
			// 当前时间是黄昏
			else if (timeOfDay >= nightfallStart) {
				// 上次时间是白天
				if (lastTimeOfDay in midnightEnd..<nightfallStart) {
					nightfallTime = timeOfDay - nightfallStart
				}
				// 上次时间是黄昏
				else {
					nightfallTime = timeOfDay - lastTimeOfDay
				}
			}
			// 当前时间是凌晨
			else {
				// 上次时间是黄昏
				if (lastTimeOfDay >= nightfallStart) {
					nightfallTime = 60 * 24 - lastTimeOfDay
					midnightTime = timeOfDay
				}
				// 上次时间是凌晨
				else {
					midnightTime = timeOfDay - lastTimeOfDay
				}
			}

			val nightfallSanity = nightfallTime * 0.0588
			val midnightSanity = midnightTime * (if (Game1.currentLocation.IsOutdoors) 0.1176 else 0.0588)
			val value = nightfallSanity + midnightSanity
			if (value > 0) {
				player.setSanity(player.getSanity() - value)
			}
		}

		fun sync(time: Long, delta: Long) {
			if (delta < 0) {
				wait += -delta
			} else {
				for (i in 0..delta) {
					update(time)
				}
			}
		}

		fun load(helper: ModHelper) {
			val data = helper.data.readSaveData<Data>("DontStarve.Sanity.Night")
			wait = data?.wait ?: 0
		}

		fun save(helper: ModHelper) {
			helper.data.writeSaveData("DontStarve.Sanity.Night", Data(wait))
		}

		data class Data(val wait: Long)
	}

	object NearNpc {
		private var wait = 0L

		fun update(time: Long) {
			if (wait > 0) {
				wait--
				return
			}

			val player = Game1.player
			val location = Game1.currentLocation
			val playerPosition = player.position
			var value = 0.0
			for (villager in location.characters.filter { it.IsVillager }) {
				val villagerPosition = villager.position
				val distance = distance(playerPosition, villagerPosition)
				val percentage = 1 - distance / 10
				if (percentage > 0) {
					if (villager.Name == player.spouse) {
						value += 1.176 * percentage
					} else {
						val level = player.getFriendshipHeartLevelForNPC(villager.Name)
						if (level >= 8) {
							value += 0.588 * percentage
						} else if (level >= 5) {
							value += 0.294 * percentage
						}
					}
				}
			}

			for (npc in location.characters.filter { it is Child || it is Pet }) {
				val villagerPosition = npc.position
				val distance = distance(playerPosition, villagerPosition)
				val percentage = 1 - distance / 10
				if (percentage > 0) {
					val level = player.getFriendshipHeartLevelForNPC(npc.Name)
					if (level == 5) {
						value += 0.588 * percentage
					} else if (level >= 3) {
						value += 0.294 * percentage
					} else {
						value += 0.147 * percentage
					}
				}
			}

			for (npc in location.characters.filter { it is Junimo || it is JunimoHarvester }) {
				val villagerPosition = npc.position
				val distance = distance(playerPosition, villagerPosition)
				val percentage = 1 - distance / 10
				if (percentage > 0) {
					value += 0.294
				}
			}

			if (value > 0) {
				player.setSanity(player.getSanity() + value)
			}
		}

		fun sync(time: Long, delta: Long) {
			if (delta < 0) {
				wait += -delta
			} else {
				for (i in 0..delta) {
					update(time)
				}
			}
		}

		fun load(helper: ModHelper) {
			val data = helper.data.readSaveData<Data>("DontStarve.Sanity.NearNpc")
			wait = data?.wait ?: 0
		}

		fun save(helper: ModHelper) {
			helper.data.writeSaveData("DontStarve.Sanity.NearNpc", Data(wait))
		}

		data class Data(val wait: Long)
	}

	object MineShaft {
		private var wait = 0L

		fun update(time: Long) {
			if (wait > 0) {
				wait--
				return
			}

			val player = Game1.player
			val location = Game1.currentLocation
			var value = 0.0
			if (location is Locations.MineShaft) {
				// 矿井
				value = 0.0588
				// 黑暗层
				if (location.isDarkArea()) {
					value = 0.588
				}
				// 骷髅矿井
				if (location.mineLevel > 120) {
					value = 0.1176
				}
				// 骷髅矿井 880 层以上
				if (location.mineLevel > 1000) {
					value = 0.2352
				}
				// 采石场矿井
				if (location.isQuarryArea) {
					value = 0.1764
				}
				// 感染层
				if (location.isSlimeArea) {
					value += 0.1176
				}
				// 地牢层
				if (location.isMonsterArea) {
					value += 0.2352
				}
				// 史前层
				if (location.isDinoArea) {
					value += 0.2352
				}
				// 危险矿井
				if (location.GetAdditionalDifficulty() > 0) {
					// 骷髅矿井
					if (location.mineLevel > 120) {
						value += 0.2352
					} else {
						value += 0.1176
					}
				}
			}
			// 火山矿井
			else if (location is VolcanoDungeon) {
				value = 0.1176
			}

			if (value > 0) {
				player.setSanity(player.getSanity() - value)
			}
		}

		fun sync(time: Long, delta: Long) {
			if (delta < 0) {
				wait += -delta
			} else {
				for (i in 0..delta) {
					update(time)
				}
			}
		}

		fun load(helper: ModHelper) {
			val data = helper.data.readSaveData<Data>("DontStarve.Sanity.MineShaft")
			wait = data?.wait ?: 0
		}

		fun save(helper: ModHelper) {
			helper.data.writeSaveData("DontStarve.Sanity.MineShaft", Data(wait))
		}

		data class Data(val wait: Long)
	}

	object SpawnMrSkitts {
		private var lastSanity = 0.0
		private var lastTime = 0L
		private var wait = 0L
		private val random = Random.Default

		fun update(time: Long) {
			if (wait > 0) {
				wait--
				return
			}

			val player = Game1.player
			val location = Game1.currentLocation

			if (player.getSanity() <= player.getMaxSanity() * 0.835) {
				val delta = time - lastTime
				if (delta >= 20 || lastSanity > player.getMaxSanity() * 0.835) {
					val playerPosition = player.position
					val xStart = player.position.x - 10 * Game1.TILE_SIZE
					val xEnd = player.position.x + 10 * Game1.TILE_SIZE
					val yStart = player.position.y - 10 * Game1.TILE_SIZE
					val yEnd = player.position.y + 10 * Game1.TILE_SIZE
					var spawnPosition: Vector2
					do {
						spawnPosition = Vector2(
							xStart + random.nextFloat() * (xEnd - xStart),
							yStart + random.nextFloat() * (yEnd - yStart)
						)

					} while (distance(playerPosition, spawnPosition) !in (5.0 * Game1.TILE_SIZE)..(10.0 * Game1.TILE_SIZE))

					location.critters?.Add(MrSkitts(spawnPosition))
					lastTime = time
				}
			}

			lastSanity = player.getSanity()
		}

		fun sync(time: Long, delta: Long) {
			if (delta < 0) {
				wait += -delta
			} else {
				for (i in 0..delta) {
					update(time)
				}
			}
		}

		fun load(helper: ModHelper) {
			val data = helper.data.readSaveData<Data>("DontStarve.Sanity.SpawnMrSkitts")
			lastSanity = data?.lastSanity ?: 0.0
			lastTime = data?.lastTime ?: 0
			wait = data?.wait ?: 0
		}

		fun save(helper: ModHelper) {
			helper.data.writeSaveData(
				"DontStarve.Sanity.SpawnMrSkitts", Data(
					lastSanity = lastSanity,
					lastTime = lastTime,
					wait = wait
				)
			)
		}

		data class Data(
			val lastSanity: Double,
			val lastTime: Long,
			val wait: Long
		)
	}

	object SpawnDarkHand {
		private var lastSanity = 0.0
		private var lastTime = 0L
		private var wait = 0L
		private val random = Random.Default

		fun update(time: Long) {
			if (wait > 0) {
				wait--
				return
			}

			val player = Game1.player
			val location = Game1.currentLocation

			if (player.getSanity() <= player.getMaxSanity() * 0.75) {
				val delta = time - lastTime
				if (delta >= 20 || lastSanity > player.getMaxSanity() * 0.75) {
					val playerPosition = player.position
					val xStart = player.position.x - 20 * Game1.TILE_SIZE
					val xEnd = player.position.x + 20 * Game1.TILE_SIZE
					val yStart = player.position.y - 20 * Game1.TILE_SIZE
					val yEnd = player.position.y + 20 * Game1.TILE_SIZE
					var spawnPosition: Vector2
					do {
						spawnPosition = Vector2(
							xStart + random.nextFloat() * (xEnd - xStart),
							yStart + random.nextFloat() * (yEnd - yStart)
						)
					} while (distance(playerPosition, spawnPosition) !in (15.0 * Game1.TILE_SIZE)..(20.0 * Game1.TILE_SIZE))

					location.critters?.Add(DarkHand(spawnPosition))
					lastTime = time
				}
			}

			lastSanity = player.getSanity()
		}

		fun sync(time: Long, delta: Long) {
			if (delta < 0) {
				wait += -delta
			} else {
				for (i in 0..delta) {
					update(time)
				}
			}
		}

		fun load(helper: ModHelper) {
			val data = helper.data.readSaveData<Data>("DontStarve.Sanity.SpawnDarkHand")
			lastSanity = data?.lastSanity ?: 0.0
			lastTime = data?.lastTime ?: 0
			wait = data?.wait ?: 0
		}

		fun save(helper: ModHelper) {
			helper.data.writeSaveData(
				"DontStarve.Sanity.SpawnDarkHand", Data(
					lastSanity = lastSanity,
					lastTime = lastTime,
					wait = wait
				)
			)
		}

		data class Data(
			val lastSanity: Double,
			val lastTime: Long,
			val wait: Long
		)
	}

	object SpawnDarkWatcher {
		private var lastSanity = 0.0
		private var lastTime = 0L
		private var wait = 0L
		private val random = Random.Default

		fun update(time: Long) {
			if (wait > 0) {
				wait--
				return
			}

			val player = Game1.player
			val location = Game1.currentLocation

			if (player.getSanity() <= player.getMaxSanity() * 0.65) {
				val delta = time - lastTime
				if (delta >= 20 || lastSanity > player.getMaxSanity() * 0.65) {
					val xStart = Game1.viewport.X
					val xEnd = Game1.viewport.Width + Game1.viewport.X
					val yStart = Game1.viewport.Y
					val yEnd = Game1.viewport.Height + Game1.viewport.Y
					val spawnPosition = Vector2(
						xStart + random.nextFloat() * (xEnd - xStart),
						yStart + random.nextFloat() * (yEnd - yStart)
					)
					if (spawnPosition.x > spawnPosition.y) {
						spawnPosition.y = yStart
					} else if (spawnPosition.x < spawnPosition.y) {
						spawnPosition.x = xStart
					}

					location.critters?.Add(DarkWatcher(spawnPosition))
					lastTime = time
				}
			}

			lastSanity = player.getSanity()
		}

		fun sync(time: Long, delta: Long) {
			if (delta < 0) {
				wait += -delta
			} else {
				for (i in 0..delta) {
					update(time)
				}
			}
		}

		fun load(helper: ModHelper) {
			val data = helper.data.readSaveData<Data>("DontStarve.Sanity.SpawnDarkWatcher")
			lastSanity = data?.lastSanity ?: 0.0
			lastTime = data?.lastTime ?: 0
			wait = data?.wait ?: 0
		}

		fun save(helper: ModHelper) {
			helper.data.writeSaveData(
				"DontStarve.Sanity.SpawnDarkWatcher", Data(
					lastSanity = lastSanity,
					lastTime = lastTime,
					wait = wait
				)
			)
		}

		data class Data(
			val lastSanity: Double,
			val lastTime: Long,
			val wait: Long
		)
	}

	object SpawnEye {
		private var lastSanity = 0.0
		private var lastTime = 0L
		private var wait = 0L
		private val random = Random.Default

		fun update(time: Long) {
			if (wait > 0) {
				wait--
				return
			}

			val player = Game1.player
			val location = Game1.currentLocation

			if (player.getSanity() <= player.getMaxSanity() * 0.6) {
				val delta = time - lastTime
				if (delta >= 20 || lastSanity > player.getMaxSanity() * 0.6) {
					val playerPosition = player.position
					val xStart = player.position.x - 15 * Game1.TILE_SIZE
					val xEnd = player.position.x + 15 * Game1.TILE_SIZE
					val yStart = player.position.y - 15 * Game1.TILE_SIZE
					val yEnd = player.position.y + 15 * Game1.TILE_SIZE
					var spawnPosition: Vector2
					do {
						spawnPosition = Vector2(
							xStart + random.nextFloat() * (xEnd - xStart),
							yStart + random.nextFloat() * (yEnd - yStart)
						)
					} while (distance(playerPosition, spawnPosition) !in (5.0 * Game1.TILE_SIZE)..(15.0 * Game1.TILE_SIZE))

					location.critters?.Add(Eye(spawnPosition))
					lastTime = time
				}
			}

			lastSanity = player.getSanity()
		}

		fun sync(time: Long, delta: Long) {
			if (delta < 0) {
				wait += -delta
			} else {
				for (i in 0..delta) {
					update(time)
				}
			}
		}

		fun load(helper: ModHelper) {
			val data = helper.data.readSaveData<Data>("DontStarve.Sanity.SpawnEye")
			lastSanity = data?.lastSanity ?: 0.0
			lastTime = data?.lastTime ?: 0
			wait = data?.wait ?: 0
		}

		fun save(helper: ModHelper) {
			helper.data.writeSaveData(
				"DontStarve.Sanity.SpawnEye", Data(
					lastSanity = lastSanity,
					lastTime = lastTime,
					wait = wait
				)
			)
		}

		data class Data(
			val lastSanity: Double,
			val lastTime: Long,
			val wait: Long
		)
	}

	object SpawnCreeperFear {
		private var lastSanity = 0.0
		private var lastTime = 0L
		private var wait = 0L
		private val random = Random.Default

		fun update(time: Long) {
			if (wait > 0) {
				wait--
				return
			}

			val player = Game1.player
			val location = Game1.currentLocation

			if (player.getSanity() <= player.getMaxSanity() * 0.5) {
				val delta = time - lastTime
				if (delta >= 20 || lastSanity > player.getMaxSanity() * 0.5) {
					val playerPosition = player.position
					val xStart = player.position.x - 15 * Game1.TILE_SIZE
					val xEnd = player.position.x + 15 * Game1.TILE_SIZE
					val yStart = player.position.y - 15 * Game1.TILE_SIZE
					val yEnd = player.position.y + 15 * Game1.TILE_SIZE
					var spawnPosition: Vector2
					do {
						spawnPosition = Vector2(
							xStart + random.nextFloat() * (xEnd - xStart),
							yStart + random.nextFloat() * (yEnd - yStart)
						)
					} while (distance(playerPosition, spawnPosition) !in (5.0 * Game1.TILE_SIZE)..(15.0 * Game1.TILE_SIZE))

					location.critters?.Add(CreeperFear(spawnPosition))
					lastTime = time
				}
			}

			lastSanity = player.getSanity()
		}

		fun sync(time: Long, delta: Long) {
			if (delta < 0) {
				wait += -delta
			} else {
				for (i in 0..delta) {
					update(time)
				}
			}
		}

		fun load(helper: ModHelper) {
			val data = helper.data.readSaveData<Data>("DontStarve.Sanity.SpawnCreeperFear")
			lastSanity = data?.lastSanity ?: 0.0
			lastTime = data?.lastTime ?: 0
			wait = data?.wait ?: 0
		}

		fun save(helper: ModHelper) {
			helper.data.writeSaveData(
				"DontStarve.Sanity.SpawnCreeperFear", Data(
					lastSanity = lastSanity,
					lastTime = lastTime,
					wait = wait
				)
			)
		}

		data class Data(
			val lastSanity: Double,
			val lastTime: Long,
			val wait: Long
		)
	}

	object SpawnTerrifyingSharpBeak {
		private var lastSanity = 0.0
		private var lastTime = 0L
		private var wait = 0L
		private val random = Random.Default

		fun update(time: Long) {
			if (wait > 0) {
				wait--
				return
			}

			val player = Game1.player
			val location = Game1.currentLocation

			if (player.getSanity() <= player.getMaxSanity() * 0.5) {
				val delta = time - lastTime
				if (delta >= 20 || lastSanity > player.getMaxSanity() * 0.5) {
					val playerPosition = player.position
					val xStart = player.position.x - 15 * Game1.TILE_SIZE
					val xEnd = player.position.x + 15 * Game1.TILE_SIZE
					val yStart = player.position.y - 15 * Game1.TILE_SIZE
					val yEnd = player.position.y + 15 * Game1.TILE_SIZE
					var spawnPosition: Vector2
					do {
						spawnPosition = Vector2(
							xStart + random.nextFloat() * (xEnd - xStart),
							yStart + random.nextFloat() * (yEnd - yStart)
						)
					} while (distance(playerPosition, spawnPosition) !in (5.0 * Game1.TILE_SIZE)..(15.0 * Game1.TILE_SIZE))

					location.critters?.Add(TerrifyingSharpBeak(spawnPosition))
					lastTime = time
				}
			}

			lastSanity = player.getSanity()
		}

		fun sync(time: Long, delta: Long) {
			if (delta < 0) {
				wait += -delta
			} else {
				for (i in 0..delta) {
					update(time)
				}
			}
		}

		fun load(helper: ModHelper) {
			val data = helper.data.readSaveData<Data>("DontStarve.Sanity.SpawnTerrifyingSharpBeak")
			lastSanity = data?.lastSanity ?: 0.0
			lastTime = data?.lastTime ?: 0
			wait = data?.wait ?: 0
		}

		fun save(helper: ModHelper) {
			helper.data.writeSaveData(
				"DontStarve.Sanity.SpawnTerrifyingSharpBeak", Data(
					lastSanity = lastSanity,
					lastTime = lastTime,
					wait = wait
				)
			)
		}

		data class Data(
			val lastSanity: Double,
			val lastTime: Long,
			val wait: Long
		)
	}

	object Sleep {
		private var lastTime = 0

		fun timeChange(e: TimeChangedEventArgs) {
			lastTime = e.newTime
		}

		fun dayEnding() {
			println("Day Ending: $lastTime")
			val player = Game1.player
			val timescale = lastTime % 100 / 10 + lastTime / 100 * 6
			if (timescale == 156) {
				player.setSanity(player.getSanity() - 20)
			} else {
				val passTimescale = 156 - timescale
				player.setSanity(player.getSanity() + passTimescale * 3)
			}
		}
	}
}