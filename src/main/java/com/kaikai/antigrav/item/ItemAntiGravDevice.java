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

public class ItemAntiGravDevice extends Item {
	public ItemAntiGravDevice() {
		this.setRegistryName(new ResourceLocation("antigrav", "antigravdevice"));
		this.setUnlocalizedName("antigravdevice");
		this.setCreativeTab(CreativeTabs.TRANSPORTATION);
		this.setMaxStackSize(1);
	}
	
	public ActionResult<ItemStack> onItemRightClick(World w, EntityPlayer p, EnumHand h) {
		if (p.hasNoGravity()) {
		p.setNoGravity(false);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, p.getHeldItem(h));
		}
		else {
		p.setNoGravity(true);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, p.getHeldItem(h));
		}
		
	}
	
	public boolean onDroppedByPlayer(Item i, EntityPlayer p) {
		if (p.hasNoGravity()) {return false;}
		else {return true;}
		
	}
	
	public void onUpdate(ItemStack s, World w, Entity e, int slot, boolean selected) {
		if (e.hasNoGravity()) {//Counteract the natural slowing down.
			e.motionX *= 1.098;
			e.motionZ *= 1.098;
			e.motionY *= 1/0.98;
		}
	}
	
}
