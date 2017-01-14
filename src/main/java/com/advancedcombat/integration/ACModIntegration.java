package com.advancedcombat.integration;

import net.minecraftforge.fml.common.Loader;

public class ACModIntegration {
	
	public static boolean thaumcraftExists = Loader.isModLoaded("Thaumcraft");
	
	/** Mods that need integration in the pre-init phase */
	public static void preInit() {
		
	}
	
	/** Mods that need integration in the init phase */
	public static void onInit() {
	}
	
	/** Mods that need integration in the post-init phase */
	public static void postInit() {
		// Thaumcraft
//		if(thaumcraftExists) {
//			ThaumcraftIntegration.mainRegistry();
//			Log.logger.info("Thaumcraft integration complete.");
//		} else {
//			Log.logger.info("Thaumcraft not found, skipping integration.");
//		}
	}

}
