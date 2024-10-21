using StardewModdingAPI;
using StardewModdingAPI.Events;
using StardewValley;

namespace DontStarve;

// ReSharper disable once UnusedType.Global
internal class Mod : StardewModdingAPI.Mod {
    public override void Entry(IModHelper helper) {
        Helper.Events.GameLoop.OneSecondUpdateTicking += (_, _) => Electric.Update();
        Helper.Events.GameLoop.OneSecondUpdateTicking += (_, e) => Health.Update(e);
        Helper.Events.GameLoop.OneSecondUpdateTicking += (_, e) => Stamina.Update(e);
    }
}