using StardewValley;

namespace DontStarve.Sanity;

public static class Npc {
    private static long lastTime;
    private static long deviation;

    public static void update(long time) {
        var player = Game1.player;
        
        if (deviation < 0) {
            deviation++;
            return;
        }
        
        lastTime = time;
    }

    public static void sync(long time, long delta) {
        deviation += delta;
        update(time);
    }
}