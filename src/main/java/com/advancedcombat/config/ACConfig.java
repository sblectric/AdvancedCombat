package com.advancedcombat.config;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import net.minecraftforge.common.config.Configuration;

public class ACConfig {
	
	// config file
	private static Configuration config;
	
	public static void mainRegistry(File f) {
		config = new Configuration(f);
		getConfig();
	}
	
	// Vanilla
	public static boolean enableEnchantmentUpgrades;
	public static boolean jeiIntegration;
	public static String emeraldSwordBase;
	public static int emeraldSwordDamage;
	
	// Advanced Addons
	public static boolean durabilityTooltips;
	public static List<String> tooltipModBL;
	public static boolean enableAdvancedBow;
	public static boolean enableAdvancedShield;
	public static boolean enableWoodenArmor;
	public static boolean enableStoneArmor;
	public static boolean enableNetherArmor;
	public static boolean enableObsidianArmor;
	public static boolean enableTrackingDispenser;
	public static int trackingDispenserRange;
	
	/** Do it up */
	private static void getConfig() {
		final String OPTIONS = config.CATEGORY_GENERAL;
		
		config.load();
		
		// Vanilla
		enableEnchantmentUpgrades = config.getBoolean("Enable enchantment upgrades", OPTIONS, true, "");
		jeiIntegration = config.getBoolean("Show Enchantment Upgrade Recipes in JEI", OPTIONS, true, "Disable to reduce memory usage");
		emeraldSwordBase = config.getString("Base for Advanced Emerald Sword Recipe", OPTIONS, "minecraft:golden_sword", "");
		emeraldSwordDamage = config.getInt("Emerald Sword Attack Damage", OPTIONS, 18, 15, 30, "");
		
		// Advanced Addons
		durabilityTooltips = config.getBoolean("Better Durability Tooltips", OPTIONS, true,
				"Always show the durability of an item in its tooltip (regular or advanced tooltip mode), and in a more aesthetic manner than vanilla.");
		tooltipModBL = Arrays.asList(config.getStringList("Tooltip Mod Blacklist", OPTIONS, new String[]{"tconstruct"}, 
				"Mods (by mod ID) that should not show better durability on their items."));
		enableAdvancedBow = config.getBoolean("Advanced Bow", OPTIONS, true, "");
		enableAdvancedShield = config.getBoolean("Advanced Shield", OPTIONS, true, "");
		enableWoodenArmor = config.getBoolean("Wooden Armor", OPTIONS, true, "");
		enableStoneArmor = config.getBoolean("Stone Armor", OPTIONS, true, "");
		enableNetherArmor = config.getBoolean("Nether Fortress Armor", OPTIONS, true, "");
		enableObsidianArmor = config.getBoolean("Obsidian Armor", OPTIONS, true, "");
		enableTrackingDispenser = config.getBoolean("Tracking Dispenser", OPTIONS, true, "");
		trackingDispenserRange = config.getInt("Tracking Dispenser Range", OPTIONS, 32, 8, 128, "");
		
		if(config.hasChanged()) config.save();
	}

}
