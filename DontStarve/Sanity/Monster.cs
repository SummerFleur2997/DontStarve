using DontStarve.Buff;
using StardewModdingAPI;
using StardewValley;

namespace DontStarve.Sanity;

public static class Monster {
    private static long lastTime;
    private static long deviation;

    public static void update(long time) {
        var player = Game1.player;
        
        if (deviation < 0) {
            deviation++;
            return;
        }
        
        lastTime = time;
    }

    public static void sync(long time, long delta) {
        deviation += delta;
        update(time);
    }
    
    public static void load(IModHelper helper) {
        var data = helper.Data.ReadSaveData<MonsterData>("DontStarve.Sanity.Monster");
        lastTime = data?.lastTime ?? 0;
        deviation = data?.deviation ?? 0;
    }

    public static void save(IModHelper helper) {
        helper.Data.WriteSaveData("DontStarve.Sanity.Monster", new MonsterData {
            lastTime = lastTime,
            deviation = deviation
        });
    }
}

internal class MonsterData {
    internal long lastTime { get; init; }
    internal long deviation { get; init; }
}