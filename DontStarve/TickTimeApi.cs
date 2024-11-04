namespace DontStarve;

internal interface TickTimeApi {
    internal long time { get; }
    internal List<Action<long>> onLoad { get; }
    internal List<Action<long>> onUpdate { get; }
    internal List<Action<long, long>> onSync { get; }
}