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
			p.motionX *= 0.6;//TODO change this so it slows you down based on the direction you're moving
			p.motionY *= 0.6;
			p.motionZ *= 0.6;
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, p.getHeldItem(h));
		}
		
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
		if (p.hasNoGravity()) {
			p.motionX += x*0.25;
			p.motionY += y*0.25;
			p.motionZ += z*0.25;
		} else {
			p.motionX += x*1.25;
			p.motionY += y*1.25;
			p.motionZ += z*1.25;
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.PASS, p.getHeldItem(h));
		
	}
	
	public void onUpdate(ItemStack s, World w, Entity e, int slot, boolean selected) {
		if (selected) {
			e.fallDistance = 0; //Take no fall damage when holding jetpack.
		}
	}
}
