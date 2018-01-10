package com.advancedcombat.swords;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Sword that wreaks havoc in creative mode, but normal players cannot abuse it */
public class SwordCreative extends SwordBasic {

	public SwordCreative(ToolMaterial material) {
		super(material);
	}
	
	@Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		if(!player.isCreative()) return super.onLeftClickEntity(stack, player, entity);
		if(entity instanceof EntityLivingBase) ((EntityLivingBase)entity).onDeath(DamageSource.causePlayerDamage(player));
		entity.setDead(); // bypasses all invulerabilities
    	return false; // bypass the damaging mechanic
    }
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if(!(attacker instanceof EntityPlayer) || !((EntityPlayer)attacker).isCreative()) return super.hitEntity(stack, target, attacker);
		target.setDead(); // bypasses all invulerabilities
		return true; // bypass the damaging mechanic
	}
	
	@Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(!(entityIn instanceof EntityPlayer) || !((EntityPlayer)entityIn).isCreative()) {
			super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
		} else {
			if(stack.getItemDamage() > 0) stack.setItemDamage(0); // never gets damaged
		}
    }
	
	@Override
    public boolean canDestroyBlockInCreative(World world, BlockPos pos, ItemStack stack, EntityPlayer player) {
        return true;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if(playerIn.isCreative()) {
			tooltip.add("\u00A7aActive");
		} else {
			tooltip.add("\u00A74Inactive");
		}
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasEffect(ItemStack blah) {
		try {
			return Minecraft.getMinecraft().player.isCreative();
		} catch (Exception e) {
			return false;
		}
	}

}
