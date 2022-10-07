package com.sorcerer_king.guis;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;

public class SpellConfigGui extends LightweightGuiDescription {
    public SpellConfigGui() {
        WGridPanel mainPanel = new WGridPanel();
        setRootPanel(mainPanel);
        mainPanel.setSize(300, 200);
    }
}
