package wrapper

import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi
import wrapper.event.ModLinked

@OptIn(ExperimentalForeignApi::class)
class ModRegistry(private val ptr: COpaquePointer) : ModLinked() {
	fun getAll(): List<ModInfo> {
		TODO()
	}

	fun get(uniqueId: String): ModInfo? {
		TODO()
	}

	fun isLoaded(uniqueId: String): Boolean {
		TODO()
	}

	fun getApi(uniqueId: String): Any? {
		TODO()
	}

	fun <T> getApi(uniqueId: String): T? {
		TODO()
	}

	override val modId: String
		get() = TODO("Not yet implemented")
}