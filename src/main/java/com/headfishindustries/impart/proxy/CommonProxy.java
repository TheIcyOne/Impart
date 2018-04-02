package com.headfishindustries.impart.proxy;

import com.headfishindustries.impart.Impart;
import com.headfishindustries.impart.ImpartedItems;
import com.headfishindustries.impart.entity.EntityPlayerBody;
import com.headfishindustries.impart.entity.EntityTethering;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void preInit(){
		
	
	}
	
	public void init(){
		GameRegistry.addSmelting(new ItemStack(Items.ENDER_EYE, 8), new ItemStack(ImpartedItems.item_tether), 24);
		EntityRegistry.registerModEntity(new ResourceLocation(Impart.MODID, "tether_entity"), EntityTethering.class, "tether_entity", 1, Impart.instance, 256, 64, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Impart.MODID, "player_body"), EntityPlayerBody.class, "player_body", 2, Impart.instance, 64, 256, false);
	}
	
	public void postInit(){
		
	}

	public void registerModels() {
		
	}
}
