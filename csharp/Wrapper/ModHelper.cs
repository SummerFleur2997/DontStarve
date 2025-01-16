using System.Runtime.InteropServices;
using StardewModdingAPI;

namespace DontStarve.Wrapper;

public static class ModHelper {
	private delegate nint DirectoryPath(nint @this);
	
	private delegate nint ConsoleCommands(nint @this);

	private delegate nint GameContent(nint @this);

	private delegate nint ModContent(nint @this);

	private delegate nint ContentPacks(nint @this);

	private delegate nint Data(nint @this);

	private delegate nint Input(nint @this);

	private delegate nint Reflection(nint @this);

	private delegate nint ModRegistry(nint @this);

	private delegate nint Multiplayer(nint @this);

	private delegate nint Translation(nint @this);

	public static unsafe void init() {
		initModHelper(
			ptr => ((IModHelper*)ptr)->DirectoryPath.cstr(),
			ptr => ((IModHelper*)ptr)->ConsoleCommands.ptr(),
			ptr => ((IModHelper*)ptr)->GameContent.ptr(),
			ptr => ((IModHelper*)ptr)->ModContent.ptr(),
			ptr => ((IModHelper*)ptr)->ContentPacks.ptr(),
			ptr => ((IModHelper*)ptr)->Data.ptr(),
			ptr => ((IModHelper*)ptr)->Input.ptr(),
			ptr => ((IModHelper*)ptr)->Reflection.ptr(),
			ptr => ((IModHelper*)ptr)->ModRegistry.ptr(),
			ptr => ((IModHelper*)ptr)->Multiplayer.ptr(),
			ptr => ((IModHelper*)ptr)->Translation.ptr()
		);
	}

	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	private static extern void initModHelper(
		DirectoryPath directoryPath,
		ConsoleCommands consoleCommands,
		GameContent gameContent,
		ModContent modContent,
		ContentPacks contentPacks,
		Data data,
		Input input,
		Reflection reflection,
		ModRegistry modRegistry,
		Multiplayer multiplayer,
		Translation translation
	);
}