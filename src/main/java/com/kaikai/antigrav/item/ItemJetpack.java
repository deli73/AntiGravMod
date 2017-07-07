package com.kaikai.antigrav.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemJetpack extends Item {
	public ItemJetpack() {
		this.setRegistryName(new ResourceLocation("antigrav", "jetpack"));
		this.setUnlocalizedName("jetpack");
		this.setCreativeTab(CreativeTabs.TRANSPORTATION);
		this.setMaxStackSize(1);
	}
	
	public ActionResult<ItemStack> onItemRightClick(World w, EntityPlayer p, EnumHand h) {
		
		if (p.isSneaking()) {
			if (p.motionX < 0.05) {p.motionX = 0f;}
			if (p.motionY < 0.05) {p.motionY = 0f;}
			if (p.motionZ < 0.05) {p.motionZ = 0f;}
			p.motionX *= 0.6;
			p.motionY *= 0.6;
			p.motionZ *= 0.6;
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, p.getHeldItem(h));
		}
		
		//I'm not sure, but I think there's probably a better way to do this in 3d space not 2d + up/down
		
		float x = 0f;
		float y = 0f;
		float z = 0f;
		
		//Yaw
		float DegYaw = 0f;
		if (p.rotationYaw < 0) {DegYaw = p.rotationYaw + 360;}
		else {DegYaw = p.rotationYaw;}
		x = (float) -Math.sin(Math.toRadians(DegYaw));
		z = (float) Math.cos(Math.toRadians(DegYaw));
		//Pitch
		float DegPitch = -(p.rotationPitch);
		y = DegPitch/90;
		
		//Add to motion
		if (p.hasNoGravity()) {
			p.motionX += x*0.25;
			p.motionY += y*0.25;
			p.motionZ += z*0.25;
		} else {
			p.motionX += x*1.25;
			p.motionY += y*1.25;
			p.motionZ += z*1.25;
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, p.getHeldItem(h));
		
	}
	
	public void onUpdate(ItemStack s, World w, Entity e, int slot, boolean selected) {
		if (selected) {
			e.fallDistance = 0; //Take no fall damage when holding jetpack.
		}
	}
}
