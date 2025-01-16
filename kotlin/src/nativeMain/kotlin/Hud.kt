import Hunger.getHunger
import Hunger.getMaxHunger
import Sanity.getMaxSanity
import Sanity.getSanity
import wrapper.*
import wrapper.event.RenderingHudEventArgs
import kotlin.math.round

object Hud {
	private val barPosition: Vector2
		get() {
			val sizeUi = Vector2(Game1.uiViewport.Width, Game1.uiViewport.Height)
			return Vector2(sizeUi.x - (if (Game1.showingHealth) 171 else 116), sizeUi.y)
		}

	fun onRenderingHud(helper: ModHelper, e: RenderingHudEventArgs) {
		if (!Context.isWorldReady || Game1.currentEvent != null) return
		onRenderingHunger(e)
		onRenderingSanity(e)
		onRenderingTooltip(helper, e)
	}

	private fun onRenderingHunger(e: RenderingHudEventArgs) {
		val player = Game1.player
		val hunger = player.getHunger()
		val maxHunger = player.getMaxHunger()

		e.spriteBatch.Draw(
			texture = Textures.hungerContainer,
			destinationRectangle = Rectangle(
				x = barPosition.x - 60,
				y = barPosition.y - 240,
				width = Textures.hungerContainer.width * 4,
				height = Textures.hungerContainer.height * 4
			),
			color = Color.WHITE
		)

		e.spriteBatch.Draw(
			texture = Textures.sanityFiller,
			position = Vector2(barPosition.x - 24, barPosition.y - 25),
			sourceRectangle = Rectangle(
				x = 0,
				y = 0,
				width = Textures.sanityFiller.width * 6 * Game1.PIXEL_ZOOM,
				height = (hunger / maxHunger * 168)
			),
			color = BarsInformation.hungerColor,
			rotation = 3.138997f,
			origin = Vector2(0.5f, 0.5f),
			scale = 1f,
			effects = SpriteEffects.None,
			layerDepth = 1f
		)

		val mousePosition = Vector2(Game1.getMousePosition(true).X, Game1.getMousePosition(true).Y)
		val checkXGreater = mousePosition.x >= barPosition.x - 60
		val checkXLess = mousePosition.x <= barPosition.x - 60 + Textures.sanityContainer.width * 4
		val checkYGreater = mousePosition.y >= barPosition.y - 240
		val checkYLess = mousePosition.y <= barPosition.y - 240 + Textures.sanityContainer.height * 4
		val checkX = checkXGreater && checkXLess
		val checkY = checkYGreater && checkYLess

		if (checkX && checkY) {
			val information = "${round(hunger)}/${round(maxHunger)}"
			val textSize = Game1.dialogueFont.MeasureString(information)
			val textPosition = Vector2(-12F, textSize.X)

			Game1.spriteBatch.DrawString(
				spriteFont = Game1.dialogueFont,
				text = information,
				position = Vector2(
					x = barPosition.x - 60 + textPosition.x,
					y = barPosition.y - 240 + Textures.sanityContainer.height + 8
				),
				color = Color(255, 255, 255),
				rotation = 0f,
				origin = Vector2(textPosition.y, 0F),
				scale = 1,
				effects = SpriteEffects.None,
				layerDepth = 0f
			)
		}
	}

	private fun onRenderingSanity(e: RenderingHudEventArgs) {
		val player = Game1.player
		val sanity = player.getSanity()
		val maxSanity = player.getMaxSanity()

		e.spriteBatch.Draw(
			texture = Textures.sanityContainer,
			destinationRectangle = Rectangle(
				x = barPosition.x,
				y = barPosition.y - 240,
				width = Textures.sanityContainer.width * 4,
				height = Textures.sanityContainer.height * 4
			),
			color = Color.WHITE
		)

		e.spriteBatch.Draw(
			texture = Textures.sanityFiller,
			position = Vector2(barPosition.x + 36, barPosition.y - 25),
			sourceRectangle = Rectangle(
				x = 0,
				y = 0,
				width = Textures.sanityFiller.width * 6 * Game1.PIXEL_ZOOM,
				height = (sanity / maxSanity * 168)
			),
			color = BarsInformation.sanityColor,
			rotation = 3.138997f,
			origin = Vector2(0.5f, 0.5f),
			scale = 1f,
			effects = SpriteEffects.None,
			layerDepth = 1f
		)

		val mousePosition = Vector2(Game1.getMousePosition(true).X, Game1.getMousePosition(true).Y)
		val checkXGreater = mousePosition.x >= barPosition.x
		val checkXLess = mousePosition.x <= barPosition.x + Textures.sanityContainer.width * 4
		val checkYGreater = mousePosition.y >= barPosition.y - 240
		val checkYLess = mousePosition.y <= barPosition.y - 240 + Textures.sanityContainer.height * 4
		val checkX = checkXGreater && checkXLess
		val checkY = checkYGreater && checkYLess

		if (checkX && checkY) {
			val information = "${round(sanity)}/${round(maxSanity)}"
			val textSize = Game1.dialogueFont.MeasureString(information)
			val textPosition = Vector2(-12F, textSize.X)

			Game1.spriteBatch.DrawString(
				spriteFont = Game1.dialogueFont,
				text = information,
				position = Vector2(
					x = barPosition.x + textPosition.x,
					y = barPosition.y - 240 + Textures.sanityContainer.height + 8
				),
				color = Color(255, 255, 255),
				rotation = 0f,
				origin = Vector2(textPosition.y, 0),
				scale = 1,
				effects = SpriteEffects.None,
				layerDepth = 0f
			)
		}
	}

	private fun onRenderingTooltip(helper: ModHelper, e: RenderingHudEventArgs) {
		val player = Game1.player

		val activeObject = player.activeObject
		if (activeObject != null) {
			val foodHunger = Hunger.EatFood.foodHunger[activeObject.ItemId]
			val foodSanity = Sanity.EatFood.foodSanity[activeObject.ItemId]
			if (foodSanity != null || foodHunger != null) {
				val sizeUi = Vector2(Game1.uiViewport.Width, Game1.uiViewport.Height)
				val text = StringBuilder()
				if (foodHunger != null) {
					text.append(helper.translation.get("hunger-tooltip", object { val value = foodHunger }))
				}

				if (foodHunger != null && foodSanity != null) {
					text.appendLine()
				}

				if (foodSanity != null) {
					text.append(helper.translation.get("sanity-tooltip", object { val value = foodSanity }))
				}

				val textSize = Game1.smallFont.MeasureString(text)
				val spriteBatch = e.spriteBatch
				ClickableMenu.drawTextureBox(
					b = spriteBatch,
					texture = Game1.menuTexture,
					sourceRect = Rectangle(0, 256, 60, 60),
					x = (sizeUi.x / 2) - (textSize.x / 2 + 25),
					y = sizeUi.y - 125 - (textSize.y + 25),
					width = (textSize.X + 50),
					height = (textSize.Y + 40),
					color = Color.WHITE * 1,
					scale = 1,
					drawShadow = false,
					draw_layer = 1
				)
				Utility.drawTextWithShadow(
					b = spriteBatch,
					text = text,
					font = Game1.smallFont,
					position = Vector2(
						x = (sizeUi.x / 2) - (textSize.x / 2 + 25) + 25,
						y = sizeUi.y - 125 - (textSize.y + 25) + 20
					),
					color = Game1.textColor
				)
			}
		}
	}
}