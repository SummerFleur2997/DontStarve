package wrapper

import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi
import wrapper.event.ModLinked

@OptIn(ExperimentalForeignApi::class)
class MultiplayerHelper(private val ptr: COpaquePointer) : ModLinked() {
	fun getNewId(): Long {
		TODO()
	}

	fun getActiveLocations(): List<GameLocation> {
		TODO()
	}

	fun getConnectedPlayer(id: Long): MultiplayerPeer? {
		TODO()
	}

	fun getConnectedPlayers(): List<MultiplayerPeer> {
		TODO()
	}

	fun <T> sendMessage(message: T, type: String, modIds: List<String>? = null, playerIds: List<Long>? = null) {
		TODO()
	}

	override val modId: String
		get() = TODO("Not yet implemented")
}