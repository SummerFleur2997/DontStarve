using kotlin;

namespace DontStarve.Integration;

public interface TimeApi {
	public long time { get; }
	public IList<Func<long, Unit>> onLoad { get; }
	public IList<Func<long, Unit>> onUpdate { get; }
	public IList<Func<long, long, Unit>> onSync { get; }
}