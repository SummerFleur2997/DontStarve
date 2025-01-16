using System.Runtime.InteropServices;
using System.Text;
using Microsoft.Xna.Framework;

namespace DontStarve;

internal static class Util {
	internal static double distance(Vector2 a, Vector2 b) => Math.Sqrt(Math.Pow(a.X - b.X, 2) + Math.Pow(a.Y - b.Y, 2));

	internal static nint cstr(this string str) {
		var bytes = Encoding.UTF8.GetBytes(str);
		var ptr = Marshal.AllocHGlobal(bytes.Length + 1);
		Marshal.Copy(bytes, 0, ptr, bytes.Length);
		Marshal.WriteByte(ptr, bytes.Length, 0);
		return ptr;
	}

	internal static nint ptr(this object o) {
		return Marshal.GetIUnknownForObject(o);
	}
}