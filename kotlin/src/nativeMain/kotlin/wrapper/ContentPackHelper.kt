package wrapper

import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi
import wrapper.event.ModLinked

@OptIn(ExperimentalForeignApi::class)
class ContentPackHelper(private val ptr: COpaquePointer) : ModLinked() {
	fun getOwned(): List<ContentPack> {
		TODO()
	}

	fun createFake(directoryPath: String): ContentPack {
		TODO()
	}

	fun createTemporary(
		directoryPath: String,
		id: String,
		name: String,
		description: String,
		author: String,
		version: SegmanticVersion
	): ContentPack {
		TODO()
	}

	override val modId: String
		get() = TODO("Not yet implemented")
}