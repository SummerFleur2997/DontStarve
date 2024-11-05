namespace DontStarve.Integration;

public interface TickTimeApi {
    public long time { get; }
    public List<Action<long>> onLoad { get; }
    public List<Action<long>> onUpdate { get; }
    public List<Action<long, long>> onSync { get; }
}