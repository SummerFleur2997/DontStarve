using DontStarve.Sanity;
using StardewModdingAPI;
using StardewValley;

namespace DontStarve.Buff;

internal static class Sanity {
    private const string BUFF = "DS_Heal_Sanity";
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
                player.setSanity(player.getSanity() + times);
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
        var data = helper.Data.ReadSaveData<SanityData>("DontStarve.Buff.Stamina");
        lastHasBuff = data?.lastHasBuff ?? false;
        lastTime = data?.lastTime ?? 0;
        deviation = data?.deviation ?? 0;
    }

    public static void save(IModHelper helper) {
        helper.Data.WriteSaveData("DontStarve.Buff.Stamina", new SanityData {
            lastHasBuff = lastHasBuff,
            lastTime = lastTime,
            deviation = deviation
        });
    }
}

internal class SanityData {
    internal bool lastHasBuff { get; init; }
    internal long lastTime { get; init; }
    internal long deviation { get; init; }
}