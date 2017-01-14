package com.advancedcombat.items;

import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** The enchantment upgrade item */
public class ItemEnchUpgrade extends ItemMeta {
	
	public static final int TIER_COUNT = 2;
	protected Enchantment enchant;
	protected boolean single;

	public ItemEnchUpgrade(Enchantment enchant, boolean single) {
		super(single ? 1 : TIER_COUNT, true, false, true);
		this.enchant = enchant;
		this.single = single;
	}
	
	public ItemEnchUpgrade(Enchantment enchant) {
		this(enchant, false);
	}
	
	/** Gets the localized name without using a massive language file */
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		String ench = I18n.translateToLocal(this.enchant.getName());
		return (stack.getItemDamage() > 0 ? "Elite " + ench : ench) + " Upgrade";
	}

	/** Add the info */
	@SideOnly(Side.CLIENT)
	@Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		int tier = stack.getItemDamage();
		tooltip.add("Upgrades to " + I18n.translateToLocal(enchant.getName()));
		if(!single) {
			tooltip.add("Tiers " + (tier * enchant.getMaxLevel() + 1) + " - " + (tier + 1) * enchant.getMaxLevel());
		}
	}
	
	/** Get the enchantment that this upgrade represents */
	public Enchantment getEnchantment() {
		return enchant;
	}

}
