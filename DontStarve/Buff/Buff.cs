using StardewModdingAPI;
using StardewModdingAPI.Events;
using StardewValley;

namespace DontStarve.Buff;

internal static class Buff {
    public static void update(object? sender, OneSecondUpdateTickingEventArgs? e) {
        if (!Context.IsWorldReady || !Game1.shouldTimePass()) return;
        Electric.update();
        Health.update();
        Stamina.update();
        Sanity.update();
    }
}