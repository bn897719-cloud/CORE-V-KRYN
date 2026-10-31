package vn.kryn.core;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class CoreVKryn implements ClientModInitializer {
    public static final String MOD_NAME = "CORE V KRYN";
    private static KeyBinding openGuiKey;

    @Override
    public void onInitializeClient() {
        Config.load();
        openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.core_vkryn.open_gui", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_P,
                "category.core_vkryn"
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openGuiKey.wasPressed()) {
                if (client.currentScreen == null) client.setScreen(new KrynScreen());
                else if (client.currentScreen instanceof KrynScreen) client.setScreen(null);
            }
        });
        HudRenderCallback.EVENT.register((drawContext, tickCounter) -> KrynHud.render(drawContext));
    }
}
