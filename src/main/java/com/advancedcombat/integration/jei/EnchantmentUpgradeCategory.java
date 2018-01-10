package com.advancedcombat.integration.jei;

import javax.annotation.Nonnull;

import com.advancedcombat.ref.RefStrings;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.ICraftingGridHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import mezz.jei.util.Log;
import mezz.jei.util.Translator;
import net.minecraft.util.ResourceLocation;

/** Enchantment upgrade recipe type */
public class EnchantmentUpgradeCategory extends BlankRecipeCategory {

	private static final int craftOutputSlot = 0;
	private static final int craftInputSlot1 = 1;
	public static final String UID = RefStrings.MODID + ":enchantment_upgrade_category";

	@Nonnull private final IDrawable background;
	@Nonnull private final ICraftingGridHelper craftingGridHelper;

	public EnchantmentUpgradeCategory(IGuiHelper guiHelper) {
		ResourceLocation location = new ResourceLocation("minecraft", "textures/gui/container/crafting_table.png");
		background = guiHelper.createDrawable(location, 29, 16, 116, 54);
		craftingGridHelper = guiHelper.createCraftingGridHelper(craftInputSlot1, craftOutputSlot);
	}

	/** Get the recipe ID */
	@Override
	public String getUid() {
		return UID;
	}

	/** Get the recipe name */
	@Override
	public String getTitle() {
		return "Enchantment Upgrade";
	}

	/** Get the background */
	@Override
	@Nonnull
	public IDrawable getBackground() {
		return background;
	}

	/** Set the recipe layout */
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		guiItemStacks.init(craftOutputSlot, false, 94, 18);

		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 3; ++x) {
				int index = craftInputSlot1 + x + (y * 3);
				guiItemStacks.init(index, true, x * 18, y * 18);
			}
		}

		if(recipeWrapper instanceof EnchantmentUpgradeWrapper) {
			guiItemStacks.set(ingredients);
		}
	}

	@Override
	public String getModName() {
		return RefStrings.NAME;
	}

}
