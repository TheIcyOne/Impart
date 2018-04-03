package com.headfishindustries.impart.capability.ex;

import com.headfishindustries.impart.Impart;
import com.headfishindustries.impart.ImpartedItems;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapItemHandle {

	public static final ResourceLocation TETHER_ENT_CAP = new ResourceLocation(Impart.MODID, "tether_ent");
	

	@SubscribeEvent
	public static void attach(AttachCapabilitiesEvent<ItemStack> e){
		Minecraft.getMinecraft().mcProfiler.startSection("ItemStack Capabilities");
		if (e.getObject().getItem() == ImpartedItems.item_tether){
			Impart.LOGGER.info("Attaching item stuff.");
			e.addCapability(TETHER_ENT_CAP, new TetherEntityProvider());
		}
		Minecraft.getMinecraft().mcProfiler.endSection();
	}
}
