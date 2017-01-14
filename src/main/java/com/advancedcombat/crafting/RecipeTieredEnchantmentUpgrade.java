package com.advancedcombat.crafting;

import com.advancedcombat.init.ACItems;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

public abstract class RecipeTieredEnchantmentUpgrade extends RecipeEnchantmentUpgrade {
	
	public RecipeTieredEnchantmentUpgrade(ItemStack upgrade, Enchantment toUpgrade, int tier) {
		super(upgrade, toUpgrade, tier * toUpgrade.getMaxLevel(), (tier + 1) * toUpgrade.getMaxLevel());
	}
	
}
