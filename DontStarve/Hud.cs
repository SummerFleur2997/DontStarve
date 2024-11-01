using DontStarve.Sanity;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using StardewModdingAPI;
using StardewModdingAPI.Events;
using StardewValley;

namespace DontStarve;

public static class Hud {
    private static Vector2 barPosition {
        get {
            var sizeUi = new Vector2(Game1.uiViewport.Width, Game1.uiViewport.Height);
            return new Vector2(sizeUi.X - (Game1.showingHealth ? 171 : 116), sizeUi.Y);
        }
    }
    
    public static void OnRenderingHud(object? _, RenderingHudEventArgs e) {
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
            scale: 1f, SpriteEffects.None,
            layerDepth: 1f
        );

        if (checkMouseHovering()) {
            var information = $"{(int)sanity}/{(int)maxSanity}";
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