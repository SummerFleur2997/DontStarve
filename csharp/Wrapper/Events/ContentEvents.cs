using System.Runtime.InteropServices;

namespace DontStarve.Wrapper.Events;

public static class ContentEvents {
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onContentEventsAssetRequested(nint ptr, nint e);
	
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onContentEventsAssetsInvalidated(nint ptr, nint e);
	
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onContentEventsAssetReady(nint ptr, nint e);
	
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onContentEventsLocaleChanged(nint ptr, nint e);
}