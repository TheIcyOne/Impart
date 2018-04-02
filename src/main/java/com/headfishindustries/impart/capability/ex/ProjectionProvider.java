package com.headfishindustries.impart.capability.ex;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class ProjectionProvider implements ICapabilitySerializable<NBTBase>{

	@CapabilityInject(IProjection.class)
	public static final Capability<IProjection> PROJECT_CAPABILITY = null;
	
	IProjection instance = PROJECT_CAPABILITY.getDefaultInstance();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == PROJECT_CAPABILITY;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		//fancy shmancy ternary
		return capability == PROJECT_CAPABILITY ? PROJECT_CAPABILITY.<T> cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return PROJECT_CAPABILITY.getStorage().writeNBT(PROJECT_CAPABILITY, instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		PROJECT_CAPABILITY.getStorage().readNBT(PROJECT_CAPABILITY, instance, null, nbt);
	}

}
