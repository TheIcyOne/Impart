package com.headfishindustries.impart.capability.ex;

import net.minecraft.util.math.Vec3d;

public interface IProjection {
	
	public void setTetherPos(Vec3d pos);
	public default void setTetherPos(double x, double y, double z){
		setTetherPos(new Vec3d(x, y, z));
	}
	public Vec3d getTetherPos();
	
	public void setPlayerPos(Vec3d pos);
	public default void setPlayerPos(double x, double y, double z){
		setPlayerPos(new Vec3d(x, y, z));
	}
	public Vec3d getPlayerPos();
	
	public void setRange(float r);
	public float getRange();
	
	public void setProjected(boolean proj);
	public boolean getProjected();
	

}
