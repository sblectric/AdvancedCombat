package com.advancedcombat.crafting;

import com.advancedcombat.config.ACConfig;
import com.advancedcombat.init.ACBlocks;
import com.advancedcombat.init.ACItems;
import com.advancedcombat.init.Swords;
import com.advancedcombat.items.ItemEnchUpgrade;
import com.advancedcombat.items.ItemSingleUpgrade;
import com.advancedcombat.ref.RefStrings;
import com.advancedcombat.util.JointList;
import com.advancedcombat.util.StackHelper;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;

public class ACCraftingManager {
	
	public static void mainRegistry() {
		addOreDictEntries();
		addCraftingRecipes();
	}
	
	// tiers of upgrades
	public static final int TIER_1 = 0;
	public static final int TIER_2 = 1;
	
	private static void addOreDictEntries() {
		OreDictionary.registerOre("blockNetherStar", new ItemStack(ACBlocks.netherStarBlock));
		OreDictionary.registerOre("skullSkeleton", new ItemStack(Items.SKULL, 1, 0));
		OreDictionary.registerOre("skullSkeleton", new ItemStack(Items.SKULL, 1, 1));
	}
	
	private static void addCraftingRecipes() {
		// blocks
		RecipeHelper.addShapedOreRecipe(new ItemStack(ACBlocks.advancedStone, 1), "IXI","XIX","IXI", 'X',StackHelper.anyStone, 'I',"cobblestone");
		GameRegistry.addRecipe(new ItemStack(ACBlocks.netherStarBlock, 1), "XXX","XXX","XXX", 'X',Items.NETHER_STAR);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.NETHER_STAR, 9), new ItemStack(ACBlocks.netherStarBlock, 1));
		
		// advanced nether brick
		RecipeHelper.addShapedOreRecipe(new ItemStack(ACBlocks.netherFortressBlock), "IXI","XIX","IXI", 'X',Blocks.IRON_BARS, 'I',Blocks.NETHER_BRICK);
		RecipeHelper.addShapedOreRecipe(new ItemStack(ACBlocks.reinforcedObsidian), "IXI","XIX","IXI", 'X',ACBlocks.advancedStone, 'I',Blocks.OBSIDIAN);
		
		// advanced bow
		if(ACConfig.enableAdvancedBow) RecipeHelper.addShapedOreRecipe(new ItemStack(ACItems.advancedBow), "XIX","XAX","XIX", 
				'X',"logWood", 'I',"blockIron", 'A',Items.BOW);
		
		// advanced shield
		if(ACConfig.enableAdvancedShield) RecipeHelper.addShapedOreRecipe(new ItemStack(ACItems.advancedShield), "XIX","XAX","XIX", 
				'X',"logWood", 'I',"blockIron", 'A',Items.SHIELD);
		
		// tracking dispenser
		if(ACConfig.enableTrackingDispenser) RecipeHelper.addShapedOreRecipe(new ItemStack(ACBlocks.trackingDispenser), "XCX","RAR","XBX", 
				'X',Blocks.TRIPWIRE_HOOK, 'C',Items.COMPARATOR, 'R',"dustRedstone", 'B',"blockRedstone", 'A',Blocks.DISPENSER);
		
		// wooden armor
		if(ACConfig.enableWoodenArmor) {
			RecipeHelper.addShapedOreRecipe(new ItemStack(ACItems.woodenHelm), "XXX","X X", 'X',"logWood");
			RecipeHelper.addShapedOreRecipe(new ItemStack(ACItems.woodenChest), "X X","XXX","XXX", 'X',"logWood");
			RecipeHelper.addShapedOreRecipe(new ItemStack(ACItems.woodenLegs), "XXX","X X","X X", 'X',"logWood");
			RecipeHelper.addShapedOreRecipe(new ItemStack(ACItems.woodenBoots), "X X","X X", 'X',"logWood");
		}
		
		// stone armor
		if(ACConfig.enableStoneArmor) {
			RecipeHelper.addShapedOreRecipe(new ItemStack(ACItems.stoneHelm), "AXA","X X", 'X',"stone", 'A',ACBlocks.advancedStone);
			RecipeHelper.addShapedOreRecipe(new ItemStack(ACItems.stoneChest), "X X","XAX","XAX", 'X',"stone", 'A',ACBlocks.advancedStone);
			RecipeHelper.addShapedOreRecipe(new ItemStack(ACItems.stoneLegs), "AXA","X X","X X", 'X',"stone", 'A',ACBlocks.advancedStone);
			RecipeHelper.addShapedOreRecipe(new ItemStack(ACItems.stoneBoots), "A A","X X", 'X',"stone", 'A',ACBlocks.advancedStone);
		}
		
		// nether fortress armor
		if(ACConfig.enableNetherArmor) {
			RecipeHelper.addShapedOreRecipe(new ItemStack(ACItems.netherHelm),
					"AXA","F F", 'X',Blocks.NETHER_BRICK, 'A',ACBlocks.netherFortressBlock, 'F',Blocks.NETHER_BRICK_FENCE);
			RecipeHelper.addShapedOreRecipe(new ItemStack(ACItems.netherChest),
					"F F","XAX","XAX", 'X',Blocks.NETHER_BRICK, 'A',ACBlocks.netherFortressBlock, 'F',Blocks.NETHER_BRICK_FENCE);
			RecipeHelper.addShapedOreRecipe(new ItemStack(ACItems.netherLegs), "AXA","X X","F F", 
					'X',Blocks.NETHER_BRICK, 'A',ACBlocks.netherFortressBlock, 'F',Blocks.NETHER_BRICK_FENCE);
			RecipeHelper.addShapedOreRecipe(new ItemStack(ACItems.netherBoots), "A A","F F", 
					'A',ACBlocks.netherFortressBlock, 'F',Blocks.NETHER_BRICK_FENCE);
		}
		
		// obsidian armor
		if(ACConfig.enableObsidianArmor) {
			RecipeHelper.addShapedOreRecipe(new ItemStack(ACItems.obsidianHelm), "AXA","X X", 'X',Blocks.OBSIDIAN, 'A',ACBlocks.reinforcedObsidian);
			RecipeHelper.addShapedOreRecipe(new ItemStack(ACItems.obsidianChest), "X X","XAX","XAX", 'X',Blocks.OBSIDIAN, 'A',ACBlocks.reinforcedObsidian);
			RecipeHelper.addShapedOreRecipe(new ItemStack(ACItems.obsidianLegs), "AXA","X X","X X", 'X',Blocks.OBSIDIAN, 'A',ACBlocks.reinforcedObsidian);
			RecipeHelper.addShapedOreRecipe(new ItemStack(ACItems.obsidianBoots), "A A","X X", 'X',Blocks.OBSIDIAN, 'A',ACBlocks.reinforcedObsidian);
		}
		
		// items
		if(ACConfig.enableEnchantmentUpgrades) {
			
			// Tier 1 recipes
			addTier1Recipe(ACItems.sharpnessUpgrade, 
					"IXI","XAX","IXI", 'X',Items.FLINT, 'A',Items.IRON_SWORD);
			addTier1Recipe(ACItems.smiteUpgrade, 
					"IXI","XAX","IXI", 'X',Items.ROTTEN_FLESH, 'A',Items.BONE);
			addTier1Recipe(ACItems.spiderUpgrade, 
					"IXI","XAX","IXI", 'X',Items.SPIDER_EYE, 'A',Items.STRING);
			addTier1Recipe(ACItems.knockbackUpgrade, 
					"IXI","XAX","IXI", 'X',Blocks.PISTON, 'A',"blockSlime");
			addTier1Recipe(ACItems.fireAspectUpgrade, 
					"IXI","XAX","IXI", 'X',Items.FIRE_CHARGE, 'A',"blockCoal");
			addTier1Recipe(ACItems.lootingUpgrade, 
					"IXI","XAX","IXI", 'X',"ingotGold", 'A',Items.GOLDEN_SWORD);
			addTier1Recipe(ACItems.unbreakingUpgrade, 
					"IXI","XAX","IXI", 'X',Blocks.OBSIDIAN, 'A',Items.DIAMOND_CHESTPLATE);
			addTier1Recipe(ACItems.mendingUpgrade, 
					"IXI","XAX","IXI", 'X',Blocks.ANVIL, 'A',Items.CHORUS_FRUIT_POPPED);
			addTier1Recipe(ACItems.protectionUpgrade, 
					"IXI","XAX","IXI", 'X',Items.LEATHER_CHESTPLATE, 'A',Items.SHIELD);
			addTier1Recipe(ACItems.fireProtectionUpgrade, 
					"IXI","XAX","IXI", 'X',Items.NETHERBRICK, 'A',Items.FLINT_AND_STEEL);
			addTier1Recipe(ACItems.blastProtectionUpgrade, 
					"IXI","XAX","IXI", 'X',Blocks.OBSIDIAN, 'A',Blocks.TNT);
			addTier1Recipe(ACItems.projectileProtectionUpgrade, 
					"IXI","XAX","IXI", 'X',Items.ARROW, 'A',Items.BOW);
			addTier1Recipe(ACItems.featherFallingUpgrade, 
					"IXI","XAX","IXI", 'X',Items.FEATHER, 'A',Blocks.HAY_BLOCK);
			addTier1Recipe(ACItems.respirationUpgrade, 
					"IXI","XAX","IXI", 'X',new ItemStack(Items.FISH, 1, 3), 'A',Items.GLASS_BOTTLE);
			addTier1Recipe(ACItems.aquaAffinityUpgrade, 
					"IXI","XAX","IXI", 'X',new ItemStack(Items.FISH, 1, 3), 'A',Items.IRON_PICKAXE);
			addTier1Recipe(ACItems.thornsUpgrade, 
					"IXI","XAX","IXI", 'X',Blocks.CACTUS, 'A',Items.FLINT);
			addTier1Recipe(ACItems.depthStriderUpgrade, 
					"IXI","XAX","IXI", 'X',Items.LEATHER_BOOTS, 'A',Items.WATER_BUCKET);
			addTier1Recipe(ACItems.frostWalkerUpgrade, 
					"IXI","XAX","IXI", 'X',Items.GOLDEN_BOOTS, 'A',Blocks.ICE);
			addTier1Recipe(ACItems.efficiencyUpgrade, 
					"IXI","XAX","IXI", 'X',"dustRedstone", 'A',Items.DIAMOND_PICKAXE);
			addTier1Recipe(ACItems.silkTouchUpgrade, 
					"IXI","XAX","IXI", 'X',Items.STRING, 'A',Items.GOLDEN_PICKAXE);
			addTier1Recipe(ACItems.fortuneUpgrade, 
					"IXI","XAX","IXI", 'X',"gemDiamond", 'A',"blockGold");
			addTier1Recipe(ACItems.powerUpgrade, 
					"IXI","XAX","IXI", 'X',Items.ARROW, 'A',Items.FLINT);
			addTier1Recipe(ACItems.punchUpgrade, 
					"IXI","XAX","IXI", 'X',Items.ARROW, 'A',Blocks.PISTON);
			addTier1Recipe(ACItems.flameUpgrade, 
					"IXI","XAX","IXI", 'X',Items.BLAZE_ROD, 'A',"blockCoal");
			addTier1Recipe(ACItems.infinityUpgrade, 
					"IXI","XAX","IXI", 'X',Items.BOW, 'A',Items.GOLDEN_APPLE);
			addTier1Recipe(ACItems.lotsUpgrade, 
					"IXI","XAX","IXI", 'X',Items.FISHING_ROD, 'A',Items.RABBIT_FOOT);
			addTier1Recipe(ACItems.lureUpgrade, 
					"IXI","XAX","IXI", 'X',Items.FISHING_ROD, 'A',new ItemStack(Items.FISH, 1, OreDictionary.WILDCARD_VALUE));
			
			// Tier 2 recipes
			addTier2Recipe(ACItems.sharpnessUpgrade, 
					"IXI","XAX","IXI", 'X',Items.DIAMOND_SWORD, 'I',Items.PRISMARINE_SHARD);
			addTier2Recipe(ACItems.smiteUpgrade, 
					"IXI","XAX","IXI", 'X',new ItemStack(Items.SKULL, 1, 2), 'I',"skullSkeleton");
			addTier2Recipe(ACItems.spiderUpgrade, 
					"IXI","XAX","IXI", 'X',Items.FERMENTED_SPIDER_EYE, 'I',Blocks.WEB);
			addTier2Recipe(ACItems.knockbackUpgrade, 
					"IXI","XAX","IXI", 'X',Blocks.STICKY_PISTON, 'I',"blockSlime");
			addTier2Recipe(ACItems.fireAspectUpgrade, 
					"IXI","XAX","IXI", 'X',Blocks.COAL_BLOCK, 'I',Items.BLAZE_ROD);
			addTier2Recipe(ACItems.lootingUpgrade, 
					"IXI","XAX","IXI", 'X',"blockGold", 'I',"blockLapis");
			addTier2Recipe(ACItems.unbreakingUpgrade, 
					"IXI","XAX","IXI", 'X',Items.GHAST_TEAR, 'I',"blockDiamond");
			addTier2Recipe(ACItems.protectionUpgrade, 
					"IXI","XAX","IXI", 'X',Items.DIAMOND_CHESTPLATE, 'I',Blocks.OBSIDIAN);
			addTier2Recipe(ACItems.fireProtectionUpgrade, 
					"IXI","XAX","IXI", 'X',Items.FIRE_CHARGE, 'I',Blocks.OBSIDIAN);
			addTier2Recipe(ACItems.blastProtectionUpgrade, 
					"IXI","XAX","IXI", 'X',Blocks.TNT, 'I',new ItemStack(Items.SKULL, 1, 4));
			addTier2Recipe(ACItems.projectileProtectionUpgrade, 
					"IXI","XAX","IXI", 'X',Items.BOW, 'I',Blocks.OBSIDIAN);
			addTier2Recipe(ACItems.featherFallingUpgrade, 
					"IXI","XAX","IXI", 'X',Blocks.HAY_BLOCK, 'I',Items.BED);
			addTier2Recipe(ACItems.thornsUpgrade, 
					"IXI","XAX","IXI", 'X',Blocks.IRON_BARS, 'I',Blocks.CACTUS);
			addTier2Recipe(ACItems.frostWalkerUpgrade, 
					"IXI","XAX","IXI", 'X',Items.DIAMOND_BOOTS, 'I',Blocks.PACKED_ICE);
			addTier2Recipe(ACItems.efficiencyUpgrade, 
					"IXI","XAX","IXI", 'X',"blockRedstone", 'I',"blockDiamond");
			addTier2Recipe(ACItems.fortuneUpgrade, 
					"IXI","XAX","IXI", 'X',"blockGold", 'I',"blockDiamond");
			addTier2Recipe(ACItems.powerUpgrade, 
					"IXI","XAX","IXI", 'X',Items.ARROW, 'I',"blockIron");
			addTier2Recipe(ACItems.punchUpgrade, 
					"IXI","XAX","IXI", 'X',Blocks.PISTON, 'I',"blockSlime");
			addTier2Recipe(ACItems.lotsUpgrade, 
					"IXI","XAX","IXI", 'X',Items.RABBIT_FOOT, 'I',"gemEmerald");
			addTier2Recipe(ACItems.lureUpgrade, 
					"IXI","XAX","IXI", 'X',new ItemStack(Items.FISH, 1, OreDictionary.WILDCARD_VALUE), 'I',Items.POISONOUS_POTATO);
			
		}
		
		// swords
		ItemStack emeraldBase = GameRegistry.makeItemStack(ACConfig.emeraldSwordBase, 0, 1, null);
		if(emeraldBase == null) emeraldBase = new ItemStack(Items.GOLDEN_SWORD);
		RecipeHelper.addShapedOreRecipe(new ItemStack(Swords.advancedWoodenSword, 1), "XXX","XIX","XXX", 'X',"logWood", 'I',Items.WOODEN_SWORD);
		RecipeHelper.addShapedOreRecipe(new ItemStack(Swords.advancedStoneSword, 1), "XXX","XIX","XXX", 'X',ACBlocks.advancedStone, 'I',Items.STONE_SWORD);
		RecipeHelper.addShapedOreRecipe(new ItemStack(Swords.advancedIronSword, 1), "XXX","XIX","XXX", 'X',"blockIron", 'I',Items.IRON_SWORD);
		RecipeHelper.addShapedOreRecipe(new ItemStack(Swords.advancedGoldenSword, 1), "XXX","XIX","XXX", 'X',"blockGold", 'I',Items.GOLDEN_SWORD);
		RecipeHelper.addShapedOreRecipe(new ItemStack(Swords.advancedEmeraldSword, 1), "XXX","XIX","XXX", 'X',"blockEmerald", 'I',emeraldBase);
		RecipeHelper.addShapedOreRecipe(new ItemStack(Swords.advancedDiamondSword, 1), "XXX","XIX","XXX", 'X',"blockDiamond", 'I',Items.DIAMOND_SWORD);
		RecipeHelper.addShapedOreRecipe(new ItemStack(Swords.netherStarSword, 1), "X","X","I", 'X',Items.NETHER_STAR, 'I',"stickWood");
		RecipeHelper.addShapedOreRecipe(new ItemStack(Swords.advancedNetherStarSword, 1),
				" B ","ZIZ"," X ", 'X',"blockNetherStar", 'I',Swords.netherStarSword, 'Z',new ItemStack(Items.GOLDEN_APPLE, 1, 1), 'B',Blocks.BEACON);
		
		// upgrades (NEW!)
		if(ACConfig.enableEnchantmentUpgrades) {
			RecipeSorter.register(RefStrings.MODID + ":item_enchantment_upgrade", RecipeItemEnchantmentUpgrade.class, Category.SHAPED, "after:minecraft:shaped");
			for(Item upgrade : ACItems.getEnchantmentUpgrades()) addFlexibleUpgradeRecipe(upgrade);
		}
	
	}
	
	/** Adds a tier 1 recipe with 'I' set to paper */
	private static void addTier1Recipe(Item result, Object... recipe) {
		JointList rec = new JointList().join(recipe).join('I', Items.PAPER);
		RecipeHelper.addShapedOreRecipe(new ItemStack(result, 1, TIER_1), rec.toArray(new Object[recipe.length]));
	}
	
	/** Adds a tier 2 recipe with 'A' set to the tier 1 result */
	private static void addTier2Recipe(Item result, Object... recipe) {
		JointList rec = new JointList().join(recipe).join('A', new ItemStack(result, 1, TIER_1));
		RecipeHelper.addShapedOreRecipe(new ItemStack(result, 1, TIER_2), rec.toArray(new Object[recipe.length]));
	}
	
	/** Registers the tier 1 and tier 2 enchantment upgrade recipes for the specified item */
	private static void addFlexibleUpgradeRecipe(Item upgrade) {
		for(int i = 0; i < (((ItemEnchUpgrade)upgrade).getEnchantment().getMaxLevel() == 1 || 
				upgrade instanceof ItemSingleUpgrade ? 1 : ItemEnchUpgrade.TIER_COUNT); i++) {
			GameRegistry.addRecipe(new RecipeFlexibleUpgrade(new ItemStack(upgrade, 1, i)));
		}
	}

}
