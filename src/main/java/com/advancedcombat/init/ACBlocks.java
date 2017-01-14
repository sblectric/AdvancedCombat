package com.advancedcombat.init;

import com.advancedcombat.api.IBlockAdvanced;
import com.advancedcombat.blocks.BlockBasic;
import com.advancedcombat.blocks.BlockTrackingDispenser;
import com.advancedcombat.config.ACConfig;
import com.advancedcombat.creativetabs.ACCreativeTabs;
import com.advancedcombat.registry.RegistryHelper;
import com.advancedcombat.util.JointList;

import net.minecraft.block.material.Material;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Block initialization class */
public class ACBlocks {
	
	private static JointList<IBlockAdvanced> blocks;
	
	public static void mainRegistry() {
		blocks = new JointList();
		addBlocks();
		registerBlocks();
	}
	
	// the blocks
	public static BlockBasic advancedStone;
	public static BlockBasic netherStarBlock;
	public static BlockBasic netherFortressBlock;
	public static BlockBasic reinforcedObsidian;
	public static BlockTrackingDispenser trackingDispenser;
	
	/** Add blocks to the game */
	private static void addBlocks() {
		blocks.join(
			advancedStone = (BlockBasic)new BlockBasic(Material.ROCK, 3, 10).setRegistryName("advanced_stone").setCreativeTab(ACCreativeTabs.main),
			netherStarBlock = (BlockBasic)new BlockBasic(Material.ROCK, 6, 50).setRegistryName("nether_star_block").setCreativeTab(ACCreativeTabs.main),
			netherFortressBlock = (BlockBasic)new BlockBasic(Material.ROCK, 5, 50).setRegistryName("nether_fortress_block").setCreativeTab(ACCreativeTabs.main),
			reinforcedObsidian = (BlockBasic)new BlockBasic(Material.ROCK, 50, 15000).setRegistryName("reinforced_obsidian").setCreativeTab(ACCreativeTabs.main),
			trackingDispenser = (BlockTrackingDispenser)new BlockTrackingDispenser().setRegistryName("tracking_dispenser").setCreativeTab(ACCreativeTabs.main)
		);
		if(!ACConfig.enableTrackingDispenser) blocks.remove(trackingDispenser);
	}
	
	/** Register the blocks */
	private static void registerBlocks() {
		RegistryHelper.registerBlocks(blocks);
	}
	
	/** Register the renderers */
	@SideOnly(Side.CLIENT)
	public static void registerRendering() {
		// iterate through them
		for(IBlockAdvanced block : blocks) {
			block.registerRender();
		}
	}

}
