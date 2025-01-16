import wrapper.Game1
import wrapper.Vector2

class MrSkitts(position: Vector2) : BellsAndWhistles.Critter() {
	init {
		this.position = position
		startingPosition = position
		sprite = AnimatedSprite(critterTexture, baseFrame, 32, 32) {
			loop = true
		}
	}

	override fun update(time: GameTime, environment: GameLocation): Boolean {
		for (farmer in environment.farmers) {
			if (distance(farmer.Position / Game1.TILE_SIZE, position / Game1.TILE_SIZE) <= 1) {
				return true
			}
		}

		return false
	}
}

class DarkHand(position: Vector2) : BellsAndWhistles.Critter() {
	init {
		this.position = position
		startingPosition = position
		sprite = AnimatedSprite(critterTexture, baseFrame, 32, 32) {
			loop = true
		}
	}

	override fun update(time: GameTime, environment: GameLocation): Boolean {
		for (farmer in environment.farmers) {
			if (distance(farmer.Position / Game1.TILE_SIZE, position / Game1.TILE_SIZE) <= 1) {
				return true
			}
		}

		return false
	}
}

class DarkWatcher(position: Vector2) : BellsAndWhistles.Critter() {
	init {
		this.position = position
		startingPosition = position
		sprite = AnimatedSprite(critterTexture, baseFrame, 32, 32) {
			loop = true
		}
	}

	override fun update(time: GameTime, environment: GameLocation): Boolean {
		for (farmer in environment.farmers) {
			if (distance(farmer.Position / Game1.TILE_SIZE, position / Game1.TILE_SIZE) <= 1) {
				return true
			}
		}

		return false
	}
}

class Eye(position: Vector2) : BellsAndWhistles.Critter() {
	init {
		this.position = position
		startingPosition = position
		sprite = AnimatedSprite(critterTexture, baseFrame, 32, 32) {
			loop = true
		}
	}

	override fun update(time: GameTime, environment: GameLocation): Boolean {
		for (farmer in environment.farmers) {
			if (distance(farmer.Position / Game1.TILE_SIZE, position / Game1.TILE_SIZE) <= 1) {
				return true
			}
		}

		return false
	}
}

class CreeperFear(position: Vector2) : BellsAndWhistles.Critter() {
	init {
		this.position = position
		startingPosition = position
		sprite = AnimatedSprite(critterTexture, baseFrame, 32, 32) {
			loop = true
		}
	}

	override fun update(time: GameTime, environment: GameLocation): Boolean {
		for (farmer in environment.farmers) {
			if (distance(farmer.Position / Game1.TILE_SIZE, position / Game1.TILE_SIZE) <= 1) {
				return true
			}
		}

		return false
	}
}

class TerrifyingSharpBeak(position: Vector2) : BellsAndWhistles.Critter() {
	init {
		this.position = position
		startingPosition = position
		sprite = AnimatedSprite(critterTexture, baseFrame, 32, 32) {
			loop = true
		}
	}

	override fun update(time: GameTime, environment: GameLocation): Boolean {
		for (farmer in environment.farmers) {
			if (distance(farmer.Position / Game1.TILE_SIZE, position / Game1.TILE_SIZE) <= 1) {
				return true
			}
		}

		return false
	}
}