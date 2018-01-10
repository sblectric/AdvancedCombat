package com.advancedcombat.proxy;

import com.advancedcombat.events.InformationHandler;
import com.advancedcombat.init.ACBlocks;
import com.advancedcombat.init.ACItems;
import com.advancedcombat.registry.ClientRegistryHelper;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/** Client-only initialization code */
public class ClientProxy extends CommonProxy {
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		MinecraftForge.EVENT_BUS.register(new ClientRegistryHelper());
	}
	
	@Override
	public void onInit(FMLInitializationEvent event) {
		super.onInit(event);
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
		MinecraftForge.EVENT_BUS.register(new InformationHandler());
	}
	
	@Override
	public void registerModels() {
		super.registerModels();
		ACBlocks.registerRendering();
		ACItems.registerRendering();
	}

}
