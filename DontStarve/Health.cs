using StardewModdingAPI;
using StardewModdingAPI.Events;
using StardewValley;

namespace DontStarve;

internal static class Health {
    private const string Buff = "DS_Heal_Health";

    public static void Update(OneSecondUpdateTickingEventArgs e) {
        if (!Context.IsWorldReady || !Game1.shouldTimePass()) return;

        var player = Game1.player;

        if (player.hasBuff(Buff)) {
            if (e.IsMultipleOf(120)) {
                if (player.health < player.maxHealth) {
                    player.health += Math.Min(2, player.maxHealth - player.health);
                }
            }
        }
    }
}