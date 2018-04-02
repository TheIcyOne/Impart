package com.headfishindustries.impart.capability.ex;

import com.headfishindustries.impart.entity.EntityTethering;

public class TetherEntity implements IHasTetherEntity{

	private EntityTethering tether;
	
	@Override
	public EntityTethering getEntityTethering() {
		return this.tether;
	}

	@Override
	public void setEntity(EntityTethering e) {
		this.tether = e;		
	}
}
