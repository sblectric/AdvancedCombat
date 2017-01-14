package com.advancedcombat.integration.jei;

import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

import com.advancedcombat.crafting.RecipeEnchantmentUpgrade;
import com.advancedcombat.crafting.RecipeItemEnchantmentUpgrade;
import com.advancedcombat.util.ACUtils;
import com.advancedcombat.util.JointList;

/** A tiered enchantment upgrade with individual recipes for JEI to read */
public class RecipeEnchantmentUpgradeJEI {
	
	private static List<RecipeEnchantmentUpgradeJEI> allRecipes = new JointList();
	protected final ItemStack item, upgrade;
	protected final ItemStack output;

	public RecipeEnchantmentUpgradeJEI(ItemStack sword, ItemStack upgrade, ItemStack output) {
		this.item = sword;
		this.upgrade = upgrade;
		this.output = output;
	}
	
	/** Gets all the defined upgrade recipes for JEI */
	public static List<RecipeEnchantmentUpgradeJEI> getRecipeList() {
		if(allRecipes.isEmpty()) {
			for(RecipeEnchantmentUpgrade u : RecipeEnchantmentUpgrade.getAllUpgrades()) {
				List<ItemStack> enchIn = getEnchantedItems(ACUtils.getAllEnchantables(u), u, 0);
				ItemStack upgrade = u.getUpgrade();
				List<ItemStack> enchOut = getEnchantedItems(ACUtils.getAllEnchantables(u), u, 1);
				for(int i = 0; i < enchIn.size(); i++) {
					allRecipes.add(new RecipeEnchantmentUpgradeJEI(enchIn.get(i), upgrade, enchOut.get(i)));
				}
			}
		}
		return allRecipes;
	}

	/** Enchant a group of swords from min to max (expands the list greatly) */
	public static JointList<ItemStack> getEnchantedItems(List<ItemStack> allOfType, RecipeEnchantmentUpgrade recipe, int step) {
		JointList<ItemStack> enchantedItems = new JointList();
		for(ItemStack currentItem : allOfType) {
			for(int level = recipe.getMinLevelToApply() + step; level < recipe.getMaxUpgradeLevel() + step; level++) {
				ItemStack stack = currentItem.copy();
				if(level > 0) stack.addEnchantment(recipe.getEnchantment(), level);
				enchantedItems.add(stack);
			}
		}
		return enchantedItems;
	}

}
