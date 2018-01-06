package com.kaikai.antigrav.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
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
	
	public void onUpdate(ItemStack s, World w, Entity e, int slot, boolean selected) {
		if (selected && e.hasNoGravity()) {
			e.fallDistance = 0; //Take no fall damage when holding jetpack.

			if (e.isSneaking()) {
				if (e.motionX < 0.05) {e.motionX = 0f;}
				if (e.motionY < 0.05) {e.motionY = 0f;}
				if (e.motionZ < 0.05) {e.motionZ = 0f;}
				e.motionX *= 0.6;//TODO change this so it slows you down based on the direction you're moving
				e.motionY *= 0.6;
				e.motionZ *= 0.6;
			}
		}
	}
}
