import Hunger.getHunger
import Hunger.getMaxHunger
import Sanity.getMaxSanity
import Sanity.getSanity
import wrapper.Color
import wrapper.Game1

object BarsInformation {
	private val fullSanityColor = Color(0xFF, 0xC7, 0x00)
	private val zeroSanityColor = Color(0xA9, 0xA9, 0xA9)
	private val fullHungerColor = Color(0xFF, 0xC7, 0x00)
	private val zeroHungerColor = Color(0xA9, 0xA9, 0xA9)

	val sanityColor: Color
		get() {
			val player = Game1.player
			val percent = player.getSanity() / player.getMaxSanity()
			val lerpR = fullSanityColor.r - zeroSanityColor.r
			val lerpG = fullSanityColor.g - zeroSanityColor.g
			val lerpB = fullSanityColor.b - zeroSanityColor.b
			return Color(
				zeroSanityColor.r + (percent * lerpR).toInt(),
				zeroSanityColor.r + (percent * lerpG).toInt(),
				zeroSanityColor.r + (percent * lerpB).toInt()
			)
		}

	val hungerColor: Color
		get() {
			val player = Game1.player
			val percent = player.getHunger() / player.getMaxHunger()
			val lerpR = fullHungerColor.r - zeroHungerColor.r
			val lerpG = fullHungerColor.g - zeroHungerColor.g
			val lerpB = fullHungerColor.b - zeroHungerColor.b
			return Color(
				zeroHungerColor.r + (percent * lerpR).toInt(),
				zeroHungerColor.g + (percent * lerpG).toInt(),
				zeroHungerColor.b + (percent * lerpB).toInt()
			)
		}
}