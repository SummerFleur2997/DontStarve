using Microsoft.Xna.Framework.Graphics;
using StardewModdingAPI;
using StardewValley;

namespace DontStarve;

internal static class Textures {
    public static Texture2D sanityContainer = null!;
    private static Texture2D backingSanityFiller = null!;

    internal static Texture2D sanityFiller {
        get {
            var color = BarsInformation.sanityColor;
            backingSanityFiller.SetData(new[] { color });
            return backingSanityFiller;
        }
    }

    internal static void loadTextures(IModContentHelper modContent) {
        sanityContainer = modContent.Load<Texture2D>("assets/sanity/bar.png");
        backingSanityFiller = new Texture2D(Game1.graphics.GraphicsDevice, 1, 1);
    }
}