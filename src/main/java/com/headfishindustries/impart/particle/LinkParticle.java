package com.headfishindustries.impart.particle;

import com.headfishindustries.impart.Impart;
import com.headfishindustries.impart.LazyMaths;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class LinkParticle extends Imparticle{
	
	ResourceLocation rl = new ResourceLocation(Impart.MODID + ":textures/particle/link.png");
	
	Vec3d startPos;
	Vec3d targetPos;
	Entity startEntity;
	Entity targetEntity;
	double width = 0.25;
	float speed;

	public LinkParticle(World world, double sourceX, double sourceY, double sourceZ, double targetX, double targetY, double targetZ) {
		super(world, sourceX, sourceY, sourceZ);
		this.startPos = new Vec3d(sourceX, sourceY, sourceZ);
		this.targetPos = new Vec3d(targetX, targetY, targetZ);
		this.speed = 0.1f;
	}
	
	public LinkParticle(World world, Entity start, Entity end){
		this(world, start.posX, start.posY, start.posZ,end.posX, end.posY, end.posZ);
		this.startEntity = start;
		this.targetEntity = end;
	}
	
	 private Vec3d getPosition(Entity startEntity2, double p_177110_2_, float p_177110_4_)
	    {
	        double d0 = startEntity2.lastTickPosX + (startEntity2.posX - startEntity2.lastTickPosX) * (double)p_177110_4_;
	        double d1 = p_177110_2_ + startEntity2.lastTickPosY + (startEntity2.posY - startEntity2.lastTickPosY) * (double)p_177110_4_;
	        double d2 = startEntity2.lastTickPosZ + (startEntity2.posZ - startEntity2.lastTickPosZ) * (double)p_177110_4_;
	        return new Vec3d(d0, d1, d2);
	    }
		
	@Override
	 public void renderParticle(BufferBuilder builder, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
	    {
/*		GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(rl);
		
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		double interpX = LazyMaths.interpolate(player.prevPosX, player.posX, partialTicks);
		double interpY = LazyMaths.interpolate(player.prevPosY, player.posY, partialTicks);
		double interpZ = LazyMaths.interpolate(player.prevPosZ, player.posZ, partialTicks);
		
		double distance = startEntity.getPositionVector().distanceTo(targetEntity.getPositionVector());
		long distanceFloor = Math.round(distance);
		long trueLength = distanceFloor * 4;
		
		if (trueLength == 0) trueLength = 4;
		
		double dstX = LazyMaths.interpolate(targetEntity.prevPosX, startEntity.posX, partialTicks);
		double dstY = LazyMaths.interpolate(targetEntity.prevPosY, startEntity.posY, partialTicks);
		double dstZ = LazyMaths.interpolate(targetEntity.prevPosZ, startEntity.posZ, partialTicks);
		
		double srcX = LazyMaths.interpolate(startEntity.prevPosX, targetEntity.posX, partialTicks);
		double srcY = LazyMaths.interpolate(startEntity.prevPosY, targetEntity.posY, partialTicks);
		double srcZ = LazyMaths.interpolate(startEntity.prevPosZ, targetEntity.posZ, partialTicks);
				
		GL11.glTranslated(dstX - interpX, dstY -interpY, dstZ - interpZ);
		
		double useWidth = width * 3;
		double dWidth = useWidth/(trueLength * distance);
		
		builder.begin(GL11.GL_TRIANGLE_STRIP, DefaultVertexFormats.POSITION_TEX);
		
		GL11.glColor4f(1, 0.3f, 1, 0.5f);
		
		for (int i = 0; i<= trueLength * distance; i++){
			float time = System.nanoTime() /10000000;
			float fac = i/trueLength;
			float f = 1 - Math.abs(i - trueLength/2) / (trueLength /2);
			
			double dX = this.startEntity.posX - this.targetEntity.posX + MathHelper.sin((float) ((srcX % 16 + distance * (1-fac) * 4 - time % 32767 / 5) / 4) )/2 * f;
			double dY = this.startEntity.posY - this.targetEntity.posY + MathHelper.sin((float) ((srcY % 16 + distance * (1-fac) * 4 - time % 32767 / 5) / 3) )/2 * f;
			double dZ = this.startEntity.posZ - this.targetEntity.posZ + MathHelper.sin((float) ((srcZ % 16 + distance * (1-fac) * 4 - time % 32767 / 5) / 2) )/2 * f;
			
			double u = (1-fac) * distance - time * speed;
			
			builder.pos(dX * fac - useWidth, dY * fac, dZ * fac).tex(u, 1).endVertex();
			builder.pos(dX * fac + useWidth, dY * fac, dZ * fac).tex(u, 0).endVertex();
			
			useWidth-= dWidth;
			
			}
		
		Tessellator.getInstance().draw();
		GL11.glPopMatrix();
		*/
		
		if (this.startEntity != null)
	        {
	            float f = 0.25f;
	            Tessellator tessellator = Tessellator.getInstance();
	            BufferBuilder bufferbuilder = tessellator.getBuffer();
	            Minecraft.getMinecraft().renderEngine.bindTexture(rl);
	            
	            GlStateManager.glTexParameteri(3553, 10242, 10497);
	            GlStateManager.glTexParameteri(3553, 10243, 10497);
	            GlStateManager.disableLighting();
	            GlStateManager.disableCull();
	            GlStateManager.disableBlend();
	            GlStateManager.depthMask(true);
	            
	        	EntityPlayerSP player = Minecraft.getMinecraft().player;
	        	
	            double interpX = LazyMaths.interpolate(player.prevPosX, player.posX, partialTicks);
	    		double interpY = LazyMaths.interpolate(player.prevPosY, player.posY, partialTicks);
	    		double interpZ = LazyMaths.interpolate(player.prevPosZ, player.posZ, partialTicks);
	            
	            double dstX = LazyMaths.interpolate(targetEntity.prevPosX, startEntity.posX, partialTicks);
	    		double dstY = LazyMaths.interpolate(targetEntity.prevPosY, startEntity.posY, partialTicks);
	    		double dstZ = LazyMaths.interpolate(targetEntity.prevPosZ, startEntity.posZ, partialTicks);

	            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
	            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	            float f2 = (float)this.targetEntity.world.getTotalWorldTime() + partialTicks;
	            float f3 = f2 * 0.5F % 1.0F;
	            float f4 = this.targetEntity.getEyeHeight();
	            GlStateManager.pushMatrix();
	            GlStateManager.translate(dstX - interpX, dstY -interpY, dstZ - interpZ);
	            Vec3d vec3d = this.getPosition(this.startEntity, (double)this.startEntity.height * 0.5D, partialTicks);
	            Vec3d vec3d1 = this.getPosition(this.targetEntity, (double)f4, partialTicks);
	            Vec3d vec3d2 = vec3d.subtract(vec3d1);
	            double d0 = vec3d2.lengthVector() + 1.0D;
	            vec3d2 = vec3d2.normalize();
	            float f5 = (float)Math.acos(vec3d2.y);
	            float f6 = (float)Math.atan2(vec3d2.z, vec3d2.x);
	            GlStateManager.rotate((((float)Math.PI / 2F) + -f6) * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
	            GlStateManager.rotate(f5 * (180F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
	            double d1 = (double)f2 * 0.05D * -1.5D;
	            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
	            float f7 = f * f;
	            int j = 64 + (int)(f7 * 191.0F);
	            int k = 32 + (int)(f7 * 191.0F);
	            int l = 128 - (int)(f7 * 64.0F);
	            double d4 = 0.0D + Math.cos(d1 + 2.356194490192345D) * 0.282D;
	            double d5 = 0.0D + Math.sin(d1 + 2.356194490192345D) * 0.282D;
	            double d6 = 0.0D + Math.cos(d1 + (Math.PI / 4D)) * 0.282D;
	            double d7 = 0.0D + Math.sin(d1 + (Math.PI / 4D)) * 0.282D;
	            double d8 = 0.0D + Math.cos(d1 + 3.9269908169872414D) * 0.282D;
	            double d9 = 0.0D + Math.sin(d1 + 3.9269908169872414D) * 0.282D;
	            double d10 = 0.0D + Math.cos(d1 + 5.497787143782138D) * 0.282D;
	            double d11 = 0.0D + Math.sin(d1 + 5.497787143782138D) * 0.282D;
	            double d12 = 0.0D + Math.cos(d1 + Math.PI) * 0.2D;
	            double d13 = 0.0D + Math.sin(d1 + Math.PI) * 0.2D;
	            double d14 = 0.0D + Math.cos(d1 + 0.0D) * 0.2D;
	            double d15 = 0.0D + Math.sin(d1 + 0.0D) * 0.2D;
	            double d16 = 0.0D + Math.cos(d1 + (Math.PI / 2D)) * 0.2D;
	            double d17 = 0.0D + Math.sin(d1 + (Math.PI / 2D)) * 0.2D;
	            double d18 = 0.0D + Math.cos(d1 + (Math.PI * 3D / 2D)) * 0.2D;
	            double d19 = 0.0D + Math.sin(d1 + (Math.PI * 3D / 2D)) * 0.2D;
	            double d22 = (double)(-1.0F + f3);
	            double d23 = d0 * 2.5D + d22;
	            bufferbuilder.pos(d12, d0, d13).tex(0.4999D, d23).color(j, k, l, 255).endVertex();
	            bufferbuilder.pos(d12, 0.0D, d13).tex(0.4999D, d22).color(j, k, l, 255).endVertex();
	            bufferbuilder.pos(d14, 0.0D, d15).tex(0.0D, d22).color(j, k, l, 255).endVertex();
	            bufferbuilder.pos(d14, d0, d15).tex(0.0D, d23).color(j, k, l, 255).endVertex();
	            bufferbuilder.pos(d16, d0, d17).tex(0.4999D, d23).color(j, k, l, 255).endVertex();
	            bufferbuilder.pos(d16, 0.0D, d17).tex(0.4999D, d22).color(j, k, l, 255).endVertex();
	            bufferbuilder.pos(d18, 0.0D, d19).tex(0.0D, d22).color(j, k, l, 255).endVertex();
	            bufferbuilder.pos(d18, d0, d19).tex(0.0D, d23).color(j, k, l, 255).endVertex();
	            double d24 = 0.0D;

	            if (this.targetEntity.ticksExisted % 2 == 0)
	            {
	                d24 = 0.5D;
	            }

	            bufferbuilder.pos(d4, d0, d5).tex(0.5D, d24 + 0.5D).color(j, k, l, 255).endVertex();
	            bufferbuilder.pos(d6, d0, d7).tex(1.0D, d24 + 0.5D).color(j, k, l, 255).endVertex();
	            bufferbuilder.pos(d10, d0, d11).tex(1.0D, d24).color(j, k, l, 255).endVertex();
	            bufferbuilder.pos(d8, d0, d9).tex(0.5D, d24).color(j, k, l, 255).endVertex();
	            tessellator.draw();
	            GlStateManager.popMatrix();
	        }
	    }
	
	public static class LinkFactory implements IParticleFactory{

		@Override
		public LinkParticle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn,
				double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
			return new LinkParticle(worldIn, zSpeedIn, zSpeedIn, zSpeedIn, zSpeedIn, zSpeedIn, zSpeedIn);
		}
		
		public LinkParticle createParticle(World world, Entity start, Entity end){
			return new LinkParticle(world, start, end);
		}
		
	}
	

}
