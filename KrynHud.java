package vn.kryn.core;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public final class KrynHud {
    private KrynHud() {}

    public static void render(DrawContext ctx) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.options.hudHidden) return;
        int x = 8, y = 8;
        if (Config.fps || Config.ping) {
            panel(ctx, x, y, 116, 29);
            String stats = (Config.fps ? "FPS " + mc.getCurrentFps() : "");
            if (Config.ping) {
                var entry = mc.getNetworkHandler() == null ? null :
                        mc.getNetworkHandler().getPlayerListEntry(mc.player.getUuid());
                stats += (stats.isEmpty() ? "" : "  ") + "PING " + (entry == null ? "--" : entry.getLatency());
            }
            ctx.drawTextWithShadow(mc.textRenderer, "✦ " + stats, x + 7, y + 10, 0xFFFFFFFF);
            y += 35;
        }
        if (Config.armor) {
            panel(ctx, x, y, 116, 28);
            int ax = x + 6;
            for (EquipmentSlot slot : new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET}) {
                ItemStack stack = mc.player.getEquippedStack(slot);
                ctx.drawItem(stack, ax, y + 5);
                if (!stack.isEmpty()) {
                    int max = stack.getMaxDamage();
                    int left = max == 0 ? 100 : (max - stack.getDamage()) * 100 / max;
                    ctx.drawText(mc.textRenderer, left + "%", ax + 1, y + 21, left < 20 ? 0xFFFF5555 : 0xFFFFFFFF, true);
                }
                ax += 27;
            }
            y += 34;
        }
        if (Config.potions && !mc.player.getStatusEffects().isEmpty()) {
            int count = Math.min(5, mc.player.getStatusEffects().size());
            panel(ctx, x, y, 170, 14 + count * 12);
            int row = 0;
            for (StatusEffectInstance effect : mc.player.getStatusEffects()) {
                if (row >= 5) break;
                String name = effect.getEffectType().value().getName().getString();
                String time = effect.isInfinite() ? "∞" : String.format("%d:%02d", effect.getDuration() / 1200, (effect.getDuration() / 20) % 60);
                ctx.drawTextWithShadow(mc.textRenderer, name + " " + time, x + 6, y + 5 + row * 12, 0xFFE9DFFF);
                row++;
            }
        }
        if (Config.inventory) {
            int iy = mc.getWindow().getScaledHeight() - 58;
            panel(ctx, 8, iy, 182, 28);
            for (int i = 0; i < 9; i++) ctx.drawItem(mc.player.getInventory().getStack(i), 12 + i * 19, iy + 5);
        }
    }

    private static void panel(DrawContext ctx, int x, int y, int w, int h) {
        ctx.fill(x - 1, y - 1, x + w + 1, y + h + 1, Config.neon ? 0xFFB45CFF : 0xFF554866);
        ctx.fill(x, y, x + w, y + h, 0xC90B0A16);
    }
}
