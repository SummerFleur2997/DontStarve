using DontStarve.Integration;
using StardewModdingAPI;
using StardewValley;

namespace DontStarve.Sanity;

internal static class Night {
    private static long deviation;

    internal static void update(long time) {
        if (deviation < 0) {
            deviation++;
            return;
        }
        
        var player = Game1.player;
        var timeOfDay = time % GameTime.TICKS_PER_DAY;
        var lastTimeOfDay = (time - (1 + deviation)) % GameTime.TICKS_PER_DAY;
        var nightfallTime = 0L;
        var midnightTime = 0L;

        var nightfallStart = Game1.season switch {
            Season.Spring => 20 * GameTime.TICKS_PER_HOUR,
            Season.Summer => 20 * GameTime.TICKS_PER_HOUR,
            Season.Fall => 19 * GameTime.TICKS_PER_HOUR,
            Season.Winter => 18 * GameTime.TICKS_PER_HOUR,
            _ => throw new Exception("Unknown Season")
        };

        const long midnightEnd = 6 * GameTime.TICKS_PER_HOUR;

        // 当前时间是白天
        if (timeOfDay < nightfallStart && timeOfDay >= midnightEnd) {
            // 上次时间是凌晨
            if (lastTimeOfDay < midnightEnd) {
                midnightTime = midnightEnd - lastTimeOfDay;
            }
        }
        // 当前时间是黄昏
        else if (timeOfDay >= nightfallStart) {
            // 上次时间是白天
            if (lastTimeOfDay < nightfallStart && lastTimeOfDay >= midnightEnd) {
                nightfallTime = timeOfDay - nightfallStart;
            }
            // 上次时间是黄昏
            else {
                nightfallTime = timeOfDay - lastTimeOfDay;
            }
        }
        // 当前时间是凌晨
        else {
            // 上次时间是黄昏
            if (lastTimeOfDay >= nightfallStart) {
                nightfallTime = GameTime.TICKS_PER_DAY - lastTimeOfDay;
                midnightTime = timeOfDay;
            }
            // 上次时间是凌晨
            else {
                midnightTime = timeOfDay - lastTimeOfDay;
            }
        }

        var nightfallSanity = nightfallTime * 0.0014;
        var midnightSanity = midnightTime * (Game1.currentLocation.IsOutdoors ? 0.007 : 0.0014);
        var value = nightfallSanity + midnightSanity;
        if (value > 0) {
            player.setSanity(player.getSanity() - value);
        }

        deviation = 0;
    }

    internal static void sync(long time, long delta) {
        deviation += delta;
        update(time);
    }
    
    internal static void load(IModHelper helper) {
        var data = helper.Data.ReadSaveData<NightData>("DontStarve.Sanity.Night");
        deviation = data?.deviation ?? 0;
    }

    internal static void save(IModHelper helper) {
        helper.Data.WriteSaveData("DontStarve.Sanity.Night", new NightData {
            deviation = deviation
        });
    }
}

internal class NightData {
    internal long deviation { get; init; }
}