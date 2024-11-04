using StardewModdingAPI;
using StardewValley;

namespace DontStarve.Sanity;

public static class Sanity {
    private const double FARMER_MAX_SANITY = 150;
    private static double farmerSanity;

    internal static void init(IModHelper helper) {
        Food.init(helper);
        Monster.init(helper);
        Wearing.init(helper);
    }

    internal static void update(long time) {
        Food.update(time);
        Monster.update(time);
        Night.update(time);
        Wearing.update(time);
        Npc.update(time);
        MineShaft.update(time);
    }

    internal static void sync(long time, long delta) {
        Monster.sync(time, delta);
        Night.sync(time, delta);
        Wearing.sync(time, delta);
        Npc.sync(time, delta);
        MineShaft.sync(time, delta);
    }
    
    internal static void load(IModHelper helper) {
        var data = helper.Data.ReadSaveData<SanityData>("DontStarve.Sanity");
        farmerSanity = data?.sanity ?? FARMER_MAX_SANITY;
        Monster.load(helper);
        Night.load(helper);
        Wearing.load(helper);
        Npc.load(helper);
        MineShaft.load(helper);
    }

    internal static void save(IModHelper helper) {
        helper.Data.WriteSaveData("DontStarve.Sanity", new SanityData {
            sanity = farmerSanity
        });
        Monster.save(helper);
        Night.save(helper);
        Wearing.save(helper);
        Npc.save(helper);
        MineShaft.save(helper);
    }

    public static double getMaxSanity(this Farmer _) => FARMER_MAX_SANITY;

    public static double getSanity(this Farmer _) {
        return farmerSanity;
    }

    public static void setSanity(this Farmer farmer, double value) {
        if (value < 0) {
            farmerSanity = 0;
        } else if (value > farmer.getMaxSanity()) {
            farmerSanity = farmer.getMaxSanity();
        } else {
            farmerSanity = value;
        }
    }
}

internal class SanityData {
    internal double? sanity { get; init; }
} 