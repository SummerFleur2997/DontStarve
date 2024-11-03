using DontStarve.Sanity;
using Microsoft.Xna.Framework;
using StardewValley;

namespace DontStarve;

public static class BarsInformation {
    private static readonly Color fullSanityColor = new(0xFF, 0xC7, 0x00);
    private static readonly Color zeroSanityColor = new(0xA9, 0xA9, 0xA9);

    public static Color sanityColor {
        get {
            var player = Game1.player;
            var percent = player.getSanity() / player.getMaxSanity();
            var lerpR = fullSanityColor.R - zeroSanityColor.R;
            var lerpG = fullSanityColor.G - zeroSanityColor.G;
            var lerpB = fullSanityColor.B - zeroSanityColor.B;
            return new Color(
                r: zeroSanityColor.R + (int)(percent * lerpR),
                g: zeroSanityColor.G + (int)(percent * lerpG),
                b: zeroSanityColor.B + (int)(percent * lerpB)
            );
        }
    }
}