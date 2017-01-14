package com.advancedcombat.proxy;

import com.advancedcombat.init.ACBlocks;
import com.advancedcombat.init.ACItems;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/** Client-only initialization code */
public class ClientProxy extends CommonProxy {
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		ACBlocks.registerRendering();
		ACItems.registerRendering();
	}
	
	@Override
	public void onInit(FMLInitializationEvent event) {
		super.onInit(event);
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}

}
