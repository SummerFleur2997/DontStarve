package wrapper

import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi
import wrapper.event.ModLinked

@OptIn(ExperimentalForeignApi::class)
class ReflectionHelper(private val ptr: COpaquePointer) : ModLinked() {
	fun <T> getField(obj: Any, name: String, required: Boolean = true): ReflectedField<T> {
		TODO()
	}

	fun <T> getField(type: Type, name: String, required: Boolean = true): ReflectedField<T> {
		TODO()
	}

	fun <T> getProperty(obj: Any, name: String, required: Boolean = true): ReflectedProperty<T> {
		TODO()
	}

	fun <T> getProperty(type: Type, name: String, required: Boolean = true): ReflectedProperty<T> {
		TODO()
	}

	fun getMethod(obj: Any, name: String, required: Boolean = true): ReflectedMethod {
		TODO()
	}

	fun getMethod(type: Type, name: String, required: Boolean = true): ReflectedMethod {
		TODO()
	}

	override val modId: String
		get() = TODO("Not yet implemented")
}