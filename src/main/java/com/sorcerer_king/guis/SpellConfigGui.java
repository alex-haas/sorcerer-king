package com.sorcerer_king.guis;

import com.sorcerer_king.common.Globals;
import com.sorcerer_king.common.components.ModComponents;
import com.sorcerer_king.common.components.ModPlayerComponent;
import com.sorcerer_king.common.spells.ModSpells;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Axis;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import org.apache.commons.lang3.NotImplementedException;

public class SpellConfigGui extends LightweightGuiDescription {
    private static final Insets DEFAULT_INSETS = new Insets(8, 16);
    private static final int GRID_SIZE = 16;
    private static final int WINDOW_WIDTH = 20;
    private static final int WINDOW_HEIGHT = 12;

    public SpellConfigGui(PlayerEntity playerEntity) {
        ModPlayerComponent modPlayer = ModComponents.PLAYER.get(playerEntity);

        WBox root = new WBox(Axis.VERTICAL);
        root.setInsets(DEFAULT_INSETS);
        //root.setBackgroundPainter(BackgroundPainter.createColorful(Color.MAGENTA_DYE.toRgb()));
        WScrollPanel scrollRoot = new WScrollPanel(root);
        scrollRoot.setSize(WINDOW_WIDTH * GRID_SIZE, WINDOW_HEIGHT * GRID_SIZE);
        root.add(buildTitleBar(modPlayer));
        root.add(buildSpellList(modPlayer));
        setRootPanel(scrollRoot);
    }

    private WWidget buildTitleBar(ModPlayerComponent modPlayer) {
        WLabel titleLabel = new WLabel(Text.translatable("gui.spell_config.title").setStyle(Style.EMPTY.withBold(true)));
        WDynamicLabel tierLabel = new WDynamicLabel(() -> I18n.translate(
                "gui.spell_config.tier",
                modPlayer.getTier(),
                modPlayer.getTierTitle()

        ));
        WDynamicLabel manaLabel = new WDynamicLabel(() -> I18n.translate(
                "gui.spell_config.mana",
                Globals.DECIMAL_FORMAT.format(modPlayer.getCurrentMana()),
                Globals.DECIMAL_FORMAT.format(modPlayer.getMaxMana())
        ));

        WGridPanel panel = new WGridPanel(GRID_SIZE);
        int middleWidth = (WINDOW_WIDTH - 2) / 2;
        panel.add(titleLabel, 0, 0, middleWidth, 1);
        panel.add(tierLabel, middleWidth, 0, middleWidth, 1);
        panel.add(manaLabel, middleWidth, 1, middleWidth, 1);

        return panel;
    }

    private WWidget buildSpellList(ModPlayerComponent modPlayer) {
        WBox panel = new WBox(Axis.VERTICAL);

        //panel.setBackgroundPainter(BackgroundPainter.createColorful(Color.ORANGE_DYE.toRgb()));
        for (int i = 0; i < 9 /*modPlayer.getTier()*/; i++) {
            panel.add(genTierPanel(i + 1, modPlayer));
        }
        return panel;
    }

    private WWidget genTierPanel(int tier, ModPlayerComponent modPlayer) {
        WGridPanel panel = new WGridPanel();
        panel.setInsets(DEFAULT_INSETS);
        panel.setBackgroundPainter(BackgroundPainter.createColorful(colorForTier(tier)));
        WLabel tierLabel = new WLabel(Text.translatable("gui.spell_config.spell_tier_label", tier));
        panel.add(tierLabel, 0, 0, WINDOW_WIDTH - 6, 1);

        WButton healBtn = new WButton(new TextureIcon(ModSpells.HEAL.getIconIdentifier()), ModSpells.HEAL.getTitle());
        healBtn.setAlignment(HorizontalAlignment.LEFT);
        panel.add(healBtn, 0, 1, 3, 1);

        WButton repairBtn = new WButton(new TextureIcon(ModSpells.REPAIR.getIconIdentifier()), ModSpells.REPAIR.getTitle());
        repairBtn.setAlignment(HorizontalAlignment.LEFT);
        panel.add(repairBtn, 4, 1, 3, 1);

        return panel;
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
