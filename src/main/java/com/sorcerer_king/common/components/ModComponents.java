package com.sorcerer_king.common.components;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public final class ModComponents implements EntityComponentInitializer {
    public static final ComponentKey<ModPlayerComponent> PLAYER =
            ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier("sorcerer-king:player"), ModPlayerComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.beginRegistration(PlayerEntity.class, PLAYER).impl(ModPlayer.class).end(ModPlayer::new);
    }
}