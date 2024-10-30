using StardewModdingAPI.Events;
using StardewValley;

namespace DontStarve.Buff;

internal class Stamina {
    private const string BUFF = "DS_Heal_Stamina";
    private bool lastHasBuff;
    private int lastTime = Game1.timeOfDay;

    public void update(TimeChangedEventArgs e) {
        var player = Game1.player;
        var hasBuff = player.hasBuff(BUFF);

        if (hasBuff) {
            if (hasBuff != lastHasBuff) {
                lastTime = Game1.timeOfDay;
            }

            var time = e.NewTime;
            if (time - lastTime >= 5) {
                var times = (time - lastTime) / 5;
                if (player.Stamina < player.MaxStamina) {
                    player.Stamina += Math.Min(times, player.MaxStamina - player.Stamina);
                }

                lastTime += times * 5;
            }
        }

        lastHasBuff = hasBuff;
    }
}