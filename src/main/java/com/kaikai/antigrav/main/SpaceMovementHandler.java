package com.kaikai.antigrav.main;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

public class SpaceMovementHandler {
    public static final float JUMP_FORCE = 0.42f;

    @SubscribeEvent
    public static void punch(PlayerInteractEvent.LeftClickBlock e){
        if(!e.getEntityPlayer().hasNoGravity()){return;}
        Vec3d pushVec = Vec3d.ZERO.subtract(e.getEntityPlayer().getLookVec()).normalize().scale(JUMP_FORCE);
        e.getEntityPlayer().motionX += pushVec.x;
        e.getEntityPlayer().motionY += pushVec.y;
        e.getEntityPlayer().motionZ += pushVec.z;
    }

    private static Vec3d convertVector(Vec3i vec){
        return new Vec3d((double)vec.getX(),(double)vec.getY(),(double)vec.getZ());
    }

    public static void kickOff(EntityPlayer player){
        if(player.hasNoGravity()){
            World world = player.getEntityWorld();
            BlockPos center = new BlockPos(player);
            ArrayList<EnumFacing> faces = new ArrayList<>();
            BlockPos d = center.down();
            BlockPos u = center.up().up();
            BlockPos n = center.north();
            BlockPos e = center.east();
            BlockPos w = center.west();
            BlockPos s = center.south();
            if(!isAir(d, world)){faces.add(EnumFacing.DOWN);}
            if(!isAir(u, world)){faces.add(EnumFacing.UP);}
            if(!isAir(n, world)){faces.add(EnumFacing.NORTH);}
            if(!isAir(e, world)){faces.add(EnumFacing.EAST);}
            if(!isAir(w, world)){faces.add(EnumFacing.WEST);}
            if(!isAir(s, world)){faces.add(EnumFacing.SOUTH);}

            Vec3d push = Vec3d.ZERO;
            for(EnumFacing f : faces){
                push = push.add(convertVector(f.getOpposite().getDirectionVec()));
            }
            push = push.normalize().scale(JUMP_FORCE);

            System.out.println("final vector: "+push.x+", "+push.y+", "+push.z);
            System.out.println("vector count: "+faces.size());

            player.motionX += push.x;
            player.motionY += push.y;
            player.motionZ += push.z;
        }
    }
    private static boolean isAir(BlockPos block, World world){
        return world.isAirBlock(block);
    }
}
