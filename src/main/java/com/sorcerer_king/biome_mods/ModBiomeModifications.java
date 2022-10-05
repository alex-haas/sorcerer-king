package com.sorcerer_king.biome_mods;

import com.sorcerer_king.Constants;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
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
        ConfiguredFeature<?, ?> manaOreFeature = new ConfiguredFeature<>(
                Feature.ORE,
                new OreFeatureConfig(
                        OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
                        Blocks.WHITE_WOOL.getDefaultState(),
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

        final String mana_ore_id = "overworld_mana_ore";
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Constants.MOD_ID, mana_ore_id), manaOreFeature);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Constants.MOD_ID, mana_ore_id), placedFeature);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Constants.MOD_ID, mana_ore_id)));

    }
}
