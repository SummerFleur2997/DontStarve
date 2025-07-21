namespace DontStarve.Integration;

public interface TimeApi {
	public long time { get; }
	public IList<Action<long>> onLoad { get; }
	public IList<Action<long>> onUpdate { get; }
	public IList<Action<long, long>> onSync { get; }
}