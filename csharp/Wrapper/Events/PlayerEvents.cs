using System.Runtime.InteropServices;

namespace DontStarve.Wrapper.Events;

public static class PlayerEvents {
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onPlayerEventsInventoryChanged(nint ptr, nint e);
	
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onPlayerEventsLevelChanged(nint ptr, nint e);
	
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onPlayerEventsWarped(nint ptr, nint e);
}