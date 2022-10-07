package com.sorcerer_king.guis;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;

public class SpellConfigGui extends LightweightGuiDescription {
    public SpellConfigGui(PlayerEntity playerEntity) {
        WGridPanel root = new WGridPanel();
        root.setSize(300, 200);
        addContent(root, playerEntity);
        setRootPanel(root);
    }

    private void addContent(WGridPanel root, PlayerEntity playerEntity) {
        WLabel label = new WLabel(Text.translatable("gui.spell_config.title"));
        root.add(label, 1, 1);


        playerEntity.readCustomDataFromNbt(new NbtCompound());
        WButton btn = new WButton(Text.translatable("spells.heal"));
        root.add(btn, 1, 2);
    }
}
