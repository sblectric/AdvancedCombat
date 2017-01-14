package com.advancedcombat.swords;

import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.advancedcombat.api.ISwordAdvanced;
import com.advancedcombat.creativetabs.ACCreativeTabs;
import com.advancedcombat.ref.RefStrings;

/** Used to implement a basic sword */
public class SwordBasic extends ItemSword implements ISwordAdvanced {

	public SwordBasic(ToolMaterial material) {
		super(material);
		this.setCreativeTab(ACCreativeTabs.main);
	}

}
