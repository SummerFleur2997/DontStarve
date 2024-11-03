using StardewValley;

namespace DontStarve.Buff;

internal static class Stamina {
    private const string BUFF = "DS_Heal_Stamina";
    private static bool lastHasBuff;
    private static long lastTime;
    private static long deviation;

    public static void update(long time) {
        var player = Game1.player;
        var hasBuff = player.hasBuff(BUFF);
        
        if (hasBuff && !lastHasBuff) {
            lastTime = time;
            deviation = 0;
        }
        
        if (lastHasBuff) {
            var delta = time - lastTime;
            if (delta >= 120 - deviation) {
                var times = (int)(1 + (delta - (120 - deviation)) / 120);
                if (player.Stamina < player.MaxStamina) {
                    player.Stamina += Math.Min(times, player.MaxStamina - player.Stamina);
                }

                deviation = delta - (120 - deviation) - (times - 1) * 120;
                lastTime = time;
            }
        }

        lastHasBuff = hasBuff;
    }

    public static void sync(long time, long delta) {
        deviation += delta;
        update(time);
    }
}