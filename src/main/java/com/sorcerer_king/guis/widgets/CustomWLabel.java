package com.sorcerer_king.guis.widgets;

import io.github.cottonmc.cotton.gui.client.LibGui;
import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

public class CustomWLabel extends WLabel {
    private final int fontHeight;
    private final double scale;

    public CustomWLabel(Text text, int fontHeight) {
        super(text);
        this.fontHeight = fontHeight;
        this.scale = ((double)fontHeight) / MinecraftClient.getInstance().textRenderer.fontHeight;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void paint(MatrixStack matrices, int x, int y, int mouseX, int mouseY) {
        int yOffset = switch (verticalAlignment) {
            case CENTER -> height / 2 - this.fontHeight / 2;
            case BOTTOM -> height - this.fontHeight;
            case TOP -> 0;
        };

        if (this.scale != 1.0) {
            matrices.push();
            matrices.scale(0.5F, 0.5F, 0.5F);
        }
        ScreenDrawing.drawString(matrices, text.asOrderedText(), horizontalAlignment, x, y + yOffset, this.getWidth(), LibGui.isDarkMode() ? darkmodeColor : color);
        if (this.scale != 1.0) {
            matrices.pop();
        }

        Style hoveredTextStyle = getTextStyleAt(mouseX, mouseY);
        ScreenDrawing.drawTextHover(matrices, hoveredTextStyle, x + mouseX, y + mouseY);
    }
}
