package com.advancedcombat.integration.jei;

import com.advancedcombat.crafting.RecipeEnchantmentUpgrade;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class EnchantmentUpgradeHandler implements IRecipeHandler<RecipeEnchantmentUpgradeJEI>{

	@Override
	public Class<RecipeEnchantmentUpgradeJEI> getRecipeClass() {
		return RecipeEnchantmentUpgradeJEI.class;
	}

	@Override
	public String getRecipeCategoryUid(RecipeEnchantmentUpgradeJEI r) {
		return EnchantmentUpgradeCategory.UID;
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(RecipeEnchantmentUpgradeJEI recipe) {
		return new EnchantmentUpgradeWrapper(recipe);
	}

	@Override
	public boolean isRecipeValid(RecipeEnchantmentUpgradeJEI recipe) {
		return !recipe.item.isEmpty() && !recipe.upgrade.isEmpty() && !recipe.output.isEmpty();
	}

}
