package wrapper

import kotlinx.cinterop.*
import wrapper.event.ModLinked

@OptIn(ExperimentalForeignApi::class)
class TranslationHelper(private val ptr: COpaquePointer) : ModLinked() {
	val locale: String
		get() = TODO()
	val localeEnum: LocalizedContentManager.LanguageCode
		get() = TODO()

	fun getTranslations(): List<Translation> {
		TODO()
	}

	fun get(key: String): Translation {
		TODO()
	}

	fun get(key: String, tokens: Any?): Translation {
		TODO()
	}

	fun getInAllLocales(key: String, withFallback: Boolean = false): Map<String, Translation> {
		TODO()
	}

	override val modId: String
		get() = TODO("Not yet implemented")
}