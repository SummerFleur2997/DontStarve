package wrapper

object Game1 {
	const val TILE_SIZE = 64
	const val PIXEL_ZOOM = 4
	var currentLocation: GameLocation
		get() = TODO()
		set(value) = TODO()
	val player: Farmer
		get() = TODO()
	val graphics: GraphicsDeviceManager
		get() = TODO()
	val isRaining: Boolean
		get() = TODO()
	val isGreenRain: Boolean
		get() = TODO()
	val isLightning: Boolean
		get() = TODO()
	val isSnowing: Boolean
		get() = TODO()
	val season: Season
		get() = TODO()
	val viewport: Rectangle
		get() = TODO()
	val uiViewport: Rectangle
		get() = TODO()
	val showingHealth: Boolean
		get() = TODO()
	val currentEvent: Event
		get() = TODO()
	val dialogueFont: SpriteFont
		get() = TODO()
	val spriteBatch: SpriteBatch
		get() = TODO()
	val smallFont: SpriteFont
		get() = TODO()
	val menuTexture: Texture2D
		get() = TODO()
	val textColor: Color
		get() = TODO()

	fun getMousePosition(uiScale: Boolean): Point {
		TODO()
	}
}