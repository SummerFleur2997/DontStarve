using StardewModdingAPI;
using StardewModdingAPI.Events;
using StardewValley;

namespace DontStarve.Sanity;

public static class Sanity {
    private const double FARMER_MAX_SANITY = 150;
    private static Dictionary<Farmer, double> farmerSanity = null!;
    private static Dictionary<string, double> foodSanity = null!;
    private static Dictionary<string, double> monsterSanity = null!;

    public static void init(IModHelper helper) {
        farmerSanity = new Dictionary<Farmer, double>();
        foodSanity = helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/food.json");
        monsterSanity = helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/monster.json");
        Wearing.init(helper);
    }

    public static void update(object? sender, OneSecondUpdateTickingEventArgs e) {
        if (!Context.IsWorldReady || !Game1.shouldTimePass()) return;
        Monster.update();
        Night.update();
        Wearing.update();
        Npc.update();
        MineShaft.update();
    }

    public static double getMaxSanity(this Farmer _) => FARMER_MAX_SANITY;

    public static double getSanity(this Farmer farmer) {
        if (farmerSanity.TryGetValue(farmer, out var data)) {
            return data;
        }

        var max = farmer.getMaxSanity();
        farmerSanity[farmer] = max;
        return max;
    }

    public static void setSanity(this Farmer farmer, double value) {
        if (value < 0) {
            farmerSanity[farmer] = 0;
        } else if (value > farmer.getMaxSanity()) {
            farmerSanity[farmer] = farmer.getMaxSanity();
        } else {
            farmerSanity[farmer] = value;
        }
    }
}