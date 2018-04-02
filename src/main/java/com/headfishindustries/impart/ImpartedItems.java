package com.headfishindustries.impart;

import com.headfishindustries.impart.item.ItemTethering;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

public class ImpartedItems {
	public static ItemTethering item_tether;
	
	public static void registerItems(RegistryEvent.Register<Item> e){
		item_tether = new ItemTethering();
		e.getRegistry().register(item_tether);
	}

}
