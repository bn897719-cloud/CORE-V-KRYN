package vn.kryn.core;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class KrynScreen extends Screen {
    private int panelX, panelY, panelW = 330, panelH = 250;
    private int pulse = 0;

    public KrynScreen() { super(Text.literal("CORE V KRYN")); }

    @Override
    protected void init() {
        panelX = (width - panelW) / 2;
        panelY = (height - panelH) / 2;
        int x = panelX + 18, y = panelY + 55, w = panelW - 36, h = 20, gap = 25;
        clearChildren();
        addDrawableChild(toggle("Bảng FPS / FPS HUD", () -> Config.fps, v -> Config.fps = v, x, y, w, h));
        addDrawableChild(toggle("Ping / Độ trễ mạng", () -> Config.ping, v -> Config.ping = v, x, y += gap, w, h));
        addDrawableChild(toggle("Độ bền giáp / Armor HUD", () -> Config.armor, v -> Config.armor = v, x, y += gap, w, h));
        addDrawableChild(toggle("Hiệu ứng thuốc / Potion HUD", () -> Config.potions, v -> Config.potions = v, x, y += gap, w, h));
        addDrawableChild(toggle("Túi đồ / Inventory HUD", () -> Config.inventory, v -> Config.inventory = v, x, y += gap, w, h));
        addDrawableChild(toggle("Ánh neon / Neon UI", () -> Config.neon, v -> Config.neon = v, x, y += gap, w, h));
        addDrawableChild(toggle("Hạt trang trí / Ambient Particles", () -> Config.particles, v -> Config.particles = v, x, y += gap, w, h));
        addDrawableChild(ButtonWidget.builder(Text.literal("LƯU CẤU HÌNH / SAVE"), b -> {
            Config.save();
            b.setMessage(Text.literal("ĐÃ LƯU / SAVED"));
        }).dimensions(panelX + 18, panelY + panelH - 34, 140, 20).build());
        addDrawableChild(ButtonWidget.builder(Text.literal("ĐÓNG / CLOSE"), b -> close())
                .dimensions(panelX + panelW - 118, panelY + panelH - 34, 100, 20).build());
    }

    private ButtonWidget toggle(String label, java.util.function.BooleanSupplier get,
                                java.util.function.Consumer<Boolean> set, int x, int y, int w, int h) {
        return ButtonWidget.builder(Text.literal(label + (get.getAsBoolean() ? "  [BẬT]" : "  [TẮT]")), b -> {
            boolean next = !get.getAsBoolean();
            set.accept(next);
            b.setMessage(Text.literal(label + (next ? "  [BẬT]" : "  [TẮT]")));
        }).dimensions(x, y, w, h).build();
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        renderBackground(ctx, mouseX, mouseY, delta);
        pulse++;
        ctx.fill(panelX - 3, panelY - 3, panelX + panelW + 3, panelY + panelH + 3, 0xAA9B42E8);
        ctx.fill(panelX, panelY, panelX + panelW, panelY + panelH, 0xF20B0A14);
        ctx.fill(panelX, panelY, panelX + panelW, panelY + 3, 0xFFB45CFF);
        ctx.fill(panelX, panelY + 39, panelX + panelW, panelY + 40, 0xFF59318A);
        ctx.drawTextWithShadow(textRenderer, "✦ CORE V KRYN ✦", panelX + 18, panelY + 13, 0xFFFFFFFF);
        ctx.drawTextWithShadow(textRenderer, "GIAO DIỆN ĐIỀU KHIỂN  /  CLIENT MENU", panelX + 18, panelY + 28, 0xFFBFA5E8);
        super.render(ctx, mouseX, mouseY, delta);
        ctx.drawTextWithShadow(textRenderer, "Fabric 1.21.4  •  P: MỞ / ĐÓNG", panelX + 18, panelY + panelH - 10, 0xFF8E83A8);
    }

    @Override
    public boolean shouldPause() { return false; }

    @Override
    public void close() {
        Config.save();
        super.close();
    }
}
