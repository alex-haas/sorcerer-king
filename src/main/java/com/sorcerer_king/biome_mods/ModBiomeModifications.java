package com.sorcerer_king.biome_mods;

import com.sorcerer_king.Constants;
import com.sorcerer_king.blocks.ManaOre;
import com.sorcerer_king.blocks.ModBlocks;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import java.util.Arrays;

public class ModBiomeModifications {

    public static void registerBiomeModifications() {
        registerManaOre();
    }

    private static void registerManaOre() {
        ConfiguredFeature<?, ?> manaOreFeature = new ConfiguredFeature<>(
                Feature.ORE,
                new OreFeatureConfig(
                        OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
                        ModBlocks.get(ManaOre.ID).getDefaultState(),
                        9
                )
        );

        PlacedFeature placedFeature = new PlacedFeature(
                RegistryEntry.of(manaOreFeature),
                Arrays.asList(
                        CountPlacementModifier.of(20), // number of veins per chunk
                        SquarePlacementModifier.of(), // spreading horizontally
                        HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))
                ));

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Constants.MOD_ID, ManaOre.ID), manaOreFeature);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Constants.MOD_ID, ManaOre.ID), placedFeature);
        RegistryKey<PlacedFeature> oreRegistryKey = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Constants.MOD_ID, ManaOre.ID));
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreRegistryKey);
    }
}
