package wrapper

data class Color(
	val r: Int,
	val g: Int,
	val b: Int
) {
	companion object {
		val WHITE = Color(255, 255, 255)
	}
}