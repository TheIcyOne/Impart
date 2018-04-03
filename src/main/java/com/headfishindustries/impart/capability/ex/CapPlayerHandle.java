package com.headfishindustries.impart.capability.ex;

import com.headfishindustries.impart.Impart;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapPlayerHandle {

	public static final ResourceLocation PROJECT_CAP = new ResourceLocation(Impart.MODID, "projection");
	
	@SubscribeEvent
	public static void attach(AttachCapabilitiesEvent<Entity> e){
		if (e.getObject() instanceof EntityPlayer) {
		Impart.LOGGER.info("Attaching player stuff.");
		e.addCapability(PROJECT_CAP, new ProjectionProvider());
		}
		
	}
}
