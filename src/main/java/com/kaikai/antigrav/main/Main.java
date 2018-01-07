package com.kaikai.antigrav.main;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.UUID;

@Mod(modid="antigrav", name="2kai2kai2's Anti-Gravity Mod", version="T.0.1.1")
public class Main {
	@SidedProxy(clientSide="com.kaikai.antigrav.main.ClientProxy",serverSide="com.kaikai.antigrav.main.ServerProxy")
	public static CommonProxy proxy;

    private static final UUID MOVEMENT_SPEED_MODIFIER_UUID = UUID.fromString("1a6aac67-5e25-4389-ba8c-2ca3a44033b0");

    @Mod.EventHandler
    public void serverStart(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandGravity());
    }

	@EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        proxy.preInit(e);
        MinecraftForge.EVENT_BUS.register(proxy);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(SpaceMovementHandler.class);
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

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void update(LivingEvent.LivingUpdateEvent e) { //stop non-player entities from moving normally
        EntityLivingBase entity = e.getEntityLiving();
        if(entity.getClass().isAssignableFrom(EntityPlayer.class) || entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getModifier(MOVEMENT_SPEED_MODIFIER_UUID) != null){
            return;
        }
        entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(
                new AttributeModifier(MOVEMENT_SPEED_MODIFIER_UUID,"Antigravity movement speed modifier",
                        0.0,1)
        );
    }
}

//TODO: EVENT STUFF TO MAKE OTHER ENTITIES WORK RIGHT WHEN PUNCHED WITH NOGRAV BUT ALSO CHECK IF THEY HAVE NOAI ON AND DON'T DO IT THEN
//TODO: ADD SOUNDS AND PARTICLES TO JETPACK
