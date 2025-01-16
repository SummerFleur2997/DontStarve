package wrapper.event

import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class, ExperimentalForeignApi::class)
class SpecializedEvents {
	val loadStageChanged = mutableListOf<EventHandler<LoadStageChangedEventArgs>>()
	val unvalidatedUpdateTicking = mutableListOf<EventHandler<UnvalidatedUpdateTickingEventArgs>>()
	val unvalidatedUpdateTicked = mutableListOf<EventHandler<UnvalidatedUpdateTickedEventArgs>>()

	@CName("onSpecializedEventsLoadStageChanged")
	fun onLoadStageChanged(ref: COpaquePointer) {
		val e = LoadStageChangedEventArgs()
		loadStageChanged.forEach { it(e) }
	}

	@CName("onSpecializedEventsUnvalidatedUpdateTicking")
	fun onUnvalidatedUpdateTicking(ref: COpaquePointer) {
		val e = UnvalidatedUpdateTickingEventArgs()
		unvalidatedUpdateTicking.forEach { it(e) }
	}

	@CName("onSpecializedEventsUnvalidatedUpdateTicked")
	fun onUnvalidatedUpdateTicked(ref: COpaquePointer) {
		val e = UnvalidatedUpdateTickedEventArgs()
		unvalidatedUpdateTicked.forEach { it(e) }
	}
}

class LoadStageChangedEventArgs
class UnvalidatedUpdateTickingEventArgs
class UnvalidatedUpdateTickedEventArgs