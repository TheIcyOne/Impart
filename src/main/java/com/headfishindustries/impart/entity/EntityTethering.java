package com.headfishindustries.impart.entity;

import com.headfishindustries.impart.TetherExtension;
import com.headfishindustries.impart.particle.TetherParticle;

import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityTethering extends EntityThrowable{
	
	private boolean hit = false;
	private Vec3d hitPos;

	public EntityTethering(World worldIn) {
		super(worldIn);
	}
	
	@Override
	protected void onImpact(RayTraceResult result) {
		
		 if (result.entityHit != null)
	        {
	            if (result.entityHit == this.thrower)
	            {
	                return;
	            }
	        }
		
		BlockPos pos = result.getBlockPos();
		if (pos != null && this.getEntityWorld().getBlockState(result.getBlockPos()).isSideSolid(this.getEntityWorld(), result.getBlockPos(), result.sideHit)){
			this.setHit(true);
			this.setHitPos(this.getPositionVector().subtract(this.motionX, this.motionY, this.motionZ));
		}
	}

	public Vec3d getHitPos() {
		return hitPos;
	}
	
	void setHitPos(Vec3d hitPos) {
		this.hitPos = hitPos;
	}

	public boolean isHit() {
		return hit;
	}

	void setHit(boolean hit) {
		this.hit = hit;
	}
	
	@Override
	protected float getGravityVelocity()
    {
        return 0.00F;
    }

	
	@Override
    public void onUpdate(){
		super.onUpdate();
		new TetherParticle(world, lastTickPosX, lastTickPosX, lastTickPosX);
		this.world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, true, this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ, 0);
		if (!TetherExtension.playerToEntity.containsValue(this)){
			this.setDead();
		}
	}



}
