package com.kaikai.antigrav.item;

import java.util.concurrent.TimeUnit;

import com.kaikai.antigrav.main.Main;
import com.kaikai.antigrav.main.AntiGravTicks;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;

public class ItemAntiGravSword extends ItemSword {

	public ItemAntiGravSword() {
		super(Main.toolmaterialantigrav);
		this.setRegistryName(new ResourceLocation("antigrav", "antigravsword"));
		this.setUnlocalizedName("antigravsword");
		this.setCreativeTab(CreativeTabs.COMBAT);
	}
	
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		new AntiGravTicks(20, target);
		return true;
	}
}
