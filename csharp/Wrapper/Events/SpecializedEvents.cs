using System.Runtime.InteropServices;

namespace DontStarve.Wrapper.Events;

public static class SpecializedEvents {
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onSpecializedEventsLoadStageChanged(nint ptr, nint e);
	
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onSpecializedEventsUnvalidatedUpdateTicking(nint ptr, nint e);
	
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onSpecializedEventsUnvalidatedUpdateTicked(nint ptr, nint e);
}