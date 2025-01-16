package wrapper

import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
class DataHelper(private val ptr: COpaquePointer) {
	fun <T> readJsonFile(path: String): T? {
		TODO()
	}

	fun <T> writeJsonFile(path: String, data: T?) {
		TODO()
	}

	fun <T> readSaveData(key: String): T? {
		TODO()
	}

	fun <T> writeSaveData(key: String, data: T?) {
		TODO()
	}

	fun <T> readGlobalData(key: String): T? {
		TODO()
	}

	fun <T> writeGlobalData(key: String, data: T) {
		TODO()
	}
}