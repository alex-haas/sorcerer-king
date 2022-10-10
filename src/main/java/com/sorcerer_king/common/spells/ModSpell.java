package com.sorcerer_king.common.spells;

import com.sorcerer_king.common.Globals;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public abstract class ModSpell {
    public abstract String getId();

    public Identifier getIconIdentifier() {
        return new Identifier(Globals.MOD_ID, "textures/spell/%s.png".formatted(getId()));
    }

    public Text getTitle() {
        return Text.translatable("spell." + getId());
    }
}
