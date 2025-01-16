package wrapper.event

import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class, ExperimentalForeignApi::class)
class GameLoopEvents {
	val gameLaunched = mutableListOf<EventHandler<GameLaunchedEventArgs>>()
	val updateTicking = mutableListOf<EventHandler<UpdateTickingEventArgs>>()
	val updateTicked = mutableListOf<EventHandler<UpdateTickedEventArgs>>()
	val oneSecondUpdateTicking = mutableListOf<EventHandler<OneSecondUpdateTickingEventArgs>>()
	val oneSecondUpdateTicked = mutableListOf<EventHandler<OneSecondUpdateTickedEventArgs>>()
	val saveCreating = mutableListOf<EventHandler<SaveCreatingEventArgs>>()
	val saveCreated = mutableListOf<EventHandler<SaveCreatedEventArgs>>()
	val saving = mutableListOf<EventHandler<SavingEventArgs>>()
	val saved = mutableListOf<EventHandler<SavedEventArgs>>()
	val saveLoaded = mutableListOf<EventHandler<SaveLoadedEventArgs>>()
	val dayStarted = mutableListOf<EventHandler<DayStartedEventArgs>>()
	val dayEnding = mutableListOf<EventHandler<DayEndingEventArgs>>()
	val timeChanged = mutableListOf<EventHandler<TimeChangedEventArgs>>()
	val returnedToTitle = mutableListOf<EventHandler<ReturnedToTitleEventArgs>>()

	@CName("onGameLoopEventsGameLaunched")
	fun onGameLaunched(ref: COpaquePointer) {
		val e = GameLaunchedEventArgs()
		gameLaunched.forEach { it(e) }
	}

	@CName("onGameLoopEventsUpdateTicking")
	fun onUpdateTicking(ref: COpaquePointer) {
		val e = UpdateTickingEventArgs()
		updateTicking.forEach { it(e) }
	}

	@CName("onGameLoopEventsUpdateTicked")
	fun onUpdateTicked(ref: COpaquePointer) {
		val e = UpdateTickedEventArgs()
		updateTicked.forEach { it(e) }
	}

	@CName("onGameLoopEventsOneSecondUpdateTicking")
	fun onOneSecondUpdateTicking(ref: COpaquePointer) {
		val e = OneSecondUpdateTickingEventArgs()
		oneSecondUpdateTicking.forEach { it(e) }
	}

	@CName("onGameLoopEventsOneSecondUpdateTicked")
	fun onOneSecondUpdateTicked(ref: COpaquePointer) {
		val e = OneSecondUpdateTickedEventArgs()
		oneSecondUpdateTicked.forEach { it(e) }
	}

	@CName("onGameLoopEventsSaveCreating")
	fun onSaveCreating(ref: COpaquePointer) {
		val e = SaveCreatingEventArgs()
		saveCreating.forEach { it(e) }
	}

	@CName("onGameLoopEventsSaveCreated")
	fun onSaveCreated(ref: COpaquePointer) {
		val e = SaveCreatedEventArgs()
		saveCreated.forEach { it(e) }
	}

	@CName("onGameLoopEventsSaving")
	fun onSaving(ref: COpaquePointer) {
		val e = SavingEventArgs()
		saving.forEach { it(e) }
	}

	@CName("onGameLoopEventsSaved")
	fun onSaved(ref: COpaquePointer) {
		val e = SavedEventArgs()
		saved.forEach { it(e) }
	}

	@CName("onGameLoopEventsSaveLoaded")
	fun onSaveLoaded(ref: COpaquePointer) {
		val e = SaveLoadedEventArgs()
		saveLoaded.forEach { it(e) }
	}

	@CName("onGameLoopEventsDayStarted")
	fun onDayStarted(ref: COpaquePointer) {
		val e = DayStartedEventArgs()
		dayStarted.forEach { it(e) }
	}

	@CName("onGameLoopEventsDayEnding")
	fun onDayEnding(ref: COpaquePointer) {
		val e = DayEndingEventArgs()
		dayEnding.forEach { it(e) }
	}

	@CName("onGameLoopEventsTimeChanged")
	fun onTimeChanged(ref: COpaquePointer) {
		val e = TimeChangedEventArgs()
		timeChanged.forEach { it(e) }
	}

	@CName("onGameLoopEventsReturnedToTitle")
	fun onReturnedToTitle(ref: COpaquePointer) {
		val e = ReturnedToTitleEventArgs()
		returnedToTitle.forEach { it(e) }
	}
}

class GameLaunchedEventArgs
class UpdateTickingEventArgs
class UpdateTickedEventArgs
class OneSecondUpdateTickingEventArgs
class OneSecondUpdateTickedEventArgs
class SaveCreatingEventArgs
class SaveCreatedEventArgs
class SavingEventArgs
class SavedEventArgs
class SaveLoadedEventArgs
class DayStartedEventArgs
class DayEndingEventArgs
class TimeChangedEventArgs {
	val oldTime: Int
		get() = TODO()
	val newTime: Int
		get() = TODO()
}
class ReturnedToTitleEventArgs