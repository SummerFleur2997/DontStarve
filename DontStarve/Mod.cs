using StardewModdingAPI;

namespace DontStarve;

// ReSharper disable once UnusedType.Global
internal class Mod : StardewModdingAPI.Mod {
    public override void Entry(IModHelper helper) {
        Buff.Buff.init(helper);
        Sanity.Sanity.init(helper);
    }
}