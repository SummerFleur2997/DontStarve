using StardewModdingAPI;
using StardewModdingAPI.Events;
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

    public static void load(IModHelper helper) {
        farmerSanity = helper.Data.ReadSaveData<SanityData>("sanity")?.sanity ?? FARMER_MAX_SANITY;
    }

    public static void save(IModHelper helper) {
        helper.Data.WriteSaveData("sanity", new SanityData {
            sanity = farmerSanity
        });
    }
}

public sealed class SanityData {
    public double sanity { get; init; }
}