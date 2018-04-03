package com.headfishindustries.impart.entity;

import com.headfishindustries.impart.Impart;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderPlayerBody extends RenderBiped<EntityPlayerBody>{

	static ModelPlayer model = new ModelPlayer(1, false);
	
	static final ResourceLocation DEFAULT_RL = new ResourceLocation(Impart.MODID,"textures/entities/steve.png");
	
	
	public RenderPlayerBody(RenderManager renderManager) {
		super(renderManager, model, NAME_TAG_RANGE);
		this.shadowSize = 0.5f;
	}
	
	@Override
    protected ResourceLocation getEntityTexture(EntityPlayerBody entity)
    {
		return DEFAULT_RL;
    }



}
