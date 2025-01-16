import wrapper.Game1
import wrapper.ModContentHelper
import wrapper.Texture2D

object Textures {
	lateinit var sanityContainer: Texture2D
	lateinit var hungerContainer: Texture2D
	private lateinit var backingSanityFiller: Texture2D
	private lateinit var backingHungerFiller: Texture2D

	val sanityFiller: Texture2D
		get() {
			val color = BarsInformation.sanityColor
			backingSanityFiller.setData(color)
			return backingSanityFiller
		}

	val hungerFiller: Texture2D
		get() {
			val color = BarsInformation.hungerColor
			backingSanityFiller.setData(color)
			return backingSanityFiller
		}

	fun loadTextures(modContent: ModContentHelper) {
		sanityContainer = modContent.load("assets/sanity/bar.png")
		backingSanityFiller = Texture2D(Game1.graphics.GraphicsDevice, 1, 1)
		hungerContainer = modContent.load("assets/hunger/bar.png")
		backingHungerFiller = Texture2D(Game1.graphics.GraphicsDevice, 1, 1)
	}
}