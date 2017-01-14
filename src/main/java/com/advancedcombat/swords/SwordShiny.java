package com.advancedcombat.swords;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.minecraft.item.Item.ToolMaterial;

/** A sword that shimmers */
public class SwordShiny extends SwordBasic {

	public SwordShiny(ToolMaterial material) {
		super(material);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasEffect(ItemStack blah) {
		return true;
	}

}
