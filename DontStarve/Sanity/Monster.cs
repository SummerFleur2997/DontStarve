using StardewValley;

namespace DontStarve.Sanity;

public static class Monster {
    private static int lastTime = Game1.timeOfDay;

    public static void update() {
        
        lastTime = Game1.timeOfDay;
    }
}