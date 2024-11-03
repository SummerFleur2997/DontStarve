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
    private static long deviation;

    public static void init(IModHelper helper) {
        hatSanity = helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/hat.json");
        bootsSanity = helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/boots.json");
        ringSanity = helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/ring.json");
        shirtSanity = helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/shirt.json");
        pantsSanity = helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/pants.json");
        trinketSanity = helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/trinket.json");
    }

    public static void update(long time) {
        var player = Game1.player;
        
        if (deviation < 0) {
            deviation++;
            return;
        }
        
        var sanity = 0.0;
        
        var hat = player.hat.Value;
        if (hat != null) {
            if (hatSanity.TryGetValue(hat.ItemId, out var value)) {
                sanity += value;
            }
        }

        var shirt = player.shirtItem.Value;
        if (shirt != null) {
            if (shirtSanity.TryGetValue(shirt.ItemId, out var value)) {
                sanity += value;
            }
        }

        var pants = player.pantsItem.Value;
        if (pants != null) {
            if (pantsSanity.TryGetValue(pants.ItemId, out var value)) {
                sanity += value;
            }
        }

        var boots = player.boots.Value;
        if (boots != null) {
            if (bootsSanity.TryGetValue(boots.ItemId, out var value)) {
                sanity += value;
            }
        }

        var leftRing = player.leftRing.Value;
        if (leftRing != null) {
            if (ringSanity.TryGetValue(leftRing.ItemId, out var value)) {
                sanity += value;
            }
        }

        var rightRing = player.rightRing.Value;
        if (rightRing != null) {
            if (ringSanity.TryGetValue(rightRing.ItemId, out var value)) {
                sanity += value;
            }
        }

        var trinket = player.trinketItems.FirstOrDefault();
        if (trinket != null) {
            if (trinketSanity.TryGetValue(trinket.ItemId, out var value)) {
                sanity += value;
            }
        }
        
        player.setSanity(player.getSanity() + sanity * (1 + deviation));
    }
    
    public static void sync(long time, long delta) {
        deviation += delta;
        update(time);
    }
}