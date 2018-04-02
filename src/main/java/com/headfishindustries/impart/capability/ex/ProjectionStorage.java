package com.headfishindustries.impart.capability.ex;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ProjectionStorage implements IStorage<IProjection>{

	@Override
	public NBTBase writeNBT(Capability<IProjection> capability,
			IProjection instance, EnumFacing side) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setFloat("range", instance.getRange());
		
		NBTTagCompound tetherPos = new NBTTagCompound();
		 tetherPos.setDouble("x", instance.getTetherPos().x);
		 tetherPos.setDouble("y", instance.getTetherPos().y);
		 tetherPos.setDouble("z", instance.getTetherPos().z);
		NBTTagCompound playerPos = new NBTTagCompound();
		 playerPos.setDouble("x", instance.getPlayerPos().x);
		 playerPos.setDouble("y", instance.getPlayerPos().y);
		 playerPos.setDouble("z", instance.getPlayerPos().z);
		nbt.setTag("tether_pos", tetherPos);
		nbt.setTag("player_pos", playerPos);
		
		nbt.setBoolean("projected", instance.getProjected());
		return nbt;
	}

	@Override
	public void readNBT(Capability<IProjection> capability,
			IProjection instance, EnumFacing side, NBTBase nbt) {
		instance.setRange(((NBTTagCompound)nbt).getFloat("range"));
		
		NBTTagCompound pos = ((NBTTagCompound) nbt).getCompoundTag("tether_pos");
		instance.setTetherPos(pos.getDouble("x"), pos.getDouble("y"), pos.getDouble("z"));
		
		pos = ((NBTTagCompound) nbt).getCompoundTag("player_pos");
		instance.setPlayerPos(pos.getDouble("x"), pos.getDouble("y"), pos.getDouble("z"));
		
		instance.setProjected(((NBTTagCompound)nbt).getBoolean("projected"));
	}
}
