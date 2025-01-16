package wrapper

import kotlinx.cinterop.*
import wrapper.event.ModLinked
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class, ExperimentalForeignApi::class)
@CName("initCommandHelper")
fun initCommandHelper(
	add: Func<(COpaquePointer, CValuesRef<ByteVar>, CValuesRef<ByteVar>, Func<(CPointer<ByteVar>, CArrayPointer<ByteVar>) -> Unit>) -> Unit>
) {
	CommandHelper.add = add
}

@OptIn(ExperimentalForeignApi::class)
class CommandHelper(private val ptr: COpaquePointer) : ModLinked() {
	fun add(name: String, documentation: String, callback: (String, List<String>) -> Unit) {
		val func = staticCFunction { str: CPointer<ByteVar>, array: CArrayPointer<ByteVar> ->
//			callback(str.toKString(), array.)
			TODO()
			Unit
		}
		add(ptr, name.cstr, documentation.cstr, func)
	}

	companion object {
		lateinit var add: Func<(COpaquePointer, CValuesRef<ByteVar>, CValuesRef<ByteVar>, Func<(CPointer<ByteVar>, CArrayPointer<ByteVar>) -> Unit>) -> Unit>
	}

	override val modId: String
		get() = TODO("Not yet implemented")
}