package wrapper

import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi
import wrapper.event.ModLinked

@OptIn(ExperimentalForeignApi::class)
class ModContentHelper(private val ptr: COpaquePointer) : ModLinked() {
	fun <T : Any> doesAssetExist(relativePath: String): Boolean {
		TODO()
	}

	fun <T : Any> load(relativePath: String): T {
		TODO()
	}

	fun getInternalAssetName(relativePath: String): AssetName {
		TODO()
	}

	fun <T : Any> getPatchHelper(data: T, relativePath: String? = null): AssetData {
		TODO()
	}

	override val modId: String
		get() = TODO("Not yet implemented")
}