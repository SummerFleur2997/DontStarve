using StardewModdingAPI;
using StardewValley;

namespace DontStarve.Sanity;

public static class Wearing {
    private static Dictionary<string, double> hatSanity = null!;
    private static Dictionary<string, double> shirtSanity = null!;
    private static Dictionary<string, double> pantsSanity = null!;
    private static Dictionary<string, double> bootsSanity = null!;
    private static Dictionary<string, double> ringSanity = null!;
    private static Dictionary<string, double> trinketSanity = null!;
    
    private static int lastTime = Game1.timeOfDay;

    public static void init(IModHelper helper) {
        hatSanity = helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/hat.json");
        bootsSanity = helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/boots.json");
        ringSanity = helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/ring.json");
        shirtSanity = helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/shirt.json");
        pantsSanity = helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/pants.json");
        trinketSanity = helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/trinket.json");
    }
    
    public static void update() {
        var player = Game1.player;
        var delta = Game1.timeOfDay - lastTime;
        var hat = player.hat.Value;
        if (hat != null) {
            if (hatSanity.TryGetValue(hat.ItemId, out var hatValue)) {
                player.setSanity(player.getSanity() - hatValue * delta);
            }
        }

        var shirt = player.shirtItem.Value;
        if (shirt != null) {
            if (shirtSanity.TryGetValue(shirt.ItemId, out var shirtValue)) {
                player.setSanity(player.getSanity() - shirtValue * delta);
            }
        }

        var pants = player.pantsItem.Value;
        if (pants != null) {
            if (pantsSanity.TryGetValue(pants.ItemId, out var pantsValue)) {
                player.setSanity(player.getSanity() - pantsValue * delta);
            }
        }

        var boots = player.boots.Value;
        if (boots != null) {
            if (bootsSanity.TryGetValue(boots.ItemId, out var bootsValue)) {
                player.setSanity(player.getSanity() - bootsValue * delta);
            }
        }

        var leftRing = player.leftRing.Value;
        if (leftRing != null) {
            if (ringSanity.TryGetValue(leftRing.ItemId, out var leftRingValue)) {
                player.setSanity(player.getSanity() - leftRingValue * delta);
            }
        }

        var rightRing = player.rightRing.Value;
        if (rightRing != null) {
            if (ringSanity.TryGetValue(rightRing.ItemId, out var rightRingValue)) {
                player.setSanity(player.getSanity() - rightRingValue * delta);
            }
        }

        foreach (var (id, trinketValue) in trinketSanity) {
            if (player.hasTrinketWithID(id)) {
                player.setSanity(player.getSanity() - trinketValue * delta);
            }
        }
        
        lastTime = Game1.timeOfDay;
    }
}