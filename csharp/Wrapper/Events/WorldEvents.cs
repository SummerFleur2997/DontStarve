using System.Runtime.InteropServices;

namespace DontStarve.Wrapper.Events;

public static class WorldEvents {
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onWorldEventsLocationListChanged(nint ptr, nint e);
	
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onWorldEventsBuildingListChanged(nint ptr, nint e);
	
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onWorldEventsDebrisListChanged(nint ptr, nint e);
	
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onWorldEventsLargeTerrainFeatureListChanged(nint ptr, nint e);
	
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onWorldEventsNpcListChanged(nint ptr, nint e);
	
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onWorldEventsObjectListChanged(nint ptr, nint e);
	
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onWorldEventsChestInventoryChanged(nint ptr, nint e);
	
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onWorldEventsTerrainFeatureListChanged(nint ptr, nint e);
	
	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	public static extern void onWorldEventsFurnitureListChanged(nint ptr, nint e);
}