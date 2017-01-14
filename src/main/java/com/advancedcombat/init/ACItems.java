package com.advancedcombat.init;

import java.util.List;

import com.advancedcombat.api.IItemAdvanced;
import com.advancedcombat.config.ACConfig;
import com.advancedcombat.items.ItemArmorAdvanced;
import com.advancedcombat.items.ItemArmorNether;
import com.advancedcombat.items.ItemArmorObsidian;
import com.advancedcombat.items.ItemArmorStone;
import com.advancedcombat.items.ItemArmorWood;
import com.advancedcombat.items.ItemBasic;
import com.advancedcombat.items.ItemBowAdvanced;
import com.advancedcombat.items.ItemEnchUpgrade;
import com.advancedcombat.items.ItemShieldAdvanced;
import com.advancedcombat.items.ItemSingleUpgrade;
import com.advancedcombat.ref.RefStrings;
import com.advancedcombat.registry.RegistryHelper;
import com.advancedcombat.util.JointList;

import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Item class */
public class ACItems {
	
	private static JointList<IItemAdvanced> items;
	private static JointList<IItemAdvanced> upgrades;
	
	public static void mainRegistry() {
		Swords.mainRegistry();
		items = new JointList();
		upgrades = new JointList();
		addMaterials();
		addItems();
		registerItems();
	}
	
	// armors
	public static ArmorMaterial wood;
	public static ArmorMaterial stone;
	public static ArmorMaterial nether;
	public static ArmorMaterial obsidian;
	
	/** add the needed materials */
	private static void addMaterials() {
		wood = EnumHelper.addArmorMaterial("Wood Log", RefStrings.MODID + ":wood", 6, new int[]{1, 3, 4, 1}, 5, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0);
		stone = EnumHelper.addArmorMaterial("Stone", RefStrings.MODID + ":stone", 11, new int[]{2, 4, 5, 2}, 8, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0);
		nether = EnumHelper.addArmorMaterial("Nether", RefStrings.MODID + ":nether", 33, new int[]{3, 5, 7, 3}, 8, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2.5F);
		obsidian = EnumHelper.addArmorMaterial("Obsidian", RefStrings.MODID + ":obsidian", 88, new int[]{3, 6, 8, 4}, 8, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 4);
		stone.setRepairItem(new ItemStack(ACBlocks.advancedStone));
		nether.setRepairItem(new ItemStack(ACBlocks.netherFortressBlock));
		obsidian.setRepairItem(new ItemStack(ACBlocks.reinforcedObsidian));
	}
	
	// upgrades
	public static ItemBasic sharpnessUpgrade;
	public static ItemBasic smiteUpgrade;
	public static ItemBasic spiderUpgrade;
	public static ItemBasic knockbackUpgrade;
	public static ItemBasic fireAspectUpgrade;
	public static ItemBasic lootingUpgrade;
	public static ItemBasic unbreakingUpgrade;
	public static ItemBasic mendingUpgrade;
	
	// addon upgrades
	public static ItemBasic protectionUpgrade;
	public static ItemBasic fireProtectionUpgrade;
	public static ItemBasic blastProtectionUpgrade;
	public static ItemBasic projectileProtectionUpgrade;
	public static ItemBasic featherFallingUpgrade;
	public static ItemBasic respirationUpgrade;
	public static ItemBasic aquaAffinityUpgrade;
	public static ItemBasic thornsUpgrade;
	public static ItemBasic depthStriderUpgrade;
	public static ItemBasic frostWalkerUpgrade;
	public static ItemBasic efficiencyUpgrade;
	public static ItemBasic silkTouchUpgrade;
	public static ItemBasic fortuneUpgrade;
	public static ItemBasic powerUpgrade;
	public static ItemBasic punchUpgrade;
	public static ItemBasic flameUpgrade;
	public static ItemBasic infinityUpgrade;
	public static ItemBasic lotsUpgrade;
	public static ItemBasic lureUpgrade;
	
	// items
	public static ItemBowAdvanced advancedBow;
	public static ItemShieldAdvanced advancedShield;
	
	// armor
	public static ItemArmorAdvanced woodenHelm;
	public static ItemArmorAdvanced woodenChest;
	public static ItemArmorAdvanced woodenLegs;
	public static ItemArmorAdvanced woodenBoots;
	
	public static ItemArmorAdvanced stoneHelm;
	public static ItemArmorAdvanced stoneChest;
	public static ItemArmorAdvanced stoneLegs;
	public static ItemArmorAdvanced stoneBoots;
	
	public static ItemArmorAdvanced netherHelm;
	public static ItemArmorAdvanced netherChest;
	public static ItemArmorAdvanced netherLegs;
	public static ItemArmorAdvanced netherBoots;
	
