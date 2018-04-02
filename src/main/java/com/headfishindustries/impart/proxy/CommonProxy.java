package com.headfishindustries.impart.proxy;

import com.headfishindustries.impart.Impart;
import com.headfishindustries.impart.entity.EntityTethering;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class CommonProxy {

	public void preInit(){
		
	
	}
	
	public void init(){
		EntityRegistry.registerModEntity(new ResourceLocation(Impart.MODID, "tether_entity"), EntityTethering.class, "tether_entity", 1, Impart.instance, 64, 64, true);
	}
	
	public void postInit(){
		
	}

	public void registerModels() {
		
	}
}
