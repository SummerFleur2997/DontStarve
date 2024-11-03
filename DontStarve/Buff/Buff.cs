namespace DontStarve.Buff;

internal static class Buff {
    public static void update(long time) {
        Electric.update();
        Health.update(time);
        Stamina.update(time);
        Sanity.update(time);
    }

    public static void sync(long time, long delta) {
        Health.sync(time, delta);
        Stamina.sync(time, delta);
        Sanity.sync(time, delta);
    }
}