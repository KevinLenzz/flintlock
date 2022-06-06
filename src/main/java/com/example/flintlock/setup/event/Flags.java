package com.example.flintlock.setup.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class Flags {
    public static boolean startTimer=false;

    public static boolean flag=false;
    public static boolean reloadflag=false;
    public static boolean shoot=false;
    public static boolean sent=false;
}
