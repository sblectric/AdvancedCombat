package com.advancedcombat.swords;

import java.util.List;

import com.advancedcombat.util.JointList;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraft.item.Item.ToolMaterial;

/** Used to implement a sword that can be repaired with a list of items */
public class SwordCustomRepair extends SwordBasic {
	
	protected JointList<ItemStack> repairStacks;

	public SwordCustomRepair(ToolMaterial mat) {
		super(mat);
		repairStacks = new JointList<ItemStack>();
	}
	
	/** Set the stacks that can repair this sword */
	public SwordCustomRepair addRepairStacks(ItemStack... repair) {
		repairStacks.join(repair);
		return this;
	}
	
	/** Set the ores that can repair this sword */
	public SwordCustomRepair addRepairOres(String... repair) {
		for(String name : repair) {
			List<ItemStack> stacks = OreDictionary.getOres(name);
			repairStacks.join(stacks);
		}
		return this;
	}
	
	/** Custom repairability method */
	@Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		for(ItemStack r : repairStacks) {
			if(OreDictionary.itemMatches(r, repair, false)) return true;
		}
    	return false;
    }

}
