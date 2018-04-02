package com.headfishindustries.impart.entity;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderPlayerBody extends RenderBiped<EntityPlayerBody>{

	static ModelPlayer model = new ModelPlayer(0.25f, false);
	
	static final ResourceLocation DEFAULT_RL = new ResourceLocation("minecraft","textures/entity/steve.png");
	
	
	public RenderPlayerBody(RenderManager renderManager) {
		super(renderManager, model, NAME_TAG_RANGE);
		
	}
	
	@Override
    protected ResourceLocation getEntityTexture(EntityPlayerBody entity)
    {
		return DEFAULT_RL;
    }
	
	@Override
	public void doRender(EntityPlayerBody entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
		GlStateManager.color(0.1f, 0.1f, 0.1f, 0.9f);
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		GlStateManager.color(1, 1, 1, 1);
    }

}
