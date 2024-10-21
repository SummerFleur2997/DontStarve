using StardewModdingAPI;
using StardewValley;

namespace DontStarve;

internal static class Electric {
    private const string Buff = "Electric";
    private static bool _lastHasBuff;
    private static bool _lastHasWeatherAddition;

    public static void Update() {
        if (!Context.IsWorldReady || !Game1.shouldTimePass()) return;
        
        var player = Game1.player;
        
        var hasBuff = player.hasBuff(Buff);
        if (hasBuff != _lastHasBuff) {
            if (hasBuff) {
                player.buffs.GetValues().AttackMultiplier.Set(player.buffs.AttackMultiplier + 0.5F);
            } else {
                player.buffs.GetValues().AttackMultiplier.Set(player.buffs.AttackMultiplier - 0.5F);
                if (_lastHasWeatherAddition) {
                    player.buffs.GetValues().AttackMultiplier.Set(player.buffs.AttackMultiplier - 1F);
                    _lastHasWeatherAddition = false;
                }
            }
        }

        if (hasBuff) {
            var hasWeatherBuff = Game1.isRaining || Game1.isGreenRain || Game1.isLightning || Game1.isSnowing;
            if (hasWeatherBuff != _lastHasWeatherAddition) {
                if (hasWeatherBuff) {
                    player.buffs.GetValues().AttackMultiplier.Set(player.buffs.AttackMultiplier + 1F);
                } else {
                    player.buffs.GetValues().AttackMultiplier.Set(player.buffs.AttackMultiplier - 1F);
                }
            }

            _lastHasWeatherAddition = hasWeatherBuff;
        }

        _lastHasBuff = hasBuff;
    }
}