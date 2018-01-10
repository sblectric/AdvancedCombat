package com.advancedcombat.events;

import java.util.UUID;

import com.advancedcombat.items.ItemArmorNether;
import com.advancedcombat.items.ItemArmorObsidian;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/** Handles armor events */
public class ArmorHandler {

	static final UUID knockbackID = UUID.fromString("1af01788-c878-4a98-868a-d4a6365be699");

	/** Stuff to handle on living hurt events */
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent e) {
		if(!e.getEntityLiving().world.isRemote && e.getSource() != null) {
			// handle armor stuff
			netherFortressArmor(e);
			obsidianArmor(e);
		}
	}

	/** Handle wearing nether fortress armor */
	private boolean netherFortressArmor(LivingHurtEvent e) {
		EntityLivingBase hurt = e.getEntityLiving();

		// do things based on the armor status
		int armorCount = getWearingSetCount(hurt, ItemArmorNether.class);
		if(armorCount > 0) {
			
			// knockback resistance
			IAttributeInstance inst = hurt.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
			AttributeModifier mod = new AttributeModifier(knockbackID, "nfakb", armorCount / 4D, 0).setSaved(false);
			if(inst.getModifier(knockbackID) == null) inst.applyModifier(mod);

			// wither thorns
			if(e.getSource().getTrueSource() != null && e.getSource().getTrueSource() instanceof EntityLivingBase) {
				EntityLivingBase attacker = (EntityLivingBase)e.getSource().getTrueSource();
				attacker.addPotionEffect(new PotionEffect(MobEffects.WITHER, armorCount * 20, armorCount > 2 ? 1 : 0));
			}

			// blast resistance I
			if(e.getSource().isExplosion()) {
				e.setAmount(e.getAmount() * (1 - armorCount / 8F));
			}
			
			return true;
		}
		return false;
	}
	
	/** Handle wearing obsidian armor */
	private boolean obsidianArmor(LivingHurtEvent e) {
		EntityLivingBase hurt = e.getEntityLiving();

		// do things based on the armor status
		int armorCount = getWearingSetCount(hurt, ItemArmorObsidian.class);
		if(armorCount > 0) {
			
			// blast resistance II
			if(e.getSource().isExplosion()) {
				e.setAmount(e.getAmount() * (1 - armorCount / 4F));
			}
			
			return true;
		}
		return false;
	}

	/** Reset some stuff every tick */
	@SubscribeEvent
	public void resetStatus(LivingUpdateEvent e) {
		EntityLivingBase guy = e.getEntityLiving();
		if(!guy.world.isRemote) {

			// nether fortress stuff
			IAttributeInstance inst = guy.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
			if(inst.getModifier(knockbackID) != null) inst.removeModifier(knockbackID);

		}
	}

	/** Get how many pieces of armor of a specified type the entity is wearing */
	public static int getWearingSetCount(EntityLivingBase entity, Class<? extends ItemArmor> armorClass) {
		ItemStack HEAD, CHEST, LEGS, FEET;
		HEAD = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
		CHEST = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		LEGS = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
		FEET = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
		boolean helm = !HEAD.isEmpty() && armorClass.isInstance(HEAD.getItem());
		boolean chest = !CHEST.isEmpty() && armorClass.isInstance(CHEST.getItem());
		boolean legs = !LEGS.isEmpty() && armorClass.isInstance(LEGS.getItem());
		boolean boots = !FEET.isEmpty() && armorClass.isInstance(FEET.getItem());
		return (helm ? 1 : 0) + (chest ? 1 : 0) + (legs ? 1 : 0) + (boots ? 1 : 0);
	}

}
