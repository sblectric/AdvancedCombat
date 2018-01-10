package com.advancedcombat.api;

import com.advancedcombat.registry.ClientRegistryHelper;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Interface to define simple methods for items to use */
public interface IItemAdvanced extends IPropertiesAdvanced {
	
	@SideOnly(Side.CLIENT)
	@Override
	public default void registerRender() {
		ClientRegistryHelper.registerModel((Item)this, 0, new ModelResourceLocation(((Item)this).getRegistryName(), "inventory"));
	}
	
}
