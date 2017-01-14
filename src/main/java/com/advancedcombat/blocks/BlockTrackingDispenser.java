package com.advancedcombat.blocks;

import com.advancedcombat.api.IBlockAdvanced;
import com.advancedcombat.creativetabs.ACCreativeTabs;
import com.advancedcombat.util.ACUtils;
import com.advancedcombat.util.TrackingDispenserBehavior.BehaviorTrackingFireballDispense;
import com.advancedcombat.util.TrackingDispenserBehavior.BehaviorTrackingProjectileDispense;

import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockSourceImpl;
import net.minecraft.block.state.IBlockState;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemFireball;
import net.minecraft.item.ItemLingeringPotion;
import net.minecraft.item.ItemSplashPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/** The tracking dispenser */
public class BlockTrackingDispenser extends BlockDispenser implements IBlockAdvanced {
	
	public BlockTrackingDispenser() {
		super();
		this.setHardness(Blocks.DISPENSER.getBlockHardness(null, null, null));
		this.setCreativeTab(ACCreativeTabs.main);
	}
	
	/** Change the dispenser name */
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);
		BlockSourceImpl src = new BlockSourceImpl(worldIn, pos);
        TileEntityDispenser tile = (TileEntityDispenser)src.getBlockTileEntity();
        if(!tile.hasCustomName()) tile.setCustomName(this.getLocalizedName());
	}
	
	/** Custom dispense routines */
	@Override
	protected void dispense(World worldIn, BlockPos pos) {
        BlockSourceImpl src = new BlockSourceImpl(worldIn, pos);
        TileEntityDispenser tile = (TileEntityDispenser)src.getBlockTileEntity();

        if (tile != null) {
            int i = tile.getDispenseSlot();

            if (i < 0) {
                worldIn.playEvent(1001, pos, 0);
            } else {
            	
            	// get the active item from its slot
                ItemStack itemstack = tile.getStackInSlot(i);
                IBehaviorDispenseItem defaultbehavior = this.getBehavior(itemstack);

                // make sure it's not regular dropper behavior
                if (defaultbehavior != IBehaviorDispenseItem.DEFAULT_BEHAVIOR) {
                	ItemStack itemstack1 = null;
                	
                	// replace vanilla behavior
                	if(defaultbehavior instanceof BehaviorProjectileDispense) {
                		itemstack1 = this.dispenseProjectile((BehaviorProjectileDispense)defaultbehavior, src, itemstack);
                	} else if(itemstack.getItem() instanceof ItemSplashPotion || itemstack.getItem() instanceof ItemLingeringPotion) {
                		itemstack1 = this.dispensePotion(src, itemstack);
                	} else if(itemstack.getItem() instanceof ItemFireball) {
                		itemstack1 = this.dispenseFireball(src, itemstack);
                	}
                	
                	// revert to vanilla on failure
                	if(itemstack1 == null) {
                		itemstack1 = defaultbehavior.dispense(src, itemstack);
                	}
                	
                	// update the inventory
                    tile.setInventorySlotContents(i, itemstack1.getCount() <= 0 ? ItemStack.EMPTY : itemstack1);
                }
            }
        }
    }
	
	/** Dispense a projectile */
    public ItemStack dispenseProjectile(BehaviorProjectileDispense base, IBlockSource source, ItemStack stack) {
        ItemStack itemstack = new BehaviorTrackingProjectileDispense(base).dispenseStack(source, stack);
        if(itemstack != null) {
	        this.playDispenseSound(source);
	        this.spawnDispenseParticles(source, ACUtils.getDispenserFacing(ACUtils.getBlockSourceMetadata(source)));
        }
        return itemstack;
    }
    
    /** Dispense a potion */
    public ItemStack dispensePotion(IBlockSource source, ItemStack stack) {
    	BehaviorProjectileDispense base = new BehaviorProjectileDispense() {
    		@Override
            protected IProjectile getProjectileEntity(World worldIn, IPosition position, ItemStack stack) {
                return new EntityPotion(worldIn, position.getX(), position.getY(), position.getZ(), stack.copy());
            }
    		@Override
            protected float getProjectileInaccuracy() {
                return super.getProjectileInaccuracy() * 0.5F;
            }
    		@Override
            protected float getProjectileVelocity() {
                return super.getProjectileVelocity() * 1.25F;
            }
        };
        return dispenseProjectile(base, source, stack);
    }
    
	/** Dispense a fireball */
    public ItemStack dispenseFireball(IBlockSource source, ItemStack stack) {
        ItemStack itemstack = new BehaviorTrackingFireballDispense().dispenseStack(source, stack);
        if(itemstack != null) {
        	this.playFireballSound(source);
        	this.spawnDispenseParticles(source, ACUtils.getDispenserFacing(ACUtils.getBlockSourceMetadata(source)));
        }
        return itemstack;
    }
    
    /** Dispense sound */
    private void playDispenseSound(IBlockSource source) {
        source.getWorld().playEvent(1000, source.getBlockPos(), 0);
    }
    
    /** Fireball sound */
    private void playFireballSound(IBlockSource source) {
    	source.getWorld().playEvent(1018, source.getBlockPos(), 0);
    }

    /**
     * Order clients to display dispense particles from the specified block and facing.
     */
    private void spawnDispenseParticles(IBlockSource source, EnumFacing facingIn) {
        source.getWorld().playEvent(2000, source.getBlockPos(), this.func_82488_a(facingIn));
    }
    
    /** A bit of an offset */
    private int func_82488_a(EnumFacing facingIn) {
        return facingIn.getFrontOffsetX() + 1 + (facingIn.getFrontOffsetZ() + 1) * 3;
    }
	
}
