package com.advancedcombat.events;

import com.advancedcombat.items.ItemShieldAdvanced;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/** Handles the custom shield's functionality */
public class CustomShieldHandler {

	/** Damage the advanced shield correctly, as vanilla code only works for the vanilla shield */
	@SubscribeEvent
	public void onShieldedAttack(LivingAttackEvent e) {
		EntityLivingBase guy = e.getEntityLiving();
		if(!guy.world.isRemote && guy instanceof EntityPlayer) {
			if(e.getAmount() > 0.0F && !guy.getActiveItemStack().isEmpty() && guy.getActiveItemStack().getItem() instanceof ItemShieldAdvanced) {
				if(this.canBlockDamageSource((EntityPlayer)guy, e.getSource())) {
					this.damageShield((EntityPlayer)guy, e.getAmount());
				}
			}
		}
	}
	
	/** Better to copy over than to use reflection to the private method */
    private boolean canBlockDamageSource(EntityPlayer owner, DamageSource damageSourceIn) {
        if (!damageSourceIn.isUnblockable() && owner.isActiveItemStackBlocking()) {
            Vec3d vec3d = damageSourceIn.getDamageLocation();

            if (vec3d != null) {
                Vec3d vec3d1 = owner.getLook(1.0F);
                Vec3d vec3d2 = vec3d.subtractReverse(new Vec3d(owner.posX, owner.posY, owner.posZ)).normalize();
                vec3d2 = new Vec3d(vec3d2.x, 0.0D, vec3d2.z);

                if (vec3d2.dotProduct(vec3d1) < 0.0D) {
                    return true;
                }
            }
        }

        return false;
    }

	/** Fixed vanilla code */
	private void damageShield(EntityPlayer owner, float damage) {
		int i = 1 + MathHelper.floor(damage);
		owner.getActiveItemStack().damageItem(i, owner);

		if (owner.getActiveItemStack().getCount() <= 0) {
			EnumHand enumhand = owner.getActiveHand();
			ForgeEventFactory.onPlayerDestroyItem(owner, owner.getActiveItemStack(), enumhand);

			if(enumhand == EnumHand.MAIN_HAND) {
				owner.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack.EMPTY);
			} else {
				owner.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, ItemStack.EMPTY);
			}

			owner.resetActiveHand();
			owner.playSound(SoundEvents.ITEM_SHIELD_BREAK, 0.8F, 0.8F + owner.world.rand.nextFloat() * 0.4F);
		}
	}

}
