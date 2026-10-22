package dev.baldmark.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public final class BaldMarkClient implements ClientModInitializer {
    public static boolean hudOn = true;
    public static boolean coordsOn = true;
    public static boolean playerEspOn = false;
    public static boolean xrayOn = false;
    public static boolean debrisFinderOn = false;
    public static boolean baseEspOn = false;
    private static KeyBinding toggleHud;
    private static KeyBinding openMenu;

    @Override
    public void onInitializeClient() {
        toggleHud = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.baldmark.toggle_hud", InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_H, KeyBinding.Category.MISC));
        openMenu = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.baldmark.open_menu", InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT, KeyBinding.Category.MISC));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleHud.wasPressed()) {
                hudOn = !hudOn;
                message(client, "HUD " + (hudOn ? "ON" : "OFF"));
            }
            while (openMenu.wasPressed()) {
                if (client.currentScreen == null) client.setScreen(new BaldMarkMainScreen());
                else client.setScreen(null);
            }
        });

        HudRenderCallback.EVENT.register((draw, tickCounter) -> {
            MinecraftClient c = MinecraftClient.getInstance();
            if (c.player == null || c.options.hudHidden || !hudOn) return;
            int x = 8, y = 8;
            draw.fill(x - 4, y - 4, x + 166, y + (coordsOn ? 45 : 31), 0xC9100D16);
            draw.fill(x - 4, y - 4, x + 166, y - 2, 0xFFB56BFF);
            draw.drawText(c.textRenderer, Text.literal("BALDMARK CLIENT"), x, y, 0xFFE0B8FF, true);
            if (coordsOn) draw.drawText(c.textRenderer,
                    Text.literal("XYZ  " + (int)c.player.getX() + "  " + (int)c.player.getY() + "  " + (int)c.player.getZ()),
                    x, y + 13, 0xFFF5F0FF, false);
            draw.drawText(c.textRenderer, Text.literal("RSHIFT  MENU  •  H  HUD"), x, y + (coordsOn ? 27 : 13), 0xFFBFAED0, false);
        });
    }

    private static void message(MinecraftClient client, String s) {
        if (client.player != null) client.player.sendMessage(Text.literal("§d[BaldMark] §f" + s), true);
    }
}
