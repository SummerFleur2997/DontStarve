package wrapper

import kotlinx.cinterop.*
import wrapper.event.ModLinked

@OptIn(ExperimentalForeignApi::class)
class GameContentHelper(private val ptr: COpaquePointer) : ModLinked() {
	val currentLocale: String
		get() = TODO()

	val currentLocaleConstant: LocalizedContentManager.LanguageCode
		get() = TODO()

	fun parseAssetName(rawName: String): AssetName {
		TODO()
	}

	fun <T : Any> doesAssetExist(assetName: AssetName): Boolean {
		TODO()
	}

	fun <T : Any> load(assetName: String): T {
		TODO()
	}

	fun <T : Any> load(assetName: AssetName): T {
		TODO()
	}

	fun invalidateCache(assetName: String): Boolean {
		TODO()
	}

	fun invalidateCache(assetName: AssetName): Boolean {
		TODO()
	}

	fun <T : Any> invalidateCache(): Boolean {
		TODO()
	}

	fun invalidateCache(predicate: (AssetInfo) -> Boolean): Boolean {
		TODO()
	}

	fun <T : Any> getPatchHelper(data: T, assetName: String? = null): AssetData {
		TODO()
	}

	override val modId: String
		get() = TODO("Not yet implemented")
}