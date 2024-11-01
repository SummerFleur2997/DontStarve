using StardewValley;

namespace DontStarve.Sanity;

public static class Night {
    private static int lastTime = Game1.timeOfDay;
    
    public static void update() {
        var player = Game1.player;
        var time = Game1.timeOfDay;
        var delta = time - lastTime;
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
        
        lastTime = Game1.timeOfDay;
    }
}