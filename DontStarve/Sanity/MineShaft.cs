using StardewValley;
using StardewValley.Locations;

namespace DontStarve.Sanity;

public static class MineShaft {
    private static int lastTime = Game1.timeOfDay;
    
    public static void update() {
        var player = Game1.player;
        var location = Game1.currentLocation;
        var value = 0.0;
        if (location is StardewValley.Locations.MineShaft mineShaft) {
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
        else if (location is VolcanoDungeon) {
            value = 0.07;
        }
        var delta = Game1.timeOfDay - lastTime;
        player.setSanity(player.getSanity() - value * delta);
        lastTime = Game1.timeOfDay;
    } 
}