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

/** Stone Armor */
public class ItemArmorStone extends ItemArmorAdvanced {

	public ItemArmorStone(EntityEquipmentSlot armorType) {
		super(ACItems.stone, armorType);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flags) {
		tooltip.add(TextFormatting.GREEN + "Ecological I");
	}

	// ecological functionality
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		if(!world.isRemote) {

			// ecological I: repair 1 damage per 5 seconds when wet
			// repair 1 damage per 60 seconds when not wet
			if(armor.getItemDamage() > 0) {
				if((player.isWet() && player.ticksExisted % 100 == 0) || player.ticksExisted % 1200 == 0) {
					armor.damageItem(-1, player);
				}
			}
			
		}
	}

}
