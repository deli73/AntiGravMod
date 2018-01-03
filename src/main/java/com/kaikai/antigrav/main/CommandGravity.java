package com.kaikai.antigrav.main;

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
        }
        String target_str = args[0];
        String activity_str = args[1];

        ArrayList<Entity> targets = new ArrayList<>();
        if(EntitySelector.isSelector(target_str)){
            targets.addAll(EntitySelector.matchEntities(sender,target_str,Entity.class));
        }
        else{
            targets.add(server.getPlayerList().getPlayerByUsername(target_str));
        }

        switch(activity_str){
            case "on":
                for (int i = 0; i < targets.size(); i++) {
                    Entity target = targets.get(i);
                    target.setNoGravity(false);
                }
                break;
            case "off":
                for (int i = 0; i < targets.size(); i++) {
                    Entity target = targets.get(i);
                    target.setNoGravity(true);
                }
                break;
            case "toggle":
                for (int i = 0; i < targets.size(); i++) {
                    Entity target = targets.get(i);
                    target.setNoGravity(!target.hasNoGravity());
                }
                break;
            default:
                ITextComponent component = new TextComponentString(TextFormatting.RED + getUsage(sender));
                sender.sendMessage(component);
        }
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "antigrav.commands.gravity.usage";
    }
}
