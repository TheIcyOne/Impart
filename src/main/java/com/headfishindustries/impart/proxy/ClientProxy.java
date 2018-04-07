package com.headfishindustries.impart.proxy;

import com.headfishindustries.impart.Impart;
import com.headfishindustries.impart.ImpartedItems;
import com.headfishindustries.impart.entity.EntityPlayerBody;
import com.headfishindustries.impart.entity.EntityTethering;
import com.headfishindustries.impart.entity.RenderEntityTethering;
import com.headfishindustries.impart.entity.RenderPlayerBody;
import com.headfishindustries.impart.particle.LinkParticle;
import net.minecraft.client.Minecraft;
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
		Minecraft.getMinecraft().mcProfiler.profilingEnabled = true;
		registerRenderers();
	}
	
	@Override
	public void init(){
		super.init();
		Minecraft.getMinecraft().effectRenderer.registerParticle(2, new LinkParticle.LinkFactory());
	}
	
	
	void registerRenderers(){
		RenderingRegistry.registerEntityRenderingHandler(EntityTethering.class, RenderEntityTethering::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityPlayerBody.class, RenderPlayerBody::new);		
	}

}
