package com.advancedcombat.creativetabs;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ASCreativeTab extends CreativeTabs {
	
	protected ItemStack displayStack;
 
    public ASCreativeTab(String unlocalizedName) {
        super(unlocalizedName);
    }
    
    public ASCreativeTab(String unlocalizedName, ItemStack stack) {
        this(unlocalizedName);
        this.displayStack = stack;
    }
	
    public ASCreativeTab(String unlocalizedName, Item item) {
        this(unlocalizedName, new ItemStack(item));
    }
    
    public ASCreativeTab(String unlocalizedName, Block item) {
    	this(unlocalizedName, new ItemStack(item));
    }
    
    public void updateItem(ItemStack item) {
    	this.displayStack = item;
    }
    
    public void updateItem(Item item) {
    	this.updateItem(new ItemStack(item));
    }
    
    public void updateItem(Block item) {
    	this.updateItem(new ItemStack(item));
    }

    @Override
	@SideOnly(Side.CLIENT)
	public ItemStack getTabIconItem() {
		return displayStack;
	}
    
}
