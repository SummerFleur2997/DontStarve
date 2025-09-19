using DontStarve.Player.Hunger;
using DontStarve.Player.Sanity;
using StardewModdingAPI;

namespace DontStarve;

// ReSharper disable once UnusedType.Global
internal class ModEntry : Mod
{
    public override void Entry(IModHelper helper)
    {
        Textures.loadTextures(helper.ModContent);
        Buff.Buff.init(helper);
        Sanity.init(helper);
        Hunger.init(helper);

        helper.Events.Display.RenderingHud += (_, e) => Hud.OnRenderingHud(helper, e);
    }
}