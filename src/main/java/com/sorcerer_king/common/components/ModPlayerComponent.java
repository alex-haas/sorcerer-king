package com.sorcerer_king.common.components;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;

public interface ModPlayerComponent extends ComponentV3 {
    int getTier();
    double getMaxMana();
    double getPassiveManaReg();
    double getCurrentMana();
}
