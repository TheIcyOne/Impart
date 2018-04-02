package com.headfishindustries.impart.capability.ex;

import net.minecraft.util.math.Vec3d;

public class Projection implements IProjection{
	
	private Vec3d tetherPos;
	private Vec3d playerPos;
	private float range;
	private boolean tethered;
	
	public Projection(){
		
	}

	@Override
	public void setTetherPos(Vec3d pos) {
		this.tetherPos = pos;		
	}

	@Override
	public Vec3d getTetherPos() {
		
		return this.tetherPos;
	}
	
	@Override
	public void setPlayerPos(Vec3d pos) {
		this.playerPos = pos;		
	}

	@Override
	public Vec3d getPlayerPos() {
		
		return this.playerPos;
	}


	@Override
	public void setRange(float r) {
		this.range = r;	
	}

	@Override
	public float getRange() {
		return this.range;
	}

	@Override
	public void setProjected(boolean proj) {
		tethered = proj;		
	}

	@Override
	public boolean getProjected() {
		return tethered;
	}



}
