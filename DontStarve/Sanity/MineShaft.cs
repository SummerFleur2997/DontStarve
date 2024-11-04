using StardewModdingAPI;
using StardewValley;
using StardewValley.Locations;

namespace DontStarve.Sanity;

internal static class MineShaft {
    private static long deviation;

    internal static void update(long time) {
        var player = Game1.player;
        var location = Game1.currentLocation;

        if (deviation < 0) {
            deviation++;
            return;
        }
        
        var value = 0.0;
        if (location is StardewValley.Locations.MineShaft mineShaft) {
            // 矿井
            value = 0.0014;
            // 黑暗层
            if (mineShaft.isDarkArea()) {
                value = 0.014;
            }
            // 骷髅矿井
            if (mineShaft.mineLevel > 120) {
                value = 0.0028;
            }
            // 骷髅矿井 880 层以上
            if (mineShaft.mineLevel > 1000) {
                value = 0.0056;
            }
            // 采石场矿井
            if (mineShaft.isQuarryArea) {
                value = 0.0042;
            }
            // 感染层
            if (mineShaft.isSlimeArea) {
                value += 0.0028;
            }
            // 地牢层
            if (mineShaft.isMonsterArea) {
                value += 0.0056;
            }
            // 史前层
            if (mineShaft.isDinoArea) {
                value += 0.0056;
            }
            // 危险矿井
            if (mineShaft.GetAdditionalDifficulty() > 0) {
                // 骷髅矿井
                if (mineShaft.mineLevel > 120) {
                    value += 0.0056;
                } else {
                    value += 0.0028;
                }
            }
        }
        // 火山矿井
        else if (location is VolcanoDungeon) {
            value = 0.0028;
        }

        if (value > 0) {
            player.setSanity(player.getSanity() - value * (1 + deviation));
            Console.WriteLine($"mineshaft: time: {time}, deviation: {deviation}, value: {value * (1 + deviation)}({value})");
        }
        
        deviation = 0;
    }

    internal static void sync(long time, long delta) {
        Console.WriteLine($"mineshaft sync: time: {time}, delta: {delta}");
        deviation += delta;
        update(time);
    }
    
    internal static void load(IModHelper helper) {
        var data = helper.Data.ReadSaveData<MineShaftData>("DontStarve.Sanity.MineShaft");
        deviation = data?.deviation ?? 0;
    }

    internal static void save(IModHelper helper) {
        helper.Data.WriteSaveData("DontStarve.Sanity.MineShaft", new MineShaftData {
            deviation = deviation
        });
    }
}

internal class MineShaftData {
    internal long deviation { get; init; }
}