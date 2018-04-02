package com.headfishindustries.impart.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityPlayerBody extends EntityLiving{
	
	private EntityPlayer player;
	static final ResourceLocation DEFAULT_RL = new ResourceLocation("minecraft","textures/entity/steve.png");

	public EntityPlayerBody(World worldIn, EntityPlayer p) {
		super(worldIn);
		this.player = (EntityPlayer) p;
	}
	
	public EntityPlayerBody(World worldIn){
		this(worldIn, null);
	}
	


	public EntityPlayer getPlayer(){
		return this.player;
	}
	
	public void setPlayer(EntityPlayer e){
		this.player = e;
	}
	
	@Override
	  public void writeEntityToNBT(NBTTagCompound compound)
    {
		
    }


}
