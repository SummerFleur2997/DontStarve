using StardewModdingAPI;
using StardewValley;

namespace DontStarve.Sanity;

internal static class Npc {
    private static Dictionary<string, double> npcSanity = null!;
    private static long lastTime;
    private static long deviation;
    
    internal static void init(IModHelper helper) {
        npcSanity = helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/villager.json");
    }
    
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
                value += npcSanity.GetValueOrDefault(villager.Name, 0);
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