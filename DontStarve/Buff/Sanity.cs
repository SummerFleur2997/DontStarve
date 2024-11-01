using DontStarve.Sanity;
using StardewValley;

namespace DontStarve.Buff;

internal static class Sanity {
    private const string BUFF = "DS_Heal_Sanity";
    private static bool lastHasBuff;
    private static int lastTime = Game1.timeOfDay;

    public static void update() {
        var player = Game1.player;
        var hasBuff = player.hasBuff(BUFF);

        if (hasBuff) {
            if (hasBuff != lastHasBuff) {
                lastTime = Game1.timeOfDay;
            }

            var time = Game1.timeOfDay;
            if (time - lastTime >= 5) {
                var times = (time - lastTime) / 5;
                if (player.getSanity() < player.getMaxSanity()) {
                    player.setSanity(player.getSanity() + Math.Min(times, player.getMaxSanity() - player.getSanity()));
                }

                lastTime += times * 5;
            }
        }

        lastHasBuff = hasBuff;
    }
}