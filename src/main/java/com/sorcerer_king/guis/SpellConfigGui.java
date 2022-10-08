package com.sorcerer_king.guis;

import com.sorcerer_king.common.Globals;
import com.sorcerer_king.common.components.ModComponents;
import com.sorcerer_king.common.components.ModPlayerComponent;
import com.sorcerer_king.common.spells.ModSpells;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WDynamicLabel;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.player.PlayerEntity;
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

        ModPlayerComponent modPlayer = ModComponents.PLAYER.get(playerEntity);
        WDynamicLabel tierLabel = new WDynamicLabel(() -> I18n.translate(
                "gui.spell_config.tier",
                modPlayer.getTier()
        ));
        root.add(tierLabel, 1, 2);

        WDynamicLabel manaLabel = new WDynamicLabel(() -> I18n.translate(
                "gui.spell_config.mana",
                Globals.DECIMAL_FORMAT.format(modPlayer.getCurrentMana()),
                Globals.DECIMAL_FORMAT.format(modPlayer.getMaxMana())
        ));
        root.add(manaLabel, 1, 3);

        WButton healBtn = new WButton(new TextureIcon(ModSpells.HEAL.getIconIdentifier()), ModSpells.HEAL.getTitle());
        WButton repairBtn = new WButton(new TextureIcon(ModSpells.REPAIR.getIconIdentifier()), ModSpells.REPAIR.getTitle());
        root.add(healBtn, 1, 4);
        root.add(repairBtn, 2, 4);
    }
}
