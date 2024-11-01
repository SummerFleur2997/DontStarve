using DontStarve.Sanity;
using Microsoft.Xna.Framework;
using StardewValley;

namespace DontStarve;

public static class BarsInformation {
    private static readonly Color fullSanityColor = new(0xFF, 0xFF, 0x00);

    public static Color sanityColor {
        get {
            var player = Game1.player;
            var value = player.getSanity();
            var max = player.getMaxSanity();
            var offset = value / max;

            var color = fullSanityColor;
            color.R = Convert.ToByte(Math.Abs(offset - 1) * byte.MaxValue);
            color.G = Convert.ToByte(offset * color.G);
            color.B = Convert.ToByte(offset * color.B);

            return color;
        }
    }
}