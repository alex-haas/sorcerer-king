package com.sorcerer_king.guis;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import net.minecraft.text.Text;

public class SpellConfigGui extends LightweightGuiDescription {
    public SpellConfigGui() {
        WGridPanel root = new WGridPanel();
        root.setSize(300, 200);
        WLabel label = new WLabel(Text.translatable("gui.spell_config.title"));
        root.add(label, 1, 1);
        setRootPanel(root);
    }
}
