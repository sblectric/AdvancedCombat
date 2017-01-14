package com.advancedcombat.util;

import java.util.Comparator;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;

/** Sort entities by distance to a base position */
public class EntityDistanceSorter implements Comparator<EntityLivingBase> {
	
	protected BlockPos base;
	
	public EntityDistanceSorter(BlockPos base) {
		this.base = base;
	}

	@Override
	public int compare(EntityLivingBase arg0, EntityLivingBase arg1) {
		double d1 = arg0.getDistanceSq(base);
		double d2 = arg1.getDistanceSq(base);
		return (d1 < d2) ? -1 : (d1 > d2) ? 1 : 0;
	}

}
