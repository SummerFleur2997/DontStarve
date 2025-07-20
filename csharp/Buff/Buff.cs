using DontStarve.Integration;
using kotlin;
using StardewModdingAPI;

namespace DontStarve.Buff;

internal static class Buff {
	internal static void init(IModHelper helper) {
		Electric.init(helper);

		helper.Events.GameLoop.GameLaunched += (_, _) => {
			var timeApi = helper.ModRegistry.GetApi<TimeApi>("Yurin.MinuteTimeHelper")!;
			timeApi.onUpdate.Add(update);
			timeApi.onSync.Add(sync);
		};

		helper.Events.GameLoop.SaveLoaded += (_, _) => load(helper);
		helper.Events.GameLoop.Saving += (_, _) => save(helper);
	}

	private static Unit update(long time) {
		Health.update(time);
		Stamina.update(time);
		Sanity.update(time);
		return Unit.INSTANCE;
	}

	private static Unit sync(long time, long delta) {
		Health.sync(time, delta);
		Stamina.sync(time, delta);
		Sanity.sync(time, delta);
		return Unit.INSTANCE;
	}

	private static void load(IModHelper helper) {
		Health.load(helper);
		Stamina.load(helper);
		Sanity.load(helper);
	}

	private static void save(IModHelper helper) {
		Health.save(helper);
		Stamina.save(helper);
		Sanity.save(helper);
	}
}