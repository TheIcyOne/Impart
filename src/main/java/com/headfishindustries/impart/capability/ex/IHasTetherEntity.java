package com.headfishindustries.impart.capability.ex;

import com.headfishindustries.impart.entity.EntityTethering;

import net.minecraft.world.World;

public interface IHasTetherEntity {

	public EntityTethering getEntityTethering();
	public void setEntity(EntityTethering e);
	
	public default boolean hasEntity(){
		return this.getEntityTethering() != null;
	}
	
	public default World getWorld(){
		return this.getEntityTethering().getEntityWorld();
	}

}
