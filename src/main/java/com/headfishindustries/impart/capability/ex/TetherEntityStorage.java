package com.headfishindustries.impart.capability.ex;

import com.headfishindustries.impart.entity.EntityTethering;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class TetherEntityStorage implements IStorage<IHasTetherEntity>{

	@Override
	public NBTBase writeNBT(Capability<IHasTetherEntity> capability,
			IHasTetherEntity instance, EnumFacing side) {
		NBTTagCompound nbt = new NBTTagCompound();
		
		return nbt;
	}

	@Override
	public void readNBT(Capability<IHasTetherEntity> capability,
			IHasTetherEntity instance, EnumFacing side, NBTBase nbt) {
		EntityTethering e = new EntityTethering(null);
		e.readEntityFromNBT(((NBTTagCompound) nbt).getCompoundTag("tether_entity"));
		
		instance.setEntity(e);
	}
}