	public static ItemArmorAdvanced obsidianHelm;
	public static ItemArmorAdvanced obsidianChest;
	public static ItemArmorAdvanced obsidianLegs;
	public static ItemArmorAdvanced obsidianBoots;
	
	/** Add items to the game */
	private static void addItems() {
		// enchantment upgrades
		if(ACConfig.enableEnchantmentUpgrades) items.join(
			sharpnessUpgrade = (ItemBasic)new ItemEnchUpgrade(Enchantments.SHARPNESS).setRegistryName("sharpness_upgrade"),
			smiteUpgrade = (ItemBasic)new ItemEnchUpgrade(Enchantments.SMITE).setRegistryName("smite_upgrade"),
			spiderUpgrade = (ItemBasic)new ItemEnchUpgrade(Enchantments.BANE_OF_ARTHROPODS).setRegistryName("spider_upgrade"),
			knockbackUpgrade = (ItemBasic)new ItemEnchUpgrade(Enchantments.KNOCKBACK).setRegistryName("knockback_upgrade"),
			fireAspectUpgrade = (ItemBasic)new ItemEnchUpgrade(Enchantments.FIRE_ASPECT).setRegistryName("fire_aspect_upgrade"),
			lootingUpgrade = (ItemBasic)new ItemEnchUpgrade(Enchantments.LOOTING).setRegistryName("looting_upgrade"),
			unbreakingUpgrade = (ItemBasic)new ItemEnchUpgrade(Enchantments.UNBREAKING).setRegistryName("unbreaking_upgrade"),
			mendingUpgrade = (ItemBasic)new ItemEnchUpgrade(Enchantments.MENDING, true).setRegistryName("mending_upgrade"),
			
			protectionUpgrade = (ItemBasic)new ItemEnchUpgrade(Enchantments.PROTECTION).setRegistryName("protection_upgrade"),
			fireProtectionUpgrade = (ItemBasic)new ItemEnchUpgrade(Enchantments.FIRE_PROTECTION).setRegistryName("fire_protection_upgrade"),
			blastProtectionUpgrade = (ItemBasic)new ItemEnchUpgrade(Enchantments.BLAST_PROTECTION).setRegistryName("blast_protection_upgrade"),
			projectileProtectionUpgrade = (ItemBasic)new ItemEnchUpgrade(Enchantments.PROJECTILE_PROTECTION).setRegistryName("projectile_protection_upgrade"),
			featherFallingUpgrade = (ItemBasic)new ItemEnchUpgrade(Enchantments.FEATHER_FALLING).setRegistryName("feather_falling_upgrade"),
			respirationUpgrade = (ItemBasic)new ItemSingleUpgrade(Enchantments.RESPIRATION).setRegistryName("respiration_upgrade"),
			aquaAffinityUpgrade = (ItemBasic)new ItemSingleUpgrade(Enchantments.AQUA_AFFINITY).setRegistryName("aqua_affinity_upgrade"),
			thornsUpgrade = (ItemBasic)new ItemEnchUpgrade(Enchantments.THORNS).setRegistryName("thorns_upgrade"),
			depthStriderUpgrade = (ItemBasic)new ItemSingleUpgrade(Enchantments.DEPTH_STRIDER).setRegistryName("depth_strider_upgrade"),
			frostWalkerUpgrade = (ItemBasic)new ItemEnchUpgrade(Enchantments.FROST_WALKER).setRegistryName("frost_walker_upgrade"),
			efficiencyUpgrade = (ItemBasic)new ItemEnchUpgrade(Enchantments.EFFICIENCY).setRegistryName("efficiency_upgrade"),
			silkTouchUpgrade = (ItemBasic)new ItemSingleUpgrade(Enchantments.SILK_TOUCH).setRegistryName("silk_touch_upgrade"),
			fortuneUpgrade = (ItemBasic)new ItemEnchUpgrade(Enchantments.FORTUNE).setRegistryName("fortune_upgrade"),
			powerUpgrade = (ItemBasic)new ItemEnchUpgrade(Enchantments.POWER).setRegistryName("power_upgrade"),
			punchUpgrade = (ItemBasic)new ItemEnchUpgrade(Enchantments.PUNCH).setRegistryName("punch_upgrade"),
			flameUpgrade = (ItemBasic)new ItemSingleUpgrade(Enchantments.FLAME).setRegistryName("flame_upgrade"),
			infinityUpgrade = (ItemBasic)new ItemSingleUpgrade(Enchantments.INFINITY).setRegistryName("infinity_upgrade"),
			lotsUpgrade = (ItemBasic)new ItemEnchUpgrade(Enchantments.LUCK_OF_THE_SEA).setRegistryName("lots_upgrade"),
			lureUpgrade = (ItemBasic)new ItemEnchUpgrade(Enchantments.LURE).setRegistryName("lure_upgrade")
		);
		upgrades.join(items); // copy the items list to the upgrades list
		
		// other mod items
		items.join(
			// tools and such
			advancedBow = (ItemBowAdvanced)new ItemBowAdvanced().setRegistryName("advanced_bow"),
			advancedShield = (ItemShieldAdvanced)new ItemShieldAdvanced().setRegistryName("advanced_shield"),
			
			// wooden armor
			woodenHelm = (ItemArmorAdvanced)new ItemArmorWood(EntityEquipmentSlot.HEAD).setRegistryName("wooden_helm"),
			woodenChest = (ItemArmorAdvanced)new ItemArmorWood(EntityEquipmentSlot.CHEST).setRegistryName("wooden_chest"),
			woodenLegs = (ItemArmorAdvanced)new ItemArmorWood(EntityEquipmentSlot.LEGS).setRegistryName("wooden_legs"),
			woodenBoots = (ItemArmorAdvanced)new ItemArmorWood(EntityEquipmentSlot.FEET).setRegistryName("wooden_boots"),
			
			// stone armor
			stoneHelm = (ItemArmorAdvanced)new ItemArmorStone(EntityEquipmentSlot.HEAD).setRegistryName("stone_helm"),
			stoneChest = (ItemArmorAdvanced)new ItemArmorStone(EntityEquipmentSlot.CHEST).setRegistryName("stone_chest"),
			stoneLegs = (ItemArmorAdvanced)new ItemArmorStone(EntityEquipmentSlot.LEGS).setRegistryName("stone_legs"),
			stoneBoots = (ItemArmorAdvanced)new ItemArmorStone(EntityEquipmentSlot.FEET).setRegistryName("stone_boots"),
			
			// nether fortress armor
			netherHelm = (ItemArmorAdvanced)new ItemArmorNether(EntityEquipmentSlot.HEAD).setRegistryName("nether_helm"),
			netherChest = (ItemArmorAdvanced)new ItemArmorNether(EntityEquipmentSlot.CHEST).setRegistryName("nether_chest"),
			netherLegs = (ItemArmorAdvanced)new ItemArmorNether(EntityEquipmentSlot.LEGS).setRegistryName("nether_legs"),
			netherBoots = (ItemArmorAdvanced)new ItemArmorNether(EntityEquipmentSlot.FEET).setRegistryName("nether_boots"),
			
			// obsidian armor
			obsidianHelm = (ItemArmorAdvanced)new ItemArmorObsidian(EntityEquipmentSlot.HEAD).setRegistryName("obsidian_helm"),
			obsidianChest = (ItemArmorAdvanced)new ItemArmorObsidian(EntityEquipmentSlot.CHEST).setRegistryName("obsidian_chest"),
			obsidianLegs = (ItemArmorAdvanced)new ItemArmorObsidian(EntityEquipmentSlot.LEGS).setRegistryName("obsidian_legs"),
			obsidianBoots = (ItemArmorAdvanced)new ItemArmorObsidian(EntityEquipmentSlot.FEET).setRegistryName("obsidian_boots")
		);
		
		// check the config for disabled items
		if(!ACConfig.enableAdvancedBow) items.remove(advancedBow);
		if(!ACConfig.enableAdvancedShield) items.remove(advancedShield);
		if(!ACConfig.enableWoodenArmor) items.removeAll(new JointList().join(woodenHelm, woodenChest, woodenLegs, woodenBoots));
		if(!ACConfig.enableStoneArmor) items.removeAll(new JointList().join(stoneHelm, stoneChest, stoneLegs, stoneBoots));
		if(!ACConfig.enableNetherArmor) items.removeAll(new JointList().join(netherHelm, netherChest, netherLegs, netherBoots));
		if(!ACConfig.enableObsidianArmor) items.removeAll(new JointList().join(obsidianHelm, obsidianChest, obsidianLegs, obsidianBoots));
	}
	
	/** Get the list of enchantment upgrades */
	public static List<Item> getEnchantmentUpgrades() {
		List<Item> ret = new JointList();
		for(IItemAdvanced i : upgrades) ret.add((Item)i);
		return ret;
	}
	
	/** Register the items */
	private static void registerItems() {
		RegistryHelper.registerItems(items);
	}
	
	/** Register the renderers */
	@SideOnly(Side.CLIENT)
	public static void registerRendering() {
		Swords.registerRendering();
		for(IItemAdvanced item : items) {
			item.registerRender();
		}
	}

}
