using DontStarve.Player.Hunger;
using DontStarve.Player.Sanity;
using Microsoft.Xna.Framework;
using StardewValley;

namespace DontStarve;

internal static class BarsInformation
{
    private static readonly Color fullSanityColor = new(0xFF, 0xC7, 0x00);
    private static readonly Color zeroSanityColor = new(0xA9, 0xA9, 0xA9);
    private static readonly Color fullHungerColor = new(0xFF, 0xC7, 0x00);
    private static readonly Color zeroHungerColor = new(0xA9, 0xA9, 0xA9);

    internal static Color sanityColor
    {
        get
        {
            var player = Game1.player;
            var percent = player.getSanity() / player.getMaxSanity();
            var lerpR = fullSanityColor.R - zeroSanityColor.R;
            var lerpG = fullSanityColor.G - zeroSanityColor.G;
            var lerpB = fullSanityColor.B - zeroSanityColor.B;
            return new Color(
                zeroSanityColor.R + (int)(percent * lerpR),
                zeroSanityColor.G + (int)(percent * lerpG),
                zeroSanityColor.B + (int)(percent * lerpB)
            );
        }
    }

    internal static Color hungerColor
    {
        get
        {
            var player = Game1.player;
            var percent = player.getHunger() / player.getMaxHunger();
            var lerpR = fullHungerColor.R - zeroHungerColor.R;
            var lerpG = fullHungerColor.G - zeroHungerColor.G;
            var lerpB = fullHungerColor.B - zeroHungerColor.B;
            return new Color(
                zeroHungerColor.R + (int)(percent * lerpR),
                zeroHungerColor.G + (int)(percent * lerpG),
                zeroHungerColor.B + (int)(percent * lerpB)
            );
        }
    }
}