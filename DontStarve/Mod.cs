using StardewModdingAPI;

namespace DontStarve;

// ReSharper disable once UnusedType.Global
internal class Mod : StardewModdingAPI.Mod {
    public override void Entry(IModHelper helper) {
        Textures.loadTextures(helper.ModContent);
        Sanity.Sanity.init(helper);

        helper.Events.GameLoop.GameLaunched += (_, _) => {
            var timeApi = helper.ModRegistry.GetApi<TickTimeApi>("Yurin.TickTime")!;
            timeApi.onUpdate.Add(Buff.Buff.update);
            timeApi.onSync.Add(Buff.Buff.sync);
            timeApi.onUpdate.Add(Sanity.Sanity.update);
            timeApi.onSync.Add(Sanity.Sanity.sync);
        };
        helper.Events.GameLoop.SaveLoaded += (_, _) => Sanity.Sanity.load(helper);
        helper.Events.GameLoop.Saving += (_, _) => Sanity.Sanity.save(helper);
        helper.Events.Display.RenderingHud += Hud.OnRenderingHud;
    }
}