package com.advancedcombat.proxy;

import com.advancedcombat.config.ACConfig;
import com.advancedcombat.creativetabs.ACCreativeTabs;
import com.advancedcombat.events.ArmorHandler;
import com.advancedcombat.events.CustomShieldHandler;
import com.advancedcombat.init.Swords;
import com.advancedcombat.integration.ACModIntegration;
import com.advancedcombat.registry.RegistryHelper;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/** Mod common initialization code */
public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new RegistryHelper());
		ACConfig.mainRegistry(event.getSuggestedConfigurationFile());
		ACCreativeTabs.mainRegistry();
		//ACBlocks.mainRegistry();
		//ACItems.mainRegistry();
		ACModIntegration.preInit();
	}
	
	public void onInit(FMLInitializationEvent event) {
		//ACCraftingManager.mainRegistry();
		ACCreativeTabs.updateCreativeTabs();
		ACModIntegration.onInit();
	}
	
	public void postInit(FMLPostInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new ArmorHandler());
		MinecraftForge.EVENT_BUS.register(new CustomShieldHandler());
		ACModIntegration.postInit();
		Swords.finalizeSwords();
	}
	
	public void registerModels() {}
			
}
