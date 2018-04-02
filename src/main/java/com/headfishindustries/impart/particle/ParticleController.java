package com.headfishindustries.impart.particle;

import net.minecraft.world.World;

public abstract class ParticleController {

	protected Imparticle particle;
	protected int priority;
	protected boolean firstTick;
	
	public ParticleController(Imparticle effect, int prio){
		this.particle = effect;
		this.priority = prio;
	}
	
	protected ParticleController targetNew(Imparticle newParticle){
		
		
		this.particle = newParticle;
		return this;
	}
	
	public abstract ParticleController clone();
	
	public abstract void doUpdate(World world);
	
	public void onUpdate(World world){
		if (!world.isRemote){
			if (this.particle != null){
				particle.setExpired();
				return;
			}
			if (particle != null){
				doUpdate(world);
			}
			if (this.firstTick){
				this.firstTick = false;
			}
		}
	}

}
