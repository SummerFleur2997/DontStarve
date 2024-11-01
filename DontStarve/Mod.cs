using StardewModdingAPI;

namespace DontStarve;

// ReSharper disable once UnusedType.Global
internal class Mod : StardewModdingAPI.Mod {
    public override void Entry(IModHelper helper) {
        Textures.loadTextures(helper.ModContent);
        Sanity.Sanity.init(Helper);

        Helper.Events.GameLoop.OneSecondUpdateTicking += Buff.Buff.update;
        Helper.Events.GameLoop.OneSecondUpdateTicking += Sanity.Sanity.update;
        Helper.Events.Display.RenderingHud += Hud.OnRenderingHud;
    }
}