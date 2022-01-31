package com.advancedcombat.items;

import com.advancedcombat.api.IItemAdvanced;
import com.advancedcombat.creativetabs.ACCreativeTabs;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** The advanced bow */
public class ItemBowAdvanced extends ItemBow implements IItemAdvanced {

	public ItemBowAdvanced() {
		super();
		this.setMaxDamage(1536);
		this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter() {
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack s, World world, EntityLivingBase e) {
				if (e == null) {
					return 0.0F;
				} else {
					ItemStack stack = e.getActiveItemStack();
					return stack != null && stack.getItem() == ItemBowAdvanced.this ? (float)(s.getMaxItemUseDuration() - e.getItemInUseCount()) / 10.0F : 0.0F;
				}
			}
		});
		this.setCreativeTab(ACCreativeTabs.main);
	}

	/** Find some arrows */
	protected ItemStack findAmmo(EntityPlayer player) {
		if (this.isArrow(player.getHeldItem(EnumHand.OFF_HAND))) {
			return player.getHeldItem(EnumHand.OFF_HAND);
		} else if (this.isArrow(player.getHeldItem(EnumHand.MAIN_HAND))) {
			return player.getHeldItem(EnumHand.MAIN_HAND);
		} else {
			for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
				ItemStack itemstack = player.inventory.getStackInSlot(i);

				if (this.isArrow(itemstack)) {
					return itemstack;
				}
			}

			return ItemStack.EMPTY;
		}
	}
	
	/** Twice as fast on the draw */
    public static float getArrowVelocity(int useTime) {
        float f = (float)useTime / 10.0F;
        f = (f * f + f * 2.0F) / 3.0F;

        if (f > 1.0F)
        {
            f = 1.0F;
        }

        return f;
    }

	/** Some custom behavior */
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer)entityLiving;
			boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
			ItemStack itemstack = this.findAmmo(entityplayer);

			int i = this.getMaxItemUseDuration(stack) - timeLeft;
			i = ForgeEventFactory.onArrowLoose(stack, worldIn, (EntityPlayer)entityLiving, i, itemstack != null || flag);
			if (i < 0) return;

			if (itemstack != null || flag) {
				if(itemstack == null) itemstack = new ItemStack(Items.ARROW);

				float f = getArrowVelocity(i);

				if ((double)f >= 0.1D) {
					boolean flag1 = flag && itemstack.getItem() instanceof ItemArrow;

					if (!worldIn.isRemote) {
						
						// 50% faster arrows
						ItemArrow itemarrow = (ItemArrow)((ItemArrow)(itemstack.getItem() instanceof ItemArrow ? itemstack.getItem() : Items.ARROW));
						EntityArrow entityarrow = itemarrow.createArrow(worldIn, itemstack, entityplayer);
						entityarrow.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, f * 4.5F, 1.0F);

						// always critical
						entityarrow.setIsCritical(true);

						// 25% more powerful at same speeds
						int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
						if (j > 0) entityarrow.setDamage(entityarrow.getDamage() + (double)j * 0.5D + 0.5D);
						entityarrow.setDamage(entityarrow.getDamage() * 1.25);

						// 1 bonus punch level
						int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
						entityarrow.setKnockbackStrength(k + 1);

						if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
							entityarrow.setFire(100);
						}

						stack.damageItem(1, entityplayer);

						if (flag1) {
							entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
						}

						worldIn.spawnEntity(entityarrow);
					}

					worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, 
							SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

					if (!flag1) {
						itemstack.shrink(1);

						if (itemstack.getCount() == 0) {
							entityplayer.inventory.deleteStack(itemstack);
						}
					}

					entityplayer.addStat(StatList.getObjectUseStats(this));
				}
			}
		}
	}

	/** Much more enchantable at the table than a regular bow */
	@Override
	public int getItemEnchantability() {
		return 10;
	}
	
	@Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Item.getItemFromBlock(Blocks.IRON_BLOCK);
    }

}
