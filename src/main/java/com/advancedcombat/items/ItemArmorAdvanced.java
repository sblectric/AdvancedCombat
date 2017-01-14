package com.advancedcombat.items;

import com.advancedcombat.api.IItemAdvanced;
import com.advancedcombat.creativetabs.ACCreativeTabs;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

/** Superclass for armors */
public abstract class ItemArmorAdvanced extends ItemArmor implements IItemAdvanced {
	
	public ItemArmorAdvanced(ArmorMaterial mat, EntityEquipmentSlot armorType) {
		super(mat, mat.ordinal(), armorType);
		this.setCreativeTab(ACCreativeTabs.main);
	}

}
