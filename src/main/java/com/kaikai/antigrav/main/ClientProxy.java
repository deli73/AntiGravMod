package com.kaikai.antigrav.main;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy{

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
	}

	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
		regitemmod(0);//antigravdevice
		regitemmod(1);//jetpack
		regitemmod(2);//gravdeflector
		regitemmod(3);//antigravsword
		
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}
	
	public static void registerModel(Item i) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, 0, new ModelResourceLocation(i.getRegistryName(), "inventory"));
	}
	public static void regitemmod(int i) { //int is index in RegistryEventHandler.ITEMS[i]
		registerModel(RegistryEventHandler.ITEMS[i]);
	}

	@SubscribeEvent
	public void tick(TickEvent.ClientTickEvent e) {
		Minecraft mc = Minecraft.getMinecraft();
		GameSettings s = mc.gameSettings;
		if(e.phase == TickEvent.Phase.START && mc.player != null && mc.player.hasNoGravity() && !mc.player.isCreative()){
			mc.player.onGround = false;
			//just fucking murder the movement key events, because voring them doesn't work apparently
			KeyBinding.setKeyBindState(s.keyBindForward.getKeyCode(), false);
			KeyBinding.setKeyBindState(s.keyBindLeft.getKeyCode(), false);
			KeyBinding.setKeyBindState(s.keyBindBack.getKeyCode(), false);
			KeyBinding.setKeyBindState(s.keyBindRight.getKeyCode(), false);
		}
		if(e.phase == TickEvent.Phase.START && mc.player != null && mc.player.hasNoGravity() &&
				s.keyBindJump.isKeyDown() &&
				(mc.player.getHeldItemMainhand().getItem() == RegistryEventHandler.JETPACK || mc.player.getHeldItemOffhand().getItem() == RegistryEventHandler.JETPACK)){
			EntityPlayer p = mc.player;
			double x = 0;
			double y = 0;
			double z = 0;

			double RadYaw = 0;
			if (p.rotationYaw < 0) {RadYaw = Math.toRadians(p.rotationYaw + 360);}
			else {RadYaw = Math.toRadians(p.rotationYaw);}

			double RadPitch = Math.toRadians(p.rotationPitch+90);

			z = (Math.cos(RadYaw) * Math.sin(RadPitch));
			x = -(Math.sin(RadYaw) * Math.sin(RadPitch));
			y = Math.cos(RadPitch);

			//Apply to motion
			p.motionX += x*0.05;
			p.motionY += y*0.05;
			p.motionZ += z*0.05;
		}
	}

	@SubscribeEvent
	public void renderTick(TickEvent.PlayerTickEvent e){
		Minecraft mc = Minecraft.getMinecraft();
		GameSettings s = mc.gameSettings;
		if(mc.player.isCreative()){return;} // don't cancel out player movement in creative
		if(e.phase == TickEvent.Phase.START && mc.player != null && mc.player.hasNoGravity()){
			//just fucking murder the movement key events, because voring them doesn't work apparently
			KeyBinding.setKeyBindState(s.keyBindForward.getKeyCode(), false);
			KeyBinding.setKeyBindState(s.keyBindLeft.getKeyCode(), false);
			KeyBinding.setKeyBindState(s.keyBindBack.getKeyCode(), false);
			KeyBinding.setKeyBindState(s.keyBindRight.getKeyCode(), false);
		}
	}

}
