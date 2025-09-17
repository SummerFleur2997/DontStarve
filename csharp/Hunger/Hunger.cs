﻿using DontStarve.Integration;
using StardewModdingAPI;
using StardewValley;

namespace DontStarve.Hunger;

public static class Hunger
{
    private const float FARMER_MAX_HUNGER = 150;
    private static float farmerHunger;

    internal static void init(IModHelper helper)
    {
        EatFood.init(helper);

        helper.Events.GameLoop.GameLaunched += (_, _) =>
        {
            var timeApi = helper.ModRegistry.GetApi<TimeApi>("Yurin.MinuteTimeHelper")!;
            timeApi.onUpdate.Add(update);
            timeApi.onSync.Add(sync);
        };

        helper.Events.GameLoop.SaveLoaded += (_, _) => load(helper);
        helper.Events.GameLoop.Saving += (_, _) => save(helper);
    }

    private static void update(long time)
    {
        TimeCycle.update(time);
    }

    private static void sync(long time, long delta)
    {
        TimeCycle.sync(time, delta);
    }

    private static void load(IModHelper helper)
    {
        var data = helper.Data.ReadSaveData<HungerData>("DontStarve.Hunger");
        farmerHunger = data?.hunger ?? FARMER_MAX_HUNGER;
        TimeCycle.load(helper);
    }

    private static void save(IModHelper helper)
    {
        helper.Data.WriteSaveData("DontStarve.Hunger", new HungerData
        {
            hunger = farmerHunger
        });
        TimeCycle.save(helper);
    }

    public static float getMaxHunger(this Farmer _)
    {
        return FARMER_MAX_HUNGER;
    }

    public static float getHunger(this Farmer _)
    {
        return farmerHunger;
    }

    public static void setHunger(this Farmer farmer, float value)
    {
        if (value < 0)
            farmerHunger = 0;
        else if (value > farmer.getMaxHunger())
            farmerHunger = farmer.getMaxHunger();
        else
            farmerHunger = value;
    }
}

internal class HungerData
{
    internal float? hunger { get; init; }
}