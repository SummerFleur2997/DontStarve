package wrapper.event

typealias EventHandler<T> = (e: T) -> Unit

class ModEvents {
	val content = ContentEvents()
	val display = DisplayEvents()
	val gameLoop = GameLoopEvents()
	val input = InputEvents()
	val multiplayer = MultiplayerEvents()
	val player = PlayerEvents()
	val world = WorldEvents()
	val specialized = SpecializedEvents()
}