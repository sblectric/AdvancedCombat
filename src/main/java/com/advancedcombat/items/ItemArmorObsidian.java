package com.advancedcombat.items;

import java.util.List;

import com.advancedcombat.init.ACItems;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Obsidian Armor */
public class ItemArmorObsidian extends ItemArmorAdvanced {

	public ItemArmorObsidian(EntityEquipmentSlot armorType) {
		super(ACItems.obsidian, armorType);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flags) {
		tooltip.add(TextFormatting.BLUE + "Blastproof II");
	}

}
