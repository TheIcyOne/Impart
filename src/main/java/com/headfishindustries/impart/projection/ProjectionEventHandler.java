package com.headfishindustries.impart.projection;

import com.headfishindustries.impart.TetherExtension;
import com.headfishindustries.impart.particle.LinkParticle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class ProjectionEventHandler {
	
	@SubscribeEvent
	public static void onPlayerTick(LivingUpdateEvent e){
		if (e.getEntityLiving() instanceof EntityPlayer){
			EntityPlayer p = (EntityPlayer) e.getEntityLiving();
			if (TetherExtension.hasTether(p)){
				TetherExtension ex = TetherExtension.For(p);
					p.capabilities.isFlying = true;
					if (p.world.getTotalWorldTime() % 20 == 0){
						for (double i = 0; i*i <= p.getPositionVector().squareDistanceTo(ex.getTetherPos()); i+= 0.8){
							Vec3d v = ex.getTetherPos().subtract(0, 2.6, 0).subtract(p.getPositionVector().subtract(0, 1 - p.getYOffset(), 0)).normalize().scale(i).add(p.getPositionVector());
							p.world.spawnParticle(EnumParticleTypes.CRIT, true, v.x, v.y, v.z, 0, 0, 0, 0);
						}
					}
					if (p.getPositionVector().squareDistanceTo(ex.getTetherPos()) > ex.getRange() * ex.getRange()){
//							LinkParticle link = new LinkParticle(p.getEntityWorld(), TetherExtension.playerToEntity.get(p), p);
//							Minecraft.getMinecraft().effectRenderer.addEffect(link);
							
							//Classic bit of 3-dimensional elasticity.
							Vec3d resistance = ex.getTetherPos().subtract(p.getPositionVector()).scale(0.025);
							p.addVelocity(resistance.x, resistance.y, resistance.z);
					}
					
				}else{
					if (!p.capabilities.allowFlying){
						p.capabilities.isFlying = false;
					}
			}
		}
	}
	
	@SubscribeEvent
	public static void onRenderPlayer(RenderLivingEvent.Pre<EntityPlayer> e){
		if (!(e.getEntity() instanceof EntityPlayer)) return;
		EntityPlayer player = (EntityPlayer) e.getEntity();
		if (TetherExtension.hasTether(player)){
			GlStateManager.color(1, 1, 1, 0.5f);
		}
	}
	
	@SubscribeEvent
	public static void onRenderPlayer(RenderLivingEvent.Post<EntityPlayer> e){
		if (!(e.getEntity() instanceof EntityPlayer)) return;
		EntityPlayer player = (EntityPlayer) e.getEntity();
		if (TetherExtension.hasTether(player)){
			GlStateManager.color(1, 1, 1, 1);
		}
	}
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onRenderGui(RenderHandEvent e){
		EntityPlayer p = Minecraft.getMinecraft().player;
		if (TetherExtension.hasTether(p)){
				GlStateManager.color(1, 1, 1, 1);
		}
	}
	
}
