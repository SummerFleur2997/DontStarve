package wrapper

class Texture2D private constructor(
	val graphicsDevice: GraphicsDevice,
	val width: Int,
	val height: Int,
	val bitmap: Boolean,
	val format: SurfaceFormat,
	val type: SurfaceType,
	val shared: Boolean,
	val arraySize: Int
) {
	constructor(
		graphicsDevice: GraphicsDevice,
		width: Int,
		height: Int
	) : this(graphicsDevice, width, height, false, SurfaceFormat.Color, SurfaceType.Texture, false, 1)

	constructor(
		graphicsDevice: GraphicsDevice,
		width: Int,
		height: Int,
		bitmap: Boolean,
		format: SurfaceFormat
	) : this(graphicsDevice, width, height, bitmap, format, SurfaceType.Texture, false, 1)

	constructor(
		graphicsDevice: GraphicsDevice,
		width: Int,
		height: Int,
		bitmap: Boolean,
		format: SurfaceFormat,
		arraySize: Int
	) : this(graphicsDevice, width, height, bitmap, format, SurfaceType.Texture, false, arraySize)

	internal constructor(
		graphicsDevice: GraphicsDevice,
		width: Int,
		height: Int,
		bitmap: Boolean,
		format: SurfaceFormat,
		type: SurfaceType
	) : this(graphicsDevice, width, height, bitmap, format, type, false, 1)

	fun <T> setData(vararg data: T) {

	}
}