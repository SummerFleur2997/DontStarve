using StardewModdingAPI;
using StardewValley;

namespace DontStarve.Sanity;

internal static class EatFood {
	public static Dictionary<string, double> foodSanity { get; private set; } = null!;
	private static Item? lastFood;
	private static bool lastEating;

	internal static void init(IModHelper helper) {
		foodSanity = helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/food.json");
		helper.Events.GameLoop.UpdateTicking += (_, _) => update();
	}

	private static void update() {
		var player = Game1.player;
		var isEating = player.isEating;
		if (!isEating && lastEating) {
			var sanity = foodSanity.GetValueOrDefault(lastFood!.ItemId, 0);
			player.setSanity(player.getSanity() + sanity);
		}

		lastFood = player.itemToEat;
		lastEating = player.isEating;
	}
}