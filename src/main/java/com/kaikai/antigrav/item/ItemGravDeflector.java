package com.kaikai.antigrav.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemGravDeflector extends Item {
	public ItemGravDeflector() {
		this.setRegistryName(new ResourceLocation("antigrav", "gravdeflector"));
		this.setUnlocalizedName("gravdeflector");
		this.setCreativeTab(CreativeTabs.MATERIALS);
		this.setMaxStackSize(16);
	}
}
