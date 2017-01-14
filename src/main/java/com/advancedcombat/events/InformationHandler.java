package com.advancedcombat.events;

import java.util.Iterator;
import java.util.List;

import com.advancedcombat.config.ACConfig;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/** Displays useful information in various ways */
public class InformationHandler {

	/** Show item damages in their tooltips */
	@SubscribeEvent
	public void onTooltipShow(ItemTooltipEvent e) {
		if(ACConfig.durabilityTooltips && !e.getItemStack().isEmpty() && e.getItemStack().getItem().isDamageable()) {
			String modid = e.getItemStack().getItem().getRegistryName().getResourceDomain();
			if(!ACConfig.tooltipModBL.contains(modid)) {
				int maxDamage = e.getItemStack().getMaxDamage();
				int currentDamage = e.getItemStack().getItemDamage();
				List<String> t = e.getToolTip();
				if(e.isShowAdvancedItemTooltips() && currentDamage > 0) { // remove the vanilla indicator
					Iterator<String> it = t.iterator();
					while(it.hasNext()) {
						String str = it.next();
						if(str.contains("Durability")) it.remove();
					}
				}
				t.add(1, TextFormatting.DARK_GRAY + "Durability: " + (maxDamage - currentDamage) + " / " + maxDamage);
			}
		}
	}

}
