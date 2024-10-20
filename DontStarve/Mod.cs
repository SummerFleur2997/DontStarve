using StardewModdingAPI;
using StardewModdingAPI.Events;
using StardewValley;

namespace DontStarve;

// ReSharper disable once UnusedType.Global
internal class Mod : StardewModdingAPI.Mod {
    private const string Buff = "BUFF";
    private bool _lastHasBuff;

    public override void Entry(IModHelper helper) {
        Helper.Events.GameLoop.OneSecondUpdateTicking += OneSecondUpdateTickingForBuffEvent;
    }

    private void OneSecondUpdateTickingForBuffEvent(object? sender, OneSecondUpdateTickingEventArgs e) {
        if (!Context.IsWorldReady || !Game1.shouldTimePass()) return;

        var player = Game1.player;
        var hasBuff = player.hasBuff(Buff);

        if (hasBuff != _lastHasBuff) {
            if (hasBuff)
                player.buffs.GetValues().AttackMultiplier.Set(player.buffs.AttackMultiplier + 0.5F);
            else
                player.buffs.GetValues().AttackMultiplier.Set(player.buffs.AttackMultiplier - 0.5F);
        }

        _lastHasBuff = hasBuff;
    }
}