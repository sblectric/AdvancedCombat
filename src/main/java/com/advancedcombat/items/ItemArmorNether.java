package com.advancedcombat.items;

import java.util.List;

import com.advancedcombat.init.ACItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Nether Fortress armor */
public class ItemArmorNether extends ItemArmorAdvanced {

	public ItemArmorNether(EntityEquipmentSlot armorType) {
		super(ACItems.nether, armorType);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add(TextFormatting.GOLD + "Brick Wall");
		tooltip.add(TextFormatting.BLUE + "Blastproof I");
	}

}
