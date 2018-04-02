package com.headfishindustries.impart.capability.ex;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class TetherEntityProvider implements ICapabilitySerializable<NBTBase>{
	
	@CapabilityInject(IHasTetherEntity.class)
	public static final Capability<IHasTetherEntity> CAP_TETHER_ENT = null;
	
	IHasTetherEntity instance = CAP_TETHER_ENT.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CAP_TETHER_ENT;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == CAP_TETHER_ENT ? CAP_TETHER_ENT.<T> cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return CAP_TETHER_ENT.getStorage().writeNBT(CAP_TETHER_ENT, instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		CAP_TETHER_ENT.getStorage().readNBT(CAP_TETHER_ENT, instance, null, nbt);
	}

}
