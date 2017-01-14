package com.advancedcombat.items;

import com.advancedcombat.api.IItemAdvanced;
import com.advancedcombat.creativetabs.ACCreativeTabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Still glitchy, but it works */
public class ItemShieldAdvanced extends ItemShield implements IItemAdvanced {
	
	public ItemShieldAdvanced() {
		super();	
		this.setMaxDamage(1536);
		this.setCreativeTab(ACCreativeTabs.main);
	}
	
	// damage it
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		stack.damageItem(1, entityLiving);
		return stack;
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return "Advanced " + super.getItemStackDisplayName(stack);
	}
	
    @SideOnly(Side.CLIENT)
    @Override
    public CreativeTabs getCreativeTab() {
        return ACCreativeTabs.main;
    }
	
	@Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Item.getItemFromBlock(Blocks.IRON_BLOCK);
    }

}
