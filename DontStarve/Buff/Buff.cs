using StardewModdingAPI;
using StardewModdingAPI.Events;

namespace DontStarve.Buff;

public class Buff {
    private IModHelper helper;
    private IMonitor monitor;
    private IManifest manifest;
    private readonly Health health = new();
    private readonly Stamina stamina = new();
    private readonly Sanity sanity = new();

    public Buff(IModHelper helper, IMonitor monitor, IManifest manifest) {
        this.helper = helper;
        this.monitor = monitor;
        this.manifest = manifest;
    }
    
    public void onOneSecondUpdateTicking(object? sender, OneSecondUpdateTickingEventArgs? e) {
        Electric.update();
    }

    public void onTimeChanging(object? sender, TimeChangedEventArgs e) {
        health.update(e);
        stamina.update(e);
        sanity.update(e);
    }
}