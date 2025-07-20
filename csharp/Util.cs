using Microsoft.Xna.Framework;

namespace DontStarve;

internal static class Util {
	internal static double distance(Vector2 a, Vector2 b) => Math.Sqrt(Math.Pow(a.X - b.X, 2) + Math.Pow(a.Y - b.Y, 2));
}