using DontStarve.Sanity;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using StardewModdingAPI;
using StardewModdingAPI.Events;
using StardewValley;
using StardewValley.Menus;

namespace DontStarve;

internal static class Hud {
    private static Vector2 barPosition {
        get {
            var sizeUi = new Vector2(Game1.uiViewport.Width, Game1.uiViewport.Height);
            return new Vector2(sizeUi.X - (Game1.showingHealth ? 171 : 116), sizeUi.Y);
        }
    }

    internal static void OnRenderingHud(RenderingHudEventArgs e) {
        if (!Context.IsWorldReady || Game1.CurrentEvent != null) return;

        var player = Game1.player;
        var sanity = player.getSanity();
        var maxSanity = player.getMaxSanity();

        e.SpriteBatch.Draw(
            texture: Textures.sanityContainer,
            destinationRectangle: new Rectangle(
                x: (int)barPosition.X,
                y: (int)barPosition.Y - 240,
                width: Textures.sanityContainer.Width * 4,
                height: Textures.sanityContainer.Height * 4
            ),
            color: Color.White
        );

        e.SpriteBatch.Draw(
            texture: Textures.sanityFiller,
            position: new Vector2(barPosition.X + 36, barPosition.Y - 25),
            sourceRectangle: new Rectangle(
                x: 0,
                y: 0,
                width: Textures.sanityFiller.Width * 6 * Game1.pixelZoom,
                height: (int)(sanity / maxSanity * 168)
            ),
            color: BarsInformation.sanityColor,
            rotation: 3.138997f,
            origin: new Vector2(0.5f, 0.5f),
            scale: 1f,
            effects: SpriteEffects.None,
            layerDepth: 1f
        );

        if (checkMouseHovering()) {
            var information = $"{Math.Round(sanity)}/{Math.Round(maxSanity)}";
            var textSize = Game1.dialogueFont.MeasureString(information);
            var textPosition = new Vector2(-12, textSize.X);

            Game1.spriteBatch.DrawString(
                spriteFont: Game1.dialogueFont,
                text: information,
                position: new Vector2(
                    x: barPosition.X + textPosition.X,
                    y: barPosition.Y - 240 + Textures.sanityContainer.Height + 8
                ),
                color: new Color(255, 255, 255),
                rotation: 0f,
                origin: new Vector2(textPosition.Y, 0),
                scale: 1,
                effects: SpriteEffects.None,
                layerDepth: 0f
            );
        }

        if (player.ActiveObject != null) {
            if (Food.foodSanity.TryGetValue(player.ActiveObject.ItemId, out var foodSanity)) {
                var sizeUi = new Vector2(Game1.uiViewport.Width, Game1.uiViewport.Height);
                var text = $"+{foodSanity} Sanity";
                var textSize = Game1.smallFont.MeasureString(text);
                var spriteBatch = e.SpriteBatch;
                IClickableMenu.drawTextureBox(spriteBatch, Game1.menuTexture, new Rectangle(0, 256, 60, 60), (int)(sizeUi.X / 2) - (int)(textSize.X / 2 + 25), (int)(sizeUi.Y) - 125 - (int)(textSize.Y + 25), (int)(textSize.X + 50), (int)(textSize.Y + 40), Color.White * 1, 1, false, 1);
                Utility.drawTextWithShadow(spriteBatch, text, Game1.smallFont, new Vector2((int)(sizeUi.X / 2) - (int)(textSize.X / 2 + 25) + 25, (int)(sizeUi.Y) - 125 - (int)(textSize.Y + 25) + 20), Game1.textColor);
            }
        }
    }


    private static bool checkMouseHovering() {
        var mousePosition = new Vector2(Game1.getMousePosition(true).X, Game1.getMousePosition(true).Y);
        var checkXGreater = mousePosition.X >= barPosition.X;
        var checkXLess = mousePosition.X <= barPosition.X + Textures.sanityContainer.Width * 4;
        var checkYGreater = mousePosition.Y >= barPosition.Y - 240;
        var checkYLess = mousePosition.Y <= barPosition.Y - 240 + Textures.sanityContainer.Height * 4;
        var checkX = checkXGreater && checkXLess;
        var checkY = checkYGreater && checkYLess;
        return checkX && checkY;
    }
}