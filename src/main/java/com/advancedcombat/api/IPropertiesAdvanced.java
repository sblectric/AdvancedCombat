package com.advancedcombat.api;

import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IPropertiesAdvanced {
	
	/** register this item with the renderer */
	@SideOnly(Side.CLIENT)
	public void registerRender();
	
}
