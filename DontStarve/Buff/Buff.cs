using StardewModdingAPI;

namespace DontStarve.Buff;

internal static class Buff {
    internal static void update(long time) {
        Electric.update(time);
        Health.update(time);
        Stamina.update(time);
        Sanity.update(time);
    }

    internal static void sync(long time, long delta) {
        Health.sync(time, delta);
        Stamina.sync(time, delta);
        Sanity.sync(time, delta);
    }
    
    internal static void load(IModHelper helper) {
        Health.load(helper);
        Stamina.load(helper);
        Sanity.load(helper);
    }

    internal static void save(IModHelper helper) {
        Health.save(helper);
        Stamina.save(helper);
        Sanity.save(helper);
    }
}