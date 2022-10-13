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
import io.github.cottonmc.cotton.gui.widget.data.*;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Arrays;
import java.util.List;

public class SpellConfigDetailGui extends LightweightGuiDescription {
    private static final Identifier BACK_ICON = new Identifier(Globals.MOD_ID, "textures/gui/back.png");
    private static final Insets DEFAULT_INSETS = new Insets(8, 16);
    private static final int GRID_SIZE = 16;
    private static final int WINDOW_WIDTH = 20;
    private static final int WINDOW_HEIGHT = 12;
    private final ModPlayerComponent player;
    private ModSpell spell;

    public SpellConfigDetailGui(ModPlayerComponent player, ModSpell spell) {
        this.player = player;
        this.spell = spell;

        WBox root = new WBox(Axis.VERTICAL);
        root.setInsets(DEFAULT_INSETS);
        WScrollPanel scrollRoot = new WScrollPanel(root);
        scrollRoot.setSize(WINDOW_WIDTH * GRID_SIZE, WINDOW_HEIGHT * GRID_SIZE);
        root.add(buildTitleBar());
        setRootPanel(scrollRoot);
    }

    private WWidget buildTitleBar() {
        WLabel titleLabel = new WLabel(spell.getTitle().setStyle(Style.EMPTY.withBold(true)));
        titleLabel.setVerticalAlignment(VerticalAlignment.CENTER);
        WButton backButton = new WButton(new TextureIcon(new Texture(BACK_ICON)));
        backButton.setOnClick(this::onBackButtonClicked);

        WBox panel = new WBox(Axis.HORIZONTAL);
        //panel.setVerticalAlignment(VerticalAlignment.CENTER);
        panel.add(backButton);
        panel.add(titleLabel);

        return panel;
    }

    private void onBackButtonClicked() {
        MinecraftClient.getInstance().setScreen(new ModScreen(new SpellConfigGui(player)));
    }
}