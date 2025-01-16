package wrapper.event

import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class, ExperimentalForeignApi::class)
class InputEvents {
	val buttonsChanged = mutableListOf<EventHandler<ButtonsChangedEventArgs>>()
	val buttonPressed = mutableListOf<EventHandler<ButtonPressedEventArgs>>()
	val buttonReleased = mutableListOf<EventHandler<ButtonReleasedEventArgs>>()
	val cursorMoved = mutableListOf<EventHandler<CursorMovedEventArgs>>()
	val mouseWheelScrolled = mutableListOf<EventHandler<MouseWheelScrolledEventArgs>>()

	@CName("onInputEventsButtonsChanges")
	fun onButtonsChanges(ref: COpaquePointer) {
		val e = ButtonsChangedEventArgs()
		buttonsChanged.forEach { it(e) }
	}

	@CName("onInputEventsButtonPressed")
	fun onButtonPressed(ref: COpaquePointer) {
		val e = ButtonPressedEventArgs()
		buttonPressed.forEach { it(e) }
	}

	@CName("onInputEventsButtonReleased")
	fun onButtonReleased(ref: COpaquePointer) {
		val e = ButtonReleasedEventArgs()
		buttonReleased.forEach { it(e) }
	}

	@CName("onInputEventsCursorMoved")
	fun onCursorMoved(ref: COpaquePointer) {
		val e = CursorMovedEventArgs()
		cursorMoved.forEach { it(e) }
	}

	@CName("onInputEventsMouseWheelScrolled")
	fun onMouseWheelScrolled(ref: COpaquePointer) {
		val e = MouseWheelScrolledEventArgs()
		mouseWheelScrolled.forEach { it(e) }
	}
}

class ButtonsChangedEventArgs
class ButtonPressedEventArgs
class ButtonReleasedEventArgs
class CursorMovedEventArgs
class MouseWheelScrolledEventArgs