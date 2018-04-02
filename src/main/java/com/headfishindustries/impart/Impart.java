package com.headfishindustries.impart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.headfishindustries.impart.entity.EntityTethering;
import com.headfishindustries.impart.item.ItemTethering;
import com.headfishindustries.impart.projection.ProjectionEventHandler;
import com.headfishindustries.impart.proxy.CommonProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

@Mod(modid = Impart.MODID, version = Impart.VERSION)
public class Impart {
	public static final String MODID = "impart";
	public static final String VERSION = "%gradle.version%";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	@Instance(MODID)
	public static Impart instance;
	
	@SidedProxy(serverSide="com.headfishindustries.impart.proxy.CommonProxy", clientSide="com.headfishindustries.impart.proxy.ClientProxy")
	static CommonProxy proxy;
	
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent e){
		Minecraft.getMinecraft().mcProfiler.profilingEnabled = true;
/*		CapabilityManager.INSTANCE.register(IProjection.class, new ProjectionStorage(), Projection.class);
		CapabilityManager.INSTANCE.register(IHasTetherEntity.class, new TetherEntityStorage(), TetherEntity::new);
		MinecraftForge.EVENT_BUS.register(CapPlayerHandle.class);
		MinecraftForge.EVENT_BUS.register(CapItemHandle.class);*/
		MinecraftForge.EVENT_BUS.register(ProjectionEventHandler.class);
		MinecraftForge.EVENT_BUS.register(Impart.class);
		
		
		
		proxy.preInit();
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent e){
		proxy.init();
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> e){
		ImpartedItems.registerItems(e);
	}
	
	@SubscribeEvent
	public static void regModels(ModelRegistryEvent e){
		proxy.registerModels();
	}
	
	//Putting this here because otherwise java complains. I don't blame it, really.
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onRenderGui(RenderHandEvent e){
		EntityPlayer p = Minecraft.getMinecraft().player;
		if (TetherExtension.hasTether(p)){
				GlStateManager.color(1, 1, 1, 0.5f);
		}
	}
}
