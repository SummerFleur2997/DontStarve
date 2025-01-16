package wrapper.event

import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class, ExperimentalForeignApi::class)
class MultiplayerEvents {
	val peerContextReceived = mutableListOf<EventHandler<PeerContextReceivedEventArgs>>()
	val peerConnected = mutableListOf<EventHandler<PeerConnectedEventArgs>>()
	val modMessageReceived = mutableListOf<EventHandler<ModMessageReceivedEventArgs>>()
	val peerDisconnected = mutableListOf<EventHandler<PeerDisconnectedEventArgs>>()

	@CName("onMultiplayerEventsPeerContextReceived")
	fun onPeerContextReceived(ref: COpaquePointer) {
		val e = PeerContextReceivedEventArgs()
		peerContextReceived.forEach { it(e) }
	}

	@CName("onMultiplayerEventsPeerConnected")
	fun onPeerConnected(ref: COpaquePointer) {
		val e = PeerConnectedEventArgs()
		peerConnected.forEach { it(e) }
	}

	@CName("onMultiplayerEventsModMessageReceived")
	fun onModMessageReceived(ref: COpaquePointer) {
		val e = ModMessageReceivedEventArgs()
		modMessageReceived.forEach { it(e) }
	}

	@CName("onMultiplayerEventsPeerDisconnected")
	fun onPeerDisconnected(ref: COpaquePointer) {
		val e = PeerDisconnectedEventArgs()
		peerDisconnected.forEach { it(e) }
	}
}

class PeerContextReceivedEventArgs
class PeerConnectedEventArgs
class ModMessageReceivedEventArgs
class PeerDisconnectedEventArgs