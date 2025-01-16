using System.Runtime.InteropServices;

namespace DontStarve.Wrapper.Events;

public static class InputEvents {
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onInputEventsButtonsChanges(nint ptr, nint e);
	
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onInputEventsButtonPressed(nint ptr, nint e);
	
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onInputEventsButtonReleased(nint ptr, nint e);
	
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onInputEventsCursorMoved(nint ptr, nint e);
	
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onInputEventsMouseWheelScrolled(nint ptr, nint e);
}