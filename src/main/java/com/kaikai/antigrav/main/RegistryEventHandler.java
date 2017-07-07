package com.kaikai.antigrav.main;

import com.kaikai.antigrav.item.ItemAntiGravDevice;
import com.kaikai.antigrav.item.ItemGravDeflector;
import com.kaikai.antigrav.item.ItemJetpack;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegistryEventHandler {
	
	public static final Item[] ITEMS = {
			new ItemAntiGravDevice(),
			new ItemJetpack(),
			new ItemGravDeflector()
			};
	
	
	@SubscribeEvent
	public static void RegisterItem(RegistryEvent.Register<Item> e) {
		e.getRegistry().registerAll(ITEMS);
	}
    
    @SubscribeEvent
    public static void createRegistries(RegistryEvent.NewRegistry e) {
    	
    }
	
    
    
}
