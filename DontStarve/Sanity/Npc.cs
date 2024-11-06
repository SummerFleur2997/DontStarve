using StardewModdingAPI;
using StardewValley;
using StardewValley.Characters;

namespace DontStarve.Sanity;

internal static class Npc {
    private static long lastTime;
    private static long deviation;

    internal static void update(long time) {
        if (deviation < 0) {
            deviation++;
            return;
        }

        var player = Game1.player;
        var location = Game1.currentLocation;
        var playerPosition = player.Position;
        var value = 0.0;
        foreach (var villager in location.characters.Where(npc => npc.IsVillager)) {
            var villagerPosition = villager.Position;
            var distance = Math.Sqrt(
                Math.Pow(villagerPosition.X - playerPosition.X, 2) +
                Math.Pow(villagerPosition.Y - playerPosition.Y, 2)
            );
            var percentage = 1 - distance / 10;
            if (percentage > 0) {
                if (villager.Name == player.spouse) {
                    value += 0.028 * percentage;
                } else {
                    var level = player.getFriendshipHeartLevelForNPC(villager.Name);
                    if (level >= 8) {
                        value += 0.014 * percentage;
                    } else if (level >= 5) {
                        value += 0.007 * percentage;
                    }
                }
            }
        }

        foreach (var npc in location.characters.Where(npc => npc is Child or Pet)) {
            var villagerPosition = npc.Position;
            var distance = Math.Sqrt(
                Math.Pow(villagerPosition.X - playerPosition.X, 2) +
                Math.Pow(villagerPosition.Y - playerPosition.Y, 2)
            );
            var percentage = 1 - distance / 10;
            if (percentage > 0) {
                var level = player.getFriendshipHeartLevelForNPC(npc.Name);
                if (level == 5) {
                    value += 0.014 * percentage;
                } else if (level >= 3) {
                    value += 0.007 * percentage;
                } else {
                    value += 0.0035 * percentage;
                }
            }
        }

        foreach (var npc in location.characters.Where(npc => npc is Junimo or JunimoHarvester)) {
            var villagerPosition = npc.Position;
            var distance = Math.Sqrt(
                Math.Pow(villagerPosition.X - playerPosition.X, 2) +
                Math.Pow(villagerPosition.Y - playerPosition.Y, 2)
            );
            var percentage = 1 - distance / 10;
            if (percentage > 0) {
                value += 0.007;
            }
        }

        if (value > 0) {
            player.setSanity(player.getSanity() + value);
        }

        lastTime = time;
    }

    internal static void sync(long time, long delta) {
        deviation += delta;
        update(time);
    }

    internal static void load(IModHelper helper) {
        var data = helper.Data.ReadSaveData<NpcData>("DontStarve.Sanity.Npc");
        lastTime = data?.lastTime ?? 0;
        deviation = data?.deviation ?? 0;
    }

    internal static void save(IModHelper helper) {
        helper.Data.WriteSaveData("DontStarve.Sanity.Npc", new NpcData {
            lastTime = lastTime,
            deviation = deviation
        });
    }
}

internal class NpcData {
    internal long lastTime { get; init; }
    internal long deviation { get; init; }
}