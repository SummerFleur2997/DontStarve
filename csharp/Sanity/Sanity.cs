using DontStarve.Integration;
using StardewModdingAPI;
using StardewModdingAPI.Events;
using StardewValley;

namespace DontStarve.Sanity;

public static class Sanity
{
    private const double FARMER_MAX_SANITY = 150;
    private static double farmerSanity;

    internal static void init(IModHelper helper)
    {
        EatFood.init(helper);
        NearMonster.init(helper);
        Wearing.init(helper);

        helper.Events.GameLoop.GameLaunched += (_, _) =>
        {
            var timeApi = helper.ModRegistry.GetApi<TimeApi>("Yurin.MinuteTimeHelper")!;
            timeApi.onUpdate.Add(update);
            timeApi.onSync.Add(sync);
        };

        helper.Events.GameLoop.SaveLoaded += (_, _) => load(helper);
        helper.Events.GameLoop.Saving += (_, _) => save(helper);
        helper.Events.GameLoop.TimeChanged += (_, e) => timeChange(e);
        helper.Events.GameLoop.DayEnding += (_, _) => dayEnding();
    }

    private static void update(long time)
    {
        NearMonster.update(time);
        Night.update(time);
        Wearing.update(time);
        NearNpc.update(time);
        MineShaft.update(time);
        SpawnMrSkitts.update(time);
        SpawnDarkHand.update(time);
        SpawnDarkWatcher.update(time);
        SpawnEye.update(time);
        SpawnCreeperFear.update(time);
        SpawnTerrifyingSharpBeak.update(time);
    }

    private static void sync(long time, long delta)
    {
        NearMonster.sync(time, delta);
        Night.sync(time, delta);
        Wearing.sync(time, delta);
        NearNpc.sync(time, delta);
        MineShaft.sync(time, delta);
        SpawnMrSkitts.sync(time, delta);
        SpawnDarkHand.sync(time, delta);
        SpawnDarkWatcher.sync(time, delta);
        SpawnEye.sync(time, delta);
        SpawnCreeperFear.sync(time, delta);
        SpawnTerrifyingSharpBeak.sync(time, delta);
    }

    private static void load(IModHelper helper)
    {
        var data = helper.Data.ReadSaveData<SanityData>("DontStarve.Sanity");
        farmerSanity = data?.sanity ?? FARMER_MAX_SANITY;
        NearMonster.load(helper);
        Night.load(helper);
        Wearing.load(helper);
        NearNpc.load(helper);
        MineShaft.load(helper);
        SpawnMrSkitts.load(helper);
        SpawnDarkHand.load(helper);
        SpawnDarkWatcher.load(helper);
        SpawnEye.load(helper);
        SpawnCreeperFear.load(helper);
        SpawnTerrifyingSharpBeak.load(helper);
    }

    private static void save(IModHelper helper)
    {
        helper.Data.WriteSaveData("DontStarve.Sanity", new SanityData
        {
            sanity = farmerSanity
        });
        NearMonster.save(helper);
        Night.save(helper);
        Wearing.save(helper);
        NearNpc.save(helper);
        MineShaft.save(helper);
        SpawnMrSkitts.save(helper);
        SpawnDarkHand.save(helper);
        SpawnDarkWatcher.save(helper);
        SpawnEye.save(helper);
        SpawnCreeperFear.save(helper);
        SpawnTerrifyingSharpBeak.save(helper);
    }

    private static void timeChange(TimeChangedEventArgs e)
    {
        Sleep.timeChange(e);
    }

    private static void dayEnding()
    {
        Sleep.dayEnding();
    }

    public static double getMaxSanity(this Farmer _)
    {
        return FARMER_MAX_SANITY;
    }

    public static double getSanity(this Farmer _)
    {
        return farmerSanity;
    }

    public static void setSanity(this Farmer farmer, double value)
    {
        if (value < 0)
            farmerSanity = 0;
        else if (value > farmer.getMaxSanity())
            farmerSanity = farmer.getMaxSanity();
        else
            farmerSanity = value;
    }
}

internal class SanityData
{
    internal double? sanity { get; init; }
}