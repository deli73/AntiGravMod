package com.kaikai.antigrav.main;

import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.EntitySelector;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;

public class CommandGravity extends CommandBase {
    public String getName() { return "gravity"; }

    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(args.length < 2) {
            ITextComponent component = new TextComponentString(TextFormatting.RED + getUsage(sender));
            sender.sendMessage(component);
            return;
        }
        String target_str = args[0];
        String activity_str = args[1];

        ArrayList<Entity> targets = new ArrayList<>();
        if(EntitySelector.isSelector(target_str)){
            targets.addAll(EntitySelector.matchEntities(sender,target_str,Entity.class));
        }
        else{
            Entity player = server.getPlayerList().getPlayerByUsername(target_str);
            if(player != null) {
                targets.add(player);
            }
            else{
                ITextComponent component = new TextComponentString(TextFormatting.RED + I18n.format("antigrav.player_not_found")+": "+target_str);
                sender.sendMessage(component);
                return;
            }
        }

        ITextComponent component;
        switch(activity_str){
            case "on":
                for (int i = 0; i < targets.size(); i++) {
                    Entity target = targets.get(i);
                    target.setNoGravity(false);
                }
                component = new TextComponentString(I18n.format("antigrav.gravity_on"));
                break;
            case "off":
                for (int i = 0; i < targets.size(); i++) {
                    Entity target = targets.get(i);
                    target.setNoGravity(true);
                }
                component = new TextComponentString(I18n.format("antigrav.gravity_off"));
                break;
            case "toggle":
                for (int i = 0; i < targets.size(); i++) {
                    Entity target = targets.get(i);
                    target.setNoGravity(!target.hasNoGravity());
                }
                component = new TextComponentString(I18n.format("antigrav.gravity_toggle"));
                break;
            default:
                component = new TextComponentString(TextFormatting.RED + getUsage(sender));
        }
        sender.sendMessage(component);
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return I18n.format("antigrav.commands.gravity.usage");
    }
}
