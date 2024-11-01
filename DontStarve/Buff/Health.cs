using StardewValley;

namespace DontStarve.Buff;

internal static class Health {
    private const string BUFF = "DS_Heal_Health";
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
                if (player.health < player.maxHealth) {
                    player.health += Math.Min(2 * times, player.maxHealth - player.health);
                }

                lastTime += times * 5;
            }
        }

        lastHasBuff = hasBuff;
    }
}