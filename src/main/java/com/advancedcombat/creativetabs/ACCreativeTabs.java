package com.advancedcombat.creativetabs;

import com.advancedcombat.init.Swords;

import net.minecraft.creativetab.CreativeTabs;

public class ACCreativeTabs {
	
	public static CreativeTabs main;
	
	public static void mainRegistry() {
		registerCreativeTabs();
	}
	
	private static void registerCreativeTabs() {
		main = new ASCreativeTab("asMain");
	}
	
	public static void updateCreativeTabs() {
		((ASCreativeTab)main).updateItem(Swords.advancedNetherStarSword);
	}

}
