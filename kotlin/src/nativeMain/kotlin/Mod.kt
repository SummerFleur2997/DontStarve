import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi
import wrapper.ModHelper
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class, ExperimentalForeignApi::class)
@CName("init")
fun init(ptr: COpaquePointer) {
	val helper = ModHelper(ptr)
	Textures.loadTextures(helper.modContent)
	Buff.init(helper)
	Sanity.init(helper)
	Hunger.init(helper)

	helper.events.display.renderingHud += { Hud.onRenderingHud(helper, it) }
}