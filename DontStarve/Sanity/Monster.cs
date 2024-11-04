using StardewModdingAPI;
using StardewValley;

namespace DontStarve.Sanity;

internal static class Monster {
    private static Dictionary<string, double> monsterSanity = null!;
    private static long lastTime;
    private static long deviation;
    
    internal static void init(IModHelper helper) {
        monsterSanity = helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/monster.json");
    }

    internal static void update(long time) {
        var player = Game1.player;
        
        if (deviation < 0) {
            deviation++;
            return;
        }
        
        lastTime = time;
    }

    internal static void sync(long time, long delta) {
        deviation += delta;
        update(time);
    }
    
    internal static void load(IModHelper helper) {
        var data = helper.Data.ReadSaveData<MonsterData>("DontStarve.Sanity.Monster");
        lastTime = data?.lastTime ?? 0;
        deviation = data?.deviation ?? 0;
    }

    internal static void save(IModHelper helper) {
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