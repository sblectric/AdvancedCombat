package com.advancedcombat.items;

import com.advancedcombat.creativetabs.ACCreativeTabs;
import com.advancedcombat.util.JointList;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMeta extends ItemBasic {
	
	public int nSubItems;
	public boolean sameIcon;
	public boolean[] hasEffect;
	
	public ItemMeta(int nSubItems, boolean sameIcon, boolean... hasEffect) {
		super();
		this.nSubItems = nSubItems;
		this.sameIcon = sameIcon;
		this.hasSubtypes = true;
		this.hasEffect = hasEffect;
	}
	
	public ItemMeta(int nSubItems) {
		this(nSubItems, false, false);
	}
	
	/** Get the sub items as a list for the creative tab */
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList sub) {
		if(this.isInCreativeTab(tab)) {
			for(int meta = 0; meta < nSubItems; meta++) {
				sub.add(new ItemStack(this, 1, meta));
			}
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
	    return this.getUnlocalizedName() + "_" + stack.getItemDamage();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasEffect(ItemStack stack) {
		int index = stack.getItemDamage();
		return hasEffect[Math.min(index, hasEffect.length - 1)];
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerRender() {
		JointList<ResourceLocation> names = new JointList();
		if(sameIcon) {
			for(int meta = 0; meta < nSubItems; meta++) {
				ModelLoader.setCustomModelResourceLocation(this, meta, new ModelResourceLocation(this.getRegistryName(), "inventory"));
			}
		} else {
			for(int meta = 0; meta < nSubItems; meta++) {
				ModelLoader.setCustomModelResourceLocation(this, meta, new ModelResourceLocation(this.getRegistryName() + "_" + meta, "inventory"));
			}
		}
	}

}
