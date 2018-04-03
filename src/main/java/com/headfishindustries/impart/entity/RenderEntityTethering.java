package com.headfishindustries.impart.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderEntityTethering extends Render<EntityTethering>{
	



	public RenderEntityTethering(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityTethering entity) {
	//	return new ResourceLocation(Impart.MODID + ":" + "textures/entities/entity_tether.png");
		return null;
	}

	
	@Override
	public void doRender(EntityTethering tether, double x, double y, double z, float yawsome, float partial) {
		
	}


}
