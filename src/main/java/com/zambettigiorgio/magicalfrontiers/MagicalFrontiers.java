package com.zambettigiorgio.magicalfrontiers;

import com.zambettigiorgio.magicalfrontiers.block.ModBlocks;
import com.zambettigiorgio.magicalfrontiers.effect.ModEffects;
import com.zambettigiorgio.magicalfrontiers.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MagicalFrontiers implements ModInitializer {
	public static final String MOD_ID = "magical-frontiers";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModEffects.registerEffects();
	}
}