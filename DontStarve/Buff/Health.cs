using StardewModdingAPI;
using StardewValley;

namespace DontStarve.Buff;

internal static class Health {
    private const string BUFF = "DS_Heal_Health";
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
                if (player.health < player.maxHealth) {
                    player.health += Math.Min(2 * times, player.maxHealth - player.health);
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
    
    public static void load(IModHelper helper) {
        var data = helper.Data.ReadSaveData<HealthData>("DontStarve.Buff.Health");
        lastHasBuff = data?.lastHasBuff ?? false;
        lastTime = data?.lastTime ?? 0;
        deviation = data?.deviation ?? 0;
    }

    public static void save(IModHelper helper) {
        helper.Data.WriteSaveData("DontStarve.Buff.Health", new HealthData {
            lastHasBuff = lastHasBuff,
            lastTime = lastTime,
            deviation = deviation
        });
    }
}

internal class HealthData {
    internal bool lastHasBuff { get; init; }
    internal long lastTime { get; init; }
    internal long deviation { get; init; }
}