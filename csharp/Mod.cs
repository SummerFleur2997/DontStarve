using System.Runtime.InteropServices;
using DontStarve.Wrapper;
using StardewModdingAPI;

namespace DontStarve;

// ReSharper disable once UnusedType.Global
internal class Mod : StardewModdingAPI.Mod {
	public override void Entry(IModHelper helper) {
		// ModHelper.init();
		// init(helper.ptr());

		Textures.loadTextures(helper.ModContent);
		Buff.Buff.init(helper);
		Sanity.Sanity.init(helper);
		Hunger.Hunger.init(helper);

		helper.Events.Display.RenderingHud += (_, e) => Hud.OnRenderingHud(helper, e);
	}

	[DllImport("DontStarve.Kotlin.dll", CallingConvention = CallingConvention.Cdecl)]
	private static extern void init(nint helper);
}