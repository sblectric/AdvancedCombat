package com.advancedcombat.items;

import com.advancedcombat.api.IItemAdvanced;
import com.advancedcombat.creativetabs.ACCreativeTabs;

import net.minecraft.item.Item;

/** Used to implement a basic item */
public class ItemBasic extends Item implements IItemAdvanced {
	
	public ItemBasic() {
		this.setCreativeTab(ACCreativeTabs.main);
	}
	
}
