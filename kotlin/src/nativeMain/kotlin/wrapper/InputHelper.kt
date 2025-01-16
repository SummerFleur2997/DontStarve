package wrapper

import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi
import wrapper.event.ModLinked

@OptIn(ExperimentalForeignApi::class)
class InputHelper(private val ptr: COpaquePointer) : ModLinked() {
	fun getCursorPosition(): ICursorPosition {
		TODO()
	}

	fun isDown(button: SButton): Boolean {
		TODO()
	}

	fun isSuppressed(button: SButton): Boolean {
		TODO()
	}

	fun suppress(button: SButton) {
		TODO()
	}

	fun suppressScrollWheel() {
		TODO()
	}

	fun suppressActiveKeyBinds(keyBindList: KeyBindList) {
		TODO()
	}

	fun getState(button: SButton): SButtonState {
		TODO()
	}

	override val modId: String
		get() = TODO("Not yet implemented")
}