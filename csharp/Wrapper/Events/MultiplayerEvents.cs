using System.Runtime.InteropServices;

namespace DontStarve.Wrapper.Events;

public static class MultiplayerEvents {
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onMultiplayerEventsPeerContextReceived(nint ptr, nint e);
	
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onMultiplayerEventsPeerConnected(nint ptr, nint e);
	
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onMultiplayerEventsModMessageReceived(nint ptr, nint e);
	
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onMultiplayerEventsPeerDisconnected(nint ptr, nint e);
}