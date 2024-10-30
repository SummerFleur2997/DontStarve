using StardewModdingAPI;
using StardewValley;

namespace DontStarve.Buff;

internal static class Electric {
    private const string BUFF = "DS_Electric";
    private static bool lastHasBuff;
    private static bool lastHasWeatherAddition;

    public static void update() {
        if (!Context.IsWorldReady || !Game1.shouldTimePass()) return;
        
        var player = Game1.player;
        
        var hasBuff = player.hasBuff(BUFF);
        if (hasBuff != lastHasBuff) {
            if (hasBuff) {
                player.buffs.GetValues().AttackMultiplier.Set(player.buffs.AttackMultiplier + 0.5F);
            } else {
                player.buffs.GetValues().AttackMultiplier.Set(player.buffs.AttackMultiplier - 0.5F);
                if (lastHasWeatherAddition) {
                    player.buffs.GetValues().AttackMultiplier.Set(player.buffs.AttackMultiplier - 1F);
                    lastHasWeatherAddition = false;
                }
            }
        }

        if (hasBuff) {
            var hasWeatherBuff = Game1.isRaining || Game1.isGreenRain || Game1.isLightning || Game1.isSnowing;
            if (hasWeatherBuff != lastHasWeatherAddition) {
                if (hasWeatherBuff) {
                    player.buffs.GetValues().AttackMultiplier.Set(player.buffs.AttackMultiplier + 1F);
                } else {
                    player.buffs.GetValues().AttackMultiplier.Set(player.buffs.AttackMultiplier - 1F);
                }
            }

            lastHasWeatherAddition = hasWeatherBuff;
        }

        lastHasBuff = hasBuff;
    }
}