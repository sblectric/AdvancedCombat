package com.advancedcombat.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.advancedcombat.api.IBlockAdvanced;
import com.advancedcombat.creativetabs.ACCreativeTabs;
import com.advancedcombat.ref.RefStrings;

/** Used to implement a basic block */
public class BlockBasic extends Block implements IBlockAdvanced {

	public BlockBasic(Material mat, float hardness, float resistance) {
		super(mat);
		this.setHardness(hardness);
		this.setResistance(resistance);
		this.setCreativeTab(ACCreativeTabs.main);
	}
	
}
