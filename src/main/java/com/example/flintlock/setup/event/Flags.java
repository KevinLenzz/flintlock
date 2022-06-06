package com.example.flintlock.setup.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class Flags {
    public static boolean startTimer=false;

//    public static boolean flag=false;
//    public static boolean reloadflag=false;
    public static boolean sent=false;
}
