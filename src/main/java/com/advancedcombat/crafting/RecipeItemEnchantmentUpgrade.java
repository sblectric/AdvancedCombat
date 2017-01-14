package com.advancedcombat.crafting;

import com.advancedcombat.items.ItemEnchUpgrade;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

/** Creates a tiered enchantment upgrade based on an ItemStack of an ItemEnchantmentUpgrade */
public class RecipeItemEnchantmentUpgrade extends RecipeTieredEnchantmentUpgrade {

	public RecipeItemEnchantmentUpgrade(ItemStack upgradeItem) {
		super(upgradeItem, ((ItemEnchUpgrade)upgradeItem.getItem()).getEnchantment(), upgradeItem.getItemDamage());
	}

}
