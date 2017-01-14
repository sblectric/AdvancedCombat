package com.advancedcombat.crafting;

import java.util.List;
import java.util.Map;

import com.advancedcombat.util.ACUtils;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;

/** For upgrading anything, not just swords */
public class RecipeFlexibleUpgrade extends RecipeItemEnchantmentUpgrade {

	public RecipeFlexibleUpgrade(ItemStack upgradeItem) {
		super(upgradeItem);
	}
	
	/** Gets an upgradeable stack */
	@Override
	public ItemStack getUpgradableStack(List<ItemStack> s) {
		if(ACUtils.doesItemStackListContain(s, upgrade, true)) {
			ItemStack toUpgrade = s.get(0);
			if(enchant.canApply(toUpgrade)) { // no sword needed now!
				Map<Enchantment, Integer> activeEnchs = EnchantmentHelper.getEnchantments(toUpgrade);
				for(Enchantment i : activeEnchs.keySet()) {
					if(i == enchant) {
						int level = activeEnchs.get(i);
						if(level >= maxLevel) {
							return null;
						}
					}
				}
				return toUpgrade.copy();
			}
		}
		return null;
	}
	

}
