package com.advancedcombat.integration.jei;

import java.util.LinkedList;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.advancedcombat.crafting.RecipeEnchantmentUpgrade;
import com.advancedcombat.util.ACUtils;
import com.advancedcombat.util.JointList;

public class EnchantmentUpgradeWrapper extends BlankRecipeWrapper {

	private final RecipeEnchantmentUpgradeJEI recipe;
	
	public EnchantmentUpgradeWrapper(RecipeEnchantmentUpgradeJEI recipe) {
		this.recipe = recipe;
	}

	public List getInputs() {
		return new JointList().join(recipe.item).join(recipe.upgrade);
	}

	public List getOutputs() {
		return new JointList().join(recipe.output);
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputs(ItemStack.class, getInputs());
		ingredients.setOutputs(ItemStack.class, getOutputs());
	}

}
