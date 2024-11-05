using DontStarve.Integration;
using StardewModdingAPI;
using StardewModdingAPI.Events;
using StardewValley;

namespace DontStarve.Sanity;

public static class Sanity {
    private const double FARMER_MAX_SANITY = 150;
    private static double farmerSanity;

    internal static void init(IModHelper helper) {
        Textures.loadTextures(helper.ModContent);
        
        Food.init(helper);
        Monster.init(helper);
        Wearing.init(helper);
        Npc.init(helper);

        helper.Events.GameLoop.GameLaunched += (_, _) => {
            var timeApi = helper.ModRegistry.GetApi<TickTimeApi>("Yurin.TickTimeHelper")!;
            timeApi.onUpdate.Add(update);
            timeApi.onSync.Add(sync);
        };

        helper.Events.GameLoop.SaveLoaded += (_, _) => load(helper);
        helper.Events.GameLoop.Saving += (_, _) => save(helper);
        helper.Events.GameLoop.TimeChanged += (_, e) => timeChange(e);
        helper.Events.GameLoop.DayEnding += (_, _) => dayEnding();
        helper.Events.Display.RenderingHud += (_, e) => Hud.OnRenderingHud(e);
    }

    private static void update(long time) {
        Food.update(time);
        Monster.update(time);
        Night.update(time);
        Wearing.update(time);
        Npc.update(time);
        MineShaft.update(time);
    }

    private static void sync(long time, long delta) {
        Monster.sync(time, delta);
        Night.sync(time, delta);
        Wearing.sync(time, delta);
        Npc.sync(time, delta);
        MineShaft.sync(time, delta);
    }

    private static void load(IModHelper helper) {
        var data = helper.Data.ReadSaveData<SanityData>("DontStarve.Sanity");
        farmerSanity = data?.sanity ?? FARMER_MAX_SANITY;
        Monster.load(helper);
        Night.load(helper);
        Wearing.load(helper);
        Npc.load(helper);
        MineShaft.load(helper);
    }

    private static void save(IModHelper helper) {
        helper.Data.WriteSaveData("DontStarve.Sanity", new SanityData {
            sanity = farmerSanity
        });
        Monster.save(helper);
        Night.save(helper);
        Wearing.save(helper);
        Npc.save(helper);
        MineShaft.save(helper);
    }

    private static void timeChange(TimeChangedEventArgs e) {
        Sleep.timeChange(e);
    }

    private static void dayEnding() {
        Sleep.dayEnding();
    }

    public static double getMaxSanity(this Farmer _) => FARMER_MAX_SANITY;

    public static double getSanity(this Farmer _) {
        return farmerSanity;
    }

    public static void setSanity(this Farmer farmer, double value) {
        if (value < 0) {
            farmerSanity = 0;
        } else if (value > farmer.getMaxSanity()) {
            farmerSanity = farmer.getMaxSanity();
        } else {
            farmerSanity = value;
        }
    }
}

internal class SanityData {
    internal double? sanity { get; init; }
}