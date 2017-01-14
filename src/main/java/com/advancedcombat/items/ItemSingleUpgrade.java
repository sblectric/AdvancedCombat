package com.advancedcombat.items;

import net.minecraft.enchantment.Enchantment;

/** Enchantment upgrade with only a tier 1 version */
public class ItemSingleUpgrade extends ItemEnchUpgrade {
	
	public ItemSingleUpgrade(Enchantment enchant) {
		super(enchant, true);
	}

}
