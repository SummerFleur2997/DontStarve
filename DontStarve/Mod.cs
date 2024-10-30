using StardewModdingAPI;

namespace DontStarve;

// ReSharper disable once UnusedType.Global
internal class Mod : StardewModdingAPI.Mod {
    public override void Entry(IModHelper helper) {
        var buff = new Buff.Buff(
            helper: Helper,
            monitor: Monitor,
            manifest: ModManifest
        );
        
        Sanity.foodSanity = Helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/food.json");
        Sanity.monsterSanity = Helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/monster.json");
        Sanity.hatSanity = Helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/hat.json");
        Sanity.bootsSanity = Helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/boots.json");
        Sanity.ringSanity = Helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/ring.json");
        Sanity.shirtSanity = Helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/shirt.json");
        Sanity.pantsSanity = Helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/pants.json");
        Sanity.trinketSanity = Helper.ModContent.Load<Dictionary<string, double>>("assets/sanity/trinket.json");

        Helper.Events.GameLoop.OneSecondUpdateTicking += (sender, e) => buff.onOneSecondUpdateTicking(sender, e);
        Helper.Events.GameLoop.TimeChanged += (sender, e) => buff.onTimeChanging(sender, e);
        Helper.Events.GameLoop.TimeChanged += (_, e) => Sanity.onTimeChanging(Helper, e);
    }
}