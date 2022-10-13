package com.sorcerer_king.guis;

import com.google.common.collect.Lists;
import com.sorcerer_king.common.Globals;
import com.sorcerer_king.common.components.ModComponents;
import com.sorcerer_king.common.components.ModPlayerComponent;
import com.sorcerer_king.common.spells.ModSpell;
import com.sorcerer_king.common.spells.ModSpells;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Axis;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Arrays;
import java.util.List;

public class SpellConfigGui extends LightweightGuiDescription {
    private static final Insets DEFAULT_INSETS = new Insets(8, 16);
    private static final int GRID_SIZE = 16;
    private static final int WINDOW_WIDTH = 20;
    private static final int WINDOW_HEIGHT = 12;
    private final ModPlayerComponent player;

    public SpellConfigGui(ModPlayerComponent player) {
        this.player = player;

        WBox root = new WBox(Axis.VERTICAL);
        root.setInsets(DEFAULT_INSETS);
        WScrollPanel scrollRoot = new WScrollPanel(root);
        scrollRoot.setSize(WINDOW_WIDTH * GRID_SIZE, WINDOW_HEIGHT * GRID_SIZE);
        root.add(buildTitleBar());
        root.add(buildSpellList());
        setRootPanel(scrollRoot);
    }

    private WWidget buildTitleBar() {
        WLabel titleLabel = new WLabel(Text.translatable("gui.spell_config.title").setStyle(Style.EMPTY.withBold(true)));
        WDynamicLabel tierLabel = new WDynamicLabel(() -> I18n.translate(
                "gui.spell_config.tier",
                player.getTier(),
                player.getTierTitle()

        ));
        WDynamicLabel manaLabel = new WDynamicLabel(() -> I18n.translate(
                "gui.spell_config.mana",
                Globals.DECIMAL_FORMAT.format(player.getCurrentMana()),
                Globals.DECIMAL_FORMAT.format(player.getMaxMana())
        ));

        WGridPanel panel = new WGridPanel(GRID_SIZE);
        int middleWidth = (WINDOW_WIDTH - 4) / 2;
        panel.add(titleLabel, 0, 0, middleWidth, 1);
        panel.add(tierLabel, middleWidth, 0, middleWidth, 1);
        panel.add(manaLabel, middleWidth, 1, middleWidth, 1);

        return panel;
    }

    private WWidget buildSpellList() {
        WBox panel = new WBox(Axis.VERTICAL);
        for (int i = 0; i < player.getTier(); i++) {
            panel.add(genTierPanel(i + 1));
        }
        return panel;
    }

    private WWidget genTierPanel(int tier) {
        WBox panel = new WBox(Axis.VERTICAL);
        panel.setInsets(DEFAULT_INSETS);
        panel.setBackgroundPainter(BackgroundPainter.createColorful(colorForTier(tier)));
        WLabel tierLabel = new WLabel(Text.translatable("gui.spell_config.spell_tier_label", tier));
        panel.add(tierLabel);

        ModSpell[] spells = getModspells(tier);
        for (List<ModSpell> row : Lists.partition(Arrays.stream(spells).toList(), 3)) {
            WBox rowPanel = new WBox(Axis.HORIZONTAL);
            for (ModSpell spell : row) {
                WButton btn = new WButton(new TextureIcon(spell.getIconIdentifier()), spell.getTitle());
                btn.setAlignment(HorizontalAlignment.LEFT);
                btn.setOnClick(() -> onSpellButtonClicked(spell));
                rowPanel.add(btn, GRID_SIZE * 5, GRID_SIZE);
            }
            panel.add(rowPanel);
        }

        return panel;
    }
    
    private void onSpellButtonClicked(ModSpell spell) {
        MinecraftClient.getInstance().setScreen(new ModScreen(new SpellConfigDetailGui(player, spell)));
    }

    private ModSpell[] getModspells(int tier) {
        // Just demo code for now
        return new ModSpell[] {ModSpells.HEAL, ModSpells.REPAIR};
    }

    private int colorForTier(int tier) {
        switch (tier) {
            case 0: return 0xFF_fff3e0;
            case 1: return 0xFF_ffe0b2;
            case 2: return 0xFF_ffcc80;
            case 3: return 0xFF_ffb74d;
            case 4: return 0xFF_ffa726;
            case 5: return 0xFF_ff9800;
            case 6: return 0xFF_fb8c00;
            case 7: return 0xFF_f57c00;
            case 8: return 0xFF_ef6c00;
            case 9: return 0xFF_e65100;
            default: throw new NotImplementedException();
        }
    }
}