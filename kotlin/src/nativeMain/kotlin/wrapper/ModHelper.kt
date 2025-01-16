package wrapper

import kotlinx.cinterop.*
import wrapper.event.ModEvents
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class, ExperimentalForeignApi::class)
@CName("initModHelper")
fun initModHelper(
	directoryPath: Func<(COpaquePointer) -> CPointer<ByteVar>>,
	consoleCommands: Func<(COpaquePointer) -> COpaquePointer>,
	gameContent: Func<(COpaquePointer) -> COpaquePointer>,
	modContent: Func<(COpaquePointer) -> COpaquePointer>,
	contentPacks: Func<(COpaquePointer) -> COpaquePointer>,
	data: Func<(COpaquePointer) -> COpaquePointer>,
	input: Func<(COpaquePointer) -> COpaquePointer>,
	reflection: Func<(COpaquePointer) -> COpaquePointer>,
	modRegistry: Func<(COpaquePointer) -> COpaquePointer>,
	multiplayer: Func<(COpaquePointer) -> COpaquePointer>,
	translation: Func<(COpaquePointer) -> COpaquePointer>,
) {
	ModHelper.directoryPath = directoryPath
	ModHelper.consoleCommands = consoleCommands
	ModHelper.gameContent = gameContent
	ModHelper.modContent = modContent
	ModHelper.contentPacks = contentPacks
	ModHelper.data = data
	ModHelper.input = input
	ModHelper.reflection = reflection
	ModHelper.modRegistry = modRegistry
	ModHelper.multiplayer = multiplayer
	ModHelper.translation = translation
}

@OptIn(ExperimentalForeignApi::class)
class ModHelper(ptr: COpaquePointer) {
	val directoryPath = directoryPath(ptr).toKString()
	val events = ModEvents()
	val consoleCommands = CommandHelper(consoleCommands(ptr))
	val gameContent = GameContentHelper(gameContent(ptr))
	val modContent = ModContentHelper(modContent(ptr))
	val contentPacks = ContentPackHelper(contentPacks(ptr))
	val data = DataHelper(data(ptr))
	val input = InputHelper(input(ptr))
	val reflection = ReflectionHelper(reflection(ptr))
	val modRegistry = ModRegistry(modRegistry(ptr))
	val multiplayer = MultiplayerHelper(multiplayer(ptr))
	val translation = TranslationHelper(translation(ptr))

	companion object {
		lateinit var directoryPath: Func<(COpaquePointer) -> CPointer<ByteVar>>
		lateinit var consoleCommands: Func<(COpaquePointer) -> COpaquePointer>
		lateinit var gameContent: Func<(COpaquePointer) -> COpaquePointer>
		lateinit var modContent: Func<(COpaquePointer) -> COpaquePointer>
		lateinit var contentPacks: Func<(COpaquePointer) -> COpaquePointer>
		lateinit var data: Func<(COpaquePointer) -> COpaquePointer>
		lateinit var input: Func<(COpaquePointer) -> COpaquePointer>
		lateinit var reflection: Func<(COpaquePointer) -> COpaquePointer>
		lateinit var modRegistry: Func<(COpaquePointer) -> COpaquePointer>
		lateinit var multiplayer: Func<(COpaquePointer) -> COpaquePointer>
		lateinit var translation: Func<(COpaquePointer) -> COpaquePointer>
	}
}