package wrapper

class Farmer {
	var health: Int
		get() = TODO()
		set(value) = TODO()
	var stamina: Float
		get() = TODO()
		set(value) = TODO()
	var spouse: String
		get() = TODO()
		set(value) = TODO()
	var activeObject: Object
		get() = TODO()
		set(value) = TODO()
	val maxHealth: Int
		get() = TODO()
	val maxStamina: Int
		get() = TODO()
	val buffs: BuffManager
		get() = TODO()
	val isEating: Boolean
		get() = TODO()
	val itemToEat: Item
		get() = TODO()
	val tile: Vector2
		get() = TODO()
	val hat: NetRef<Hat>
		get() = TODO()
	val boots: NetRef<Boots>
		get() = TODO()
	val leftRing: NetRef<Ring>
		get() = TODO()
	val rightRing: NetRef<Ring>
		get() = TODO()
	val shirtItem: NetRef<Clothing>
		get() = TODO()
	val pantsItem: NetRef<Clothing>
		get() = TODO()
	val trinketItems: NetList<Trinket, NetRef<Trinket>>
		get() = TODO()
	val position: Vector2
		get() = TODO()

	fun hasBuff(id: String): Boolean {
		TODO()
	}

	fun getFriendshipHeartLevelForNPC(name: String): Int {
		TODO()
	}
}