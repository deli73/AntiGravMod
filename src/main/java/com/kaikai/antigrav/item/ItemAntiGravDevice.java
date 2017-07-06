package com.kaikai.antigrav.item;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
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
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;

public class ItemAntiGravDevice extends Item {
	public ItemAntiGravDevice() {
		this.setRegistryName(new ResourceLocation("antigrav", "antigravdevice"));
		this.setUnlocalizedName("antigravdevice");
		this.setCreativeTab(CreativeTabs.TRANSPORTATION);
		this.setMaxStackSize(1);
		//ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(this.getRegistryName(),"inventory"));
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
			e.motionX *= 1.1;
			e.motionZ *= 1.1;
		}
	}
	
}
