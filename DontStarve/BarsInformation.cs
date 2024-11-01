using DontStarve.Sanity;
using Microsoft.Xna.Framework;
using StardewValley;

namespace DontStarve;

public static class BarsInformation {
    private static readonly Color fullSanityColor = new(0x8F, 0x50, 0xD2);

    public static Color sanityColor {
        get {
            var player = Game1.player;
            var percent = player.getSanity() / player.getMaxSanity();
            return new Color(
                r: (int)(percent * fullSanityColor.R),
                g: (int)(percent * fullSanityColor.G),
                b: (int)(percent * fullSanityColor.B)
            );
        }
    }
}