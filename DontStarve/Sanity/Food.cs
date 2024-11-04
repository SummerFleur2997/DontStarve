using StardewModdingAPI;
using StardewValley;

namespace DontStarve.Sanity;

internal static class Food {
    private static Dictionary<string, double> foodSanity = null!;
    private static Item? lastFood;
    private static bool lastEating;
    
    internal static void init(IModHelper helper) {
        foodSanity = helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/food.json");
    }

    internal static void update(double _) {
        var player = Game1.player;
        if (player.isEating) {
            lastFood = player.itemToEat;
            lastEating = true;
        } else if (lastEating) {
            lastEating = false;
            var sanity = foodSanity.GetValueOrDefault(lastFood!.ItemId, 0);
            player.setSanity(player.getSanity() + sanity);
        }
    }
}