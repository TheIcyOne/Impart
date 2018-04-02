package com.headfishindustries.impart.particle;

import com.headfishindustries.impart.Impart;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public class TetherParticle extends Imparticle{

	public TetherParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn){
		super(worldIn, xCoordIn, yCoordIn, zCoordIn);
	}
	
	public TetherParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn,
			double ySpeedIn, double zSpeedIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
			
		particleGravity = 0;
		particleMaxAge = 100;
		
		this.particleAlpha = 0.9F;
		
		this.setParticleTexture(Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(Impart.MODID + ":entity/tether_body_particle"));
		
	    motionX = xSpeedIn;
	    motionY = ySpeedIn;
	    motionZ = zSpeedIn;
	}
	

	
	@Override
	public void onUpdate(){
		if (this.particleAge >= this.particleMaxAge){
			this.setExpired();
		}
	}

	

}
