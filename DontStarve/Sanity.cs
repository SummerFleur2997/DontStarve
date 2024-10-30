using StardewModdingAPI;
using StardewModdingAPI.Events;
using StardewValley;
using StardewValley.Locations;

namespace DontStarve;

public static class Sanity {
    public const double FARMER_MAX_SANITY = 150;
    public static Dictionary<Farmer, double> farmerSanity;
    public static Dictionary<string, double> foodSanity;
    public static Dictionary<string, double> monsterSanity;
    public static Dictionary<string, double> hatSanity;
    public static Dictionary<string, double> shirtSanity;
    public static Dictionary<string, double> pantsSanity;
    public static Dictionary<string, double> bootsSanity;
    public static Dictionary<string, double> ringSanity;
    public static Dictionary<string, double> trinketSanity;

    public static void onTimeChanging(IModHelper helper, TimeChangedEventArgs e) {
        monster(helper, e);
        night(helper, e);
        wearing(helper, e);
        npc(helper, e);
        mineShaft(helper, e);
    }

    private static void monster(IModHelper helper, TimeChangedEventArgs e) { }

    private static void night(IModHelper helper, TimeChangedEventArgs e) {
        var player = Game1.player;
        var time = e.NewTime;
        var delta = time - e.OldTime;
        if (time > 2400) {
            player.setSanity(player.getSanity() - delta);
        } else {
            switch (Game1.season) {
                case Season.Spring when time > 2000:
                case Season.Summer when time > 2000:
                case Season.Fall when time > 1900:
                case Season.Winter when time > 1800:
                    player.setSanity(player.getSanity() - delta);
                    break;
            }
        }
    }

    private static void wearing(IModHelper helper, TimeChangedEventArgs e) {
        var player = Game1.player;
        var delta = e.NewTime - e.OldTime;
        if (hatSanity.TryGetValue(player.hat.Value.ItemId, out var hatValue)) {
            player.setSanity(player.getSanity() - hatValue * delta);
        }
        if (shirtSanity.TryGetValue(player.shirtItem.Value.ItemId, out var shirtValue)) {
            player.setSanity(player.getSanity() - shirtValue * delta);
        }
        if (pantsSanity.TryGetValue(player.pantsItem.Value.ItemId, out var pantsValue)) {
            player.setSanity(player.getSanity() - pantsValue * delta);
        }
        if (bootsSanity.TryGetValue(player.boots.Value.ItemId, out var bootsValue)) {
            player.setSanity(player.getSanity() - bootsValue * delta);
        }
        if (ringSanity.TryGetValue(player.leftRing.Value.ItemId, out var leftRingValue)) {
            player.setSanity(player.getSanity() - leftRingValue * delta);
        }
        if (ringSanity.TryGetValue(player.rightRing.Value.ItemId, out var rightRingValue)) {
            player.setSanity(player.getSanity() - rightRingValue * delta);
        }
        foreach (var (id, trinketValue) in trinketSanity) {
            if (player.hasTrinketWithID(id)) {
                player.setSanity(player.getSanity() - trinketValue * delta);
            }
        }
    }

    private static void npc(IModHelper helper, TimeChangedEventArgs e) { }

    private static void mineShaft(IModHelper helper, TimeChangedEventArgs e) {
        var player = Game1.player;
        var location = Game1.currentLocation;
        var value = 0.0;
        if (location is MineShaft mineShaft) {
            // 矿井
            value = 0.035;
            // 黑暗层
            if (mineShaft.isDarkArea()) {
                value = 0.35;
            }
            // 骷髅矿井
            if (mineShaft.mineLevel > 120) {
                value = 0.07;
            }
            // 骷髅矿井 880 层以上
            if (mineShaft.mineLevel > 1000) {
                value = 0.14;
            }
            // 采石场矿井
            if (mineShaft.isQuarryArea) {
                value = 0.105;
            }
            // 感染层
            if (mineShaft.isSlimeArea) {
                value += 0.07;
            }
            // 地牢层
            if (mineShaft.isMonsterArea) {
                value += 0.14;
            }
            // 史前层
            if (mineShaft.isDinoArea) {
                value += 0.14;
            }
            // 危险矿井
            if (mineShaft.GetAdditionalDifficulty() > 0) {
                value += 0.07;
            }
        }
        // 火山矿井
        if (location is VolcanoDungeon) {
            value = 0.07;
        }
        var delta = e.NewTime - e.OldTime;
        player.setSanity(player.getSanity() - value * delta);
    }
    
    public static double getMaxSanity(this Farmer _) => FARMER_MAX_SANITY;

    public static double getSanity(this Farmer farmer) {
        if (farmerSanity.TryGetValue(farmer, out var data)) {
            return data;
        }

        var max = farmer.getMaxSanity();
        farmerSanity[farmer] = max;
        return max;
    }

    public static void setSanity(this Farmer farmer, double value) {
        if (value < 0) {
            farmerSanity[farmer] = 0;
        } else if (value > farmer.getMaxSanity()) {
            farmerSanity[farmer] = farmer.getMaxSanity();
        } else {
            farmerSanity[farmer] = value;
        }
    }
}