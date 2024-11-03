using StardewModdingAPI;
using StardewValley;

namespace DontStarve.Sanity;

public static class Sanity {
    private const double FARMER_MAX_SANITY = 150;
    private static double farmerSanity;
    private static Dictionary<string, double> foodSanity = null!;
    private static Dictionary<string, double> monsterSanity = null!;

    public static void init(IModHelper helper) {
        foodSanity = helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/food.json");
        monsterSanity = helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/monster.json");
        Wearing.init(helper);
    }

    public static void update(long time) {
        Monster.update(time);
        Night.update(time);
        Wearing.update(time);
        Npc.update(time);
        MineShaft.update(time);
    }

    public static void sync(long time, long delta) {
        Monster.sync(time, delta);
        Night.sync(time, delta);
        Wearing.sync(time, delta);
        Npc.sync(time, delta);
        MineShaft.sync(time, delta);
    }
    
    public static void load(IModHelper helper) {
        var data = helper.Data.ReadSaveData<SanityData>("DontStarve.Sanity");
        farmerSanity = data?.sanity ?? FARMER_MAX_SANITY;
        Monster.load(helper);
        Night.load(helper);
        Wearing.load(helper);
        Npc.load(helper);
        MineShaft.load(helper);
    }

    public static void save(IModHelper helper) {
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