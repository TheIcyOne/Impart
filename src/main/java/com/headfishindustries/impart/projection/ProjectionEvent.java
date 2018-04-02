package com.headfishindustries.impart.projection;

import com.headfishindustries.impart.TetherExtension;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class ProjectionEvent extends PlayerEvent{
	
//	private IProjection project;
	
	private TetherExtension ex;	

	public ProjectionEvent(EntityPlayer player) {
		super(player);
		this.ex = TetherExtension.For(player);		
	}
	
	public TetherExtension getTetherExtension(){
		return this.ex;
	}
	
	public Vec3d playerPos(){
		return ex.getPlayerPos();
	}
	
	public Vec3d tetherPos(){
		return ex.getTetherPos();
	}
	
	public static class Transmit extends ProjectionEvent{

		public Transmit(EntityPlayer player) {
			super(player);
		}
		
	}
	
	public static class Return extends ProjectionEvent{

		public Return(EntityPlayer player) {
			super(player);
		}
		
	}

	

}
