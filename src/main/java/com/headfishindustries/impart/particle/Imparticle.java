package com.headfishindustries.impart.particle;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public abstract class Imparticle extends Particle {
	
	private Set<ParticleController> controllers = new HashSet<ParticleController>();
	
	protected Imparticle(World worldIn, double posXIn, double posYIn, double posZIn) {
		super(worldIn, posXIn, posYIn, posZIn);
	}

	public Imparticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn,
			double ySpeedIn, double zSpeedIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		
	}

	public void addController(ParticleController control){
		this.controllers.add(control);
	}
	
	public void removeController(ParticleController control){
		this.controllers.remove(control);
	}
	
	public void clearControllers(){
		this.controllers = new HashSet<ParticleController>();
	}
	
	@Override
	public int getFXLayer(){
		return 3;
	}
	
	public void setPositionFromEntity(Entity entity) {
		setPosition(entity.posX, entity.posY, entity.posZ);
	}
	

}
