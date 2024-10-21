using StardewModdingAPI;
using StardewModdingAPI.Events;
using StardewValley;

namespace DontStarve;

internal static class Stamina {
    private const string Buff = "DS_Heal_Stamina";

    public static void Update(OneSecondUpdateTickingEventArgs e) {
        if (!Context.IsWorldReady || !Game1.shouldTimePass()) return;

        var player = Game1.player;

        if (player.hasBuff(Buff)) {
            if (e.IsMultipleOf(120)) {
                if (player.Stamina < player.MaxStamina) {
                    player.Stamina += Math.Min(2, player.MaxStamina - player.Stamina);
                }
            }
        }
    }
}