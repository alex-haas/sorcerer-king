package com.sorcerer_king.guis;

import com.sorcerer_king.common.Globals;
import com.sorcerer_king.common.components.ModComponents;
import com.sorcerer_king.common.components.ModPlayerComponent;
import com.sorcerer_king.common.spells.ModSpells;
import com.sorcerer_king.guis.widgets.CustomWLabel;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Color;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class SpellConfigGui extends LightweightGuiDescription {

    public SpellConfigGui(PlayerEntity playerEntity) {
        WGridPanel root = new WGridPanel();
        root.setBackgroundPainter(BackgroundPainter.createColorful(Color.BLUE.toRgb()));
        root.setSize(300, 200);
        addContent(root, playerEntity);
        setRootPanel(root);
    }

    private void addContent(WGridPanel root, PlayerEntity playerEntity) {
        WLabel label = new CustomWLabel(Text.translatable("gui.spell_config.title"), 12);
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

        for (int i = 1; i <= modPlayer.getTier(); i++) {
            root.add(genTierPanel(i, modPlayer), 1, 4, 15, 3);
        }
    }

    private WWidget genTierPanel(int tier, ModPlayerComponent modPlayer) {
        WGridPanel panel = new WGridPanel();
        panel.setBackgroundPainter(BackgroundPainter.createColorful(Color.GREEN.toRgb()));
        WLabel tierLabel = new CustomWLabel(Text.literal("Tier " + tier), 6);
        panel.add(tierLabel, 0, 0, 5, 1);

        WButton healBtn = new WButton(new TextureIcon(ModSpells.HEAL.getIconIdentifier()), ModSpells.HEAL.getTitle());
        panel.add(healBtn, 0, 2, 3, 1);
        WButton repairBtn = new WButton(new TextureIcon(ModSpells.REPAIR.getIconIdentifier()), ModSpells.REPAIR.getTitle());
        panel.add(repairBtn, 4, 2, 3, 1);
        return panel;
    }
}
