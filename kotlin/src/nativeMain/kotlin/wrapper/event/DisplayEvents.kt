package wrapper.event

import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class, ExperimentalForeignApi::class)
class DisplayEvents {
	val menuChanged = mutableListOf<EventHandler<MenuChangedEventArgs>>()
	val renderingStep = mutableListOf<EventHandler<RenderingStepEventArgs>>()
	val renderedStep = mutableListOf<EventHandler<RenderedStepEventArgs>>()
	val rendering = mutableListOf<EventHandler<RenderingEventArgs>>()
	val rendered = mutableListOf<EventHandler<RenderedEventArgs>>()
	val renderingWorld = mutableListOf<EventHandler<RenderingWorldEventArgs>>()
	val renderedWorld = mutableListOf<EventHandler<RenderedWorldEventArgs>>()
	val renderingActiveMenu = mutableListOf<EventHandler<RenderingActiveMenuEventArgs>>()
	val renderedActiveMenu = mutableListOf<EventHandler<RenderedActiveMenuEventArgs>>()
	val renderingHud = mutableListOf<EventHandler<RenderingHudEventArgs>>()
	val renderedHud = mutableListOf<EventHandler<RenderedHudEventArgs>>()
	val windowResized = mutableListOf<EventHandler<WindowResizedEventArgs>>()

	@CName("onDisplayEventsMenuChanged")
	fun onMenuChanged(ref: COpaquePointer) {
		val e = MenuChangedEventArgs()
		menuChanged.forEach { it(e) }
	}

	@CName("onDisplayEventsRenderingStep")
	fun onRenderingStep(ref: COpaquePointer) {
		val e = RenderingStepEventArgs()
		renderingStep.forEach { it(e) }
	}

	@CName("onDisplayEventsRenderedStep")
	fun onRenderedStep(ref: COpaquePointer) {
		val e = RenderedStepEventArgs()
		renderedStep.forEach { it(e) }
	}

	@CName("onDisplayEventsRendering")
	fun onRendering(ref: COpaquePointer) {
		val e = RenderingEventArgs()
		rendering.forEach { it(e) }
	}

	@CName("onDisplayEventsRendered")
	fun onRendered(ref: COpaquePointer) {
		val e = RenderedEventArgs()
		rendered.forEach { it(e) }
	}

	@CName("onDisplayEventsRenderingWorld")
	fun onRenderingWorld(ref: COpaquePointer) {
		val e = RenderingWorldEventArgs()
		renderingWorld.forEach { it(e) }
	}

	@CName("onDisplayEventsRenderedWorld")
	fun onRenderedWorld(ref: COpaquePointer) {
		val e = RenderedWorldEventArgs()
		renderedWorld.forEach { it(e) }
	}

	@CName("onDisplayEventsRenderingActiveMenu")
	fun onRenderingActiveMenu(ref: COpaquePointer) {
		val e = RenderingActiveMenuEventArgs()
		renderingActiveMenu.forEach { it(e) }
	}

	@CName("onDisplayEventsRenderedActiveMenu")
	fun onRenderedActiveMenu(ref: COpaquePointer) {
		val e = RenderedActiveMenuEventArgs()
		renderedActiveMenu.forEach { it(e) }
	}

	@CName("onDisplayEventsRenderingHud")
	fun onRenderingHud(ref: COpaquePointer) {
		val e = RenderingHudEventArgs()
		renderingHud.forEach { it(e) }
	}

	@CName("onDisplayEventsRenderedHud")
	fun onRenderedHud(ref: COpaquePointer) {
		val e = RenderedHudEventArgs()
		renderedHud.forEach { it(e) }
	}

	@CName("onDisplayEventsWindowResized")
	fun onWindowResized(ref: COpaquePointer) {
		val e = WindowResizedEventArgs()
		windowResized.forEach { it(e) }
	}
}

class MenuChangedEventArgs
class RenderingStepEventArgs
class RenderedStepEventArgs
class RenderingEventArgs
class RenderedEventArgs
class RenderingWorldEventArgs
class RenderedWorldEventArgs
class RenderingActiveMenuEventArgs
class RenderedActiveMenuEventArgs
class RenderingHudEventArgs {
	val spriteBatch: SpriteBatch
		get() = TODO()
}
class RenderedHudEventArgs
class WindowResizedEventArgs