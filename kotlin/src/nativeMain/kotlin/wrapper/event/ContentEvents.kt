package wrapper.event

import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class, ExperimentalForeignApi::class)
class ContentEvents {
	val assetRequested = mutableListOf<EventHandler<AssetRequestedEventArgs>>()
	val assetsInvalidated = mutableListOf<EventHandler<AssetsInvalidatedEventArgs>>()
	val assetReady = mutableListOf<EventHandler<AssetReadyEventArgs>>()
	val localeChanged = mutableListOf<EventHandler<LocaleChangedEventArgs>>()

	@CName("onContentEventsAssetRequested")
	fun onAssetRequested(ref: COpaquePointer) {
		val e = AssetRequestedEventArgs()
		assetRequested.forEach { it(e) }
	}

	@CName("onContentEventsAssetsInvalidated")
	fun onAssetsInvalidated(ref: COpaquePointer) {
		val e = AssetsInvalidatedEventArgs()
		assetsInvalidated.forEach { it(e) }
	}

	@CName("onContentEventsAssetReady")
	fun onAssetReady(ref: COpaquePointer) {
		val e = AssetReadyEventArgs()
		assetReady.forEach { it(e) }
	}

	@CName("onContentEventsLocaleChanged")
	fun onLocaleChanged(ref: COpaquePointer) {
		val e = LocaleChangedEventArgs()
		localeChanged.forEach { it(e) }
	}
}

class AssetRequestedEventArgs
class AssetsInvalidatedEventArgs
class AssetReadyEventArgs
class LocaleChangedEventArgs