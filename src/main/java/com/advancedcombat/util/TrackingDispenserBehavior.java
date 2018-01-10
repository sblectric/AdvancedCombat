package com.advancedcombat.util;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

import com.advancedcombat.config.ACConfig;

import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.PositionImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

/** Handles the behavior for the tracking dispenser */
public abstract class TrackingDispenserBehavior {
	
	// the defined range
	public static int range = ACConfig.trackingDispenserRange;
	
	/** Get the item stack to dispense */
	public abstract ItemStack dispenseStack(IBlockSource source, ItemStack stack);
	
	/** Get the direction to the nearest target */
	public Vec3d getNearestTargetDirection(World world, BlockPos pos) {
		Vec3d pos2 = new Vec3d(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
		List<EntityLivingBase> ents = world.getEntitiesWithinAABB(EntityLivingBase.class, 
				new AxisAlignedBB(pos.down(range).east(range).south(range), pos.up(range).west(range).north(range)));
		ents.sort(new EntityDistanceSorter(pos));
		for(EntityLivingBase e : ents) {
			if(canEntityBeSeen(e, pos)) {
				return new Vec3d(e.posX - pos2.x, e.posY - pos2.y, e.posZ - pos2.z).normalize();
			}
		}
		return null;
	}
	
	/** Can the entity be seen? */
	private boolean canEntityBeSeen(Entity e, BlockPos pos) {
		if(!(e instanceof EntityPlayer && ((EntityPlayer)e).capabilities.disableDamage)) {
		Vec3d pos2 = new Vec3d(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
		return e.world.rayTraceBlocks(new Vec3d(pos2.x + 1, pos2.y, pos2.z + 0), new Vec3d(e.posX, e.posY + e.getEyeHeight(), e.posZ)) == null || 
				e.world.rayTraceBlocks(new Vec3d(pos2.x + 0, pos2.y, pos2.z + 1), new Vec3d(e.posX, e.posY + e.getEyeHeight(), e.posZ)) == null || 
				e.world.rayTraceBlocks(new Vec3d(pos2.x - 1, pos2.y, pos2.z + 0), new Vec3d(e.posX, e.posY + e.getEyeHeight(), e.posZ)) == null || 
				e.world.rayTraceBlocks(new Vec3d(pos2.x + 0, pos2.y, pos2.z - 1), new Vec3d(e.posX, e.posY + e.getEyeHeight(), e.posZ)) == null; 
		} else {
			return false;
		}
	}
	
	/** Tracking behavior for arrows and the like */
	public static class BehaviorTrackingProjectileDispense extends TrackingDispenserBehavior {
		
		BehaviorProjectileDispense standard;
		
		public BehaviorTrackingProjectileDispense(BehaviorProjectileDispense standard) {
			this.standard = standard;
		}

		@Override
		public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
	        World world = source.getWorld();
	        BlockPos pos = source.getBlockPos();
	        Vec3d pos2 = new Vec3d(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
	        Vec3d dir = this.getNearestTargetDirection(world, pos);
	        if(dir == null) return null;
	        IPosition ipos = new PositionImpl(pos2.x + dir.x * .75, pos2.y + dir.y * .75, pos2.z + dir.z * .75);
	        IProjectile iproj = this.getProjectileEntity(standard, world, ipos, stack);
	        iproj.shoot(dir.x, dir.y + 0.1F, dir.z, 
	        		this.getProjectileVelocity(standard), this.getProjectileInaccuracy(standard));
	        world.spawnEntity((Entity)iproj);
	        stack.splitStack(1);
	        return stack;
		}
		
		/** Get the projectile entity via reflection */
		private IProjectile getProjectileEntity(BehaviorProjectileDispense standard, World world, IPosition pos, ItemStack stack) {
			Method m = ACUtils.findMethod(BehaviorProjectileDispense.class, standard, new String[]{"getProjectileEntity", "func_82499_a"}, 
					World.class, IPosition.class, ItemStack.class);
			try {
				return (IProjectile)m.invoke(standard, world, pos, stack);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		/** Get the projectile velocity via reflection */
		private float getProjectileVelocity(BehaviorProjectileDispense standard) {
			Method m = ACUtils.findMethod(BehaviorProjectileDispense.class, standard, new String[]{"getProjectileVelocity", "func_82500_b"});
			try {
				return (float)m.invoke(standard);
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		}
		
		/** Get the projectile inaccuracy via reflection */
		private float getProjectileInaccuracy(BehaviorProjectileDispense standard) {
			Method m = ACUtils.findMethod(BehaviorProjectileDispense.class, standard, new String[]{"getProjectileInaccuracy", "func_82498_a"});
			try {
				return (float)m.invoke(standard);
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		}
		
	}
	
	/** Handles fireball behavior */
	public static class BehaviorTrackingFireballDispense extends TrackingDispenserBehavior {

		@Override
		public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
	        World world = source.getWorld();
	        BlockPos pos = source.getBlockPos();
	        Vec3d pos2 = new Vec3d(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
	        Vec3d dir = this.getNearestTargetDirection(world, pos);
	        if(dir == null) return null;
	        IPosition ipos = new PositionImpl(pos2.x + dir.x * .75, pos2.y + dir.y * .75, pos2.z + dir.z * .75);
            double d0 = ipos.getX();
            double d1 = ipos.getY();
            double d2 = ipos.getZ();
            Random random = world.rand;
            double d3 = random.nextGaussian() * 0.05D + dir.x;
            double d4 = random.nextGaussian() * 0.05D + dir.y;
            double d5 = random.nextGaussian() * 0.05D + dir.z;
            world.spawnEntity(new EntitySmallFireball(world, d0, d1, d2, d3, d4, d5));
            stack.splitStack(1);
            return stack;
		}
		
	}

}
