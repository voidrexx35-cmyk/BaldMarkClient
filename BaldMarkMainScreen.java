package dev.baldmark.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public final class BaldMarkMainScreen extends Screen {
    public BaldMarkMainScreen() { super(Text.literal("BaldMark Client")); }

    @Override
    protected void init() {
        int cx = this.width / 2;
        int top = this.height / 2 - 54;
        addDrawableChild(ButtonWidget.builder(Text.literal("Open PvP ClickGUI"), b -> this.client.setScreen(new BaldMarkClickGuiScreen()))
                .dimensions(cx - 105, top, 210, 22).build());
        addDrawableChild(ButtonWidget.builder(Text.literal("Toggle HUD: " + (BaldMarkClient.hudOn ? "ON" : "OFF")), b -> {
            BaldMarkClient.hudOn = !BaldMarkClient.hudOn; this.client.setScreen(new BaldMarkMainScreen());
        }).dimensions(cx - 105, top + 28, 210, 22).build());
        addDrawableChild(ButtonWidget.builder(Text.literal("Coordinates: " + (BaldMarkClient.coordsOn ? "ON" : "OFF")), b -> {
            BaldMarkClient.coordsOn = !BaldMarkClient.coordsOn; this.client.setScreen(new BaldMarkMainScreen());
        }).dimensions(cx - 105, top + 56, 210, 22).build());
        addDrawableChild(ButtonWidget.builder(Text.literal("Back to game"), b -> this.close())
                .dimensions(cx - 105, top + 84, 210, 22).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        int cx = this.width / 2;
        context.fill(cx - 124, this.height / 2 - 103, cx + 124, this.height / 2 + 93, 0xE9100D16);
        context.fill(cx - 124, this.height / 2 - 103, cx + 124, this.height / 2 - 100, 0xFFB56BFF);
        context.drawCenteredTextWithShadow(textRenderer, Text.literal("BALDMARK"), cx, this.height / 2 - 91, 0xFFE0B8FF);
        context.drawCenteredTextWithShadow(textRenderer, Text.literal("FEATHER-STYLE CLIENT MENU"), cx, this.height / 2 - 77, 0xFFBFAED0);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override public boolean shouldPause() { return false; }
}
