package wrapper.event

import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class, ExperimentalForeignApi::class)
class PlayerEvents {
	val inventoryChanged = mutableListOf<EventHandler<InventoryChangedEventArgs>>()
	val levelChanged = mutableListOf<EventHandler<LevelChangedEventArgs>>()
	val warped = mutableListOf<EventHandler<WarpedEventArgs>>()

	@CName("onPlayerEventsInventoryChanged")
	fun onInventoryChanged(ref: COpaquePointer) {
		val e = InventoryChangedEventArgs()
		inventoryChanged.forEach { it(e) }
	}

	@CName("onPlayerEventsLevelChanged")
	fun onLevelChanged(ref: COpaquePointer) {
		val e = LevelChangedEventArgs()
		levelChanged.forEach { it(e) }
	}


	@CName("onPlayerEventsWarped")
	fun onWarped(ref: COpaquePointer) {
		val e = WarpedEventArgs()
		warped.forEach { it(e) }
	}
}

class InventoryChangedEventArgs
class LevelChangedEventArgs
class WarpedEventArgs