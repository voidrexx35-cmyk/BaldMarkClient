package dev.baldmark.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public final class BaldMarkClickGuiScreen extends Screen {
    public BaldMarkClickGuiScreen() { super(Text.literal("BaldMark PvP ClickGUI")); }

    @Override
    protected void init() {
        int cx = this.width / 2;
        int left = cx - 112;
        int top = this.height / 2 - 62;
        addDrawableChild(ButtonWidget.builder(toggleLabel("Player ESP", BaldMarkClient.playerEspOn), b -> {
            BaldMarkClient.playerEspOn = !BaldMarkClient.playerEspOn; this.client.setScreen(new BaldMarkClickGuiScreen());
        }).dimensions(left, top, 224, 20).build());
        addDrawableChild(ButtonWidget.builder(toggleLabel("X-Ray", BaldMarkClient.xrayOn), b -> {
            BaldMarkClient.xrayOn = !BaldMarkClient.xrayOn; this.client.setScreen(new BaldMarkClickGuiScreen());
        }).dimensions(left, top + 25, 224, 20).build());
        addDrawableChild(ButtonWidget.builder(toggleLabel("Ancient Debris Finder", BaldMarkClient.debrisFinderOn), b -> {
            BaldMarkClient.debrisFinderOn = !BaldMarkClient.debrisFinderOn; this.client.setScreen(new BaldMarkClickGuiScreen());
        }).dimensions(left, top + 50, 224, 20).build());
        addDrawableChild(ButtonWidget.builder(toggleLabel("Base ESP", BaldMarkClient.baseEspOn), b -> {
            BaldMarkClient.baseEspOn = !BaldMarkClient.baseEspOn; this.client.setScreen(new BaldMarkClickGuiScreen());
        }).dimensions(left, top + 75, 224, 20).build());
        addDrawableChild(ButtonWidget.builder(Text.literal("Main Menu"), b -> this.client.setScreen(new BaldMarkMainScreen()))
                .dimensions(left, top + 100, 108, 20).build());
        addDrawableChild(ButtonWidget.builder(Text.literal("Close"), b -> this.close())
                .dimensions(left + 116, top + 100, 108, 20).build());
    }

    private static Text toggleLabel(String name, boolean enabled) {
        return Text.literal(name + "  •  " + (enabled ? "ON" : "OFF"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        int cx = this.width / 2;
        context.fill(cx - 132, this.height / 2 - 105, cx + 132, this.height / 2 + 91, 0xE9100D16);
        context.fill(cx - 132, this.height / 2 - 105, cx + 132, this.height / 2 - 102, 0xFFB56BFF);
        context.drawCenteredTextWithShadow(textRenderer, Text.literal("BALDMARK / PVP MODULES"), cx, this.height / 2 - 94, 0xFFE0B8FF);
        context.drawCenteredTextWithShadow(textRenderer, Text.literal("UI toggles are prototypes; gameplay modules are not implemented."), cx, this.height / 2 + 77, 0xFFFFD28A);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override public boolean shouldPause() { return false; }
}
