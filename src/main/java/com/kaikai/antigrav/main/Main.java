package com.kaikai.antigrav.main;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid="antigrav", name="2kai2kai2's Anti-Gravity Mod", version="1.0.4")
public class Main {
	@SidedProxy(clientSide="com.kaikai.antigrav.main.ClientProxy",serverSide="com.kaikai.antigrav.main.ServerProxy")
	public static CommonProxy proxy;


    @Mod.EventHandler
    public void serverStart(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandGravity());
    }

	@EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
    
    public static ToolMaterial toolmaterialantigrav = EnumHelper.addToolMaterial("toolmaterialantigrav", 3, 1024, 10, 2f, 20);
}
