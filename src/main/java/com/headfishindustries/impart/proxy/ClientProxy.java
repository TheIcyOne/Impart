package com.headfishindustries.impart.proxy;

import com.headfishindustries.impart.Impart;
import com.headfishindustries.impart.ImpartedItems;
import com.headfishindustries.impart.entity.EntityTethering;
import com.headfishindustries.impart.entity.RenderEntityTethering;
import com.headfishindustries.impart.particle.LinkParticle;
import com.headfishindustries.impart.particle.TetherParticle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Barrier;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerModels(){
		ModelLoader.setCustomModelResourceLocation(ImpartedItems.item_tether, 0, new ModelResourceLocation(Impart.MODID + ":item_tether", "inventory"));
	}
	
	@Override
	public void preInit(){
		registerRenderers();
	}
	
	@Override
	public void init(){
		super.init();
		Minecraft.getMinecraft().effectRenderer.registerParticle(2, new LinkParticle.LinkFactory());
	}
	
	
	void registerRenderers(){
		RenderingRegistry.registerEntityRenderingHandler(EntityTethering.class, RenderEntityTethering::new);
		
		
	}

}
