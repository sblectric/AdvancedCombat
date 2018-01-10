package com.advancedcombat.integration.jei;

import com.advancedcombat.api.ISwordAdvanced;
import com.advancedcombat.config.ACConfig;
import com.advancedcombat.init.Swords;
import com.advancedcombat.ref.Log;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@mezz.jei.api.JEIPlugin
public class JEIPlugin implements IModPlugin {
	
	/** Register this mod plugin with the mod registry. */
	@Override
	public void register(IModRegistry reg) {
		IJeiHelpers jeiHelpers = reg.getJeiHelpers();
		
		// hide the addon swords when the mods aren't available
		for(ISwordAdvanced item : Swords.getRegisteredSwords()) {
			if(((Item)item).getCreativeTab() == null)
				jeiHelpers.getIngredientBlacklist().addIngredientToBlacklist(new ItemStack((Item)item));
		}
		
		// don't do integration if disabled
		if(ACConfig.enableEnchantmentUpgrades && ACConfig.jeiIntegration) {
			// register the categories
			reg.addRecipeCategories(
					new EnchantmentUpgradeCategory(jeiHelpers.getGuiHelper())
			);
			
			// and the handlers
			reg.addRecipeHandlers(
					new EnchantmentUpgradeHandler()
			);
			
			
			// add the recipes
			reg.addRecipes(RecipeEnchantmentUpgradeJEI.getRecipeList());
			
			Log.logger.info("JEI integration complete.");
		} else {
			Log.logger.info("JEI integration complete, enchantment upgrade recipes disabled.");
		}
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime arg0) {}

	@Override
	public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {}

	@Override
	public void registerIngredients(IModIngredientRegistration registry) {}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {}

}
