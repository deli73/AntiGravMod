package com.kaikai.antigrav.main;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;

public class AntiGravTicks {
	private int tick = 0;
	private EntityLivingBase elb;
	private int ticks;
	
	public AntiGravTicks(int ticks, EntityLivingBase elb) {
		elb.setNoGravity(true);
		MinecraftForge.EVENT_BUS.register(this);
		this.ticks = ticks;
		this.elb = elb;
		elb.motionY *= 0.8;
	}
	
	@SubscribeEvent
	public void ServerTick(ServerTickEvent e) {
		if (e.phase==Phase.END) {
			
			if (this.tick == ticks) { //Stop when done
				elb.setNoGravity(false); 
				//Delete this instance as much as I can.
				MinecraftForge.EVENT_BUS.unregister(this);
				nullify(tick);
				nullify(elb);
				nullify(ticks);
				nullify(this);
			}
			
			else {//Increase number of ticks passed.
				tick++;
				elb.motionX *= 1.1;
				elb.motionZ *= 1.1;
			}
		}
	}
	
	
	
	public static void nullify(Object o) {
		o = null;
	}
}
