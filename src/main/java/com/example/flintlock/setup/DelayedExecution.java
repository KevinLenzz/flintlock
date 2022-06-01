package com.example.flintlock.setup;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DelayedExecution {
    Runnable runnable;
    int delayTick;
    IEventBus bus;

    /***
     * @param bus 事件总线
     * @param runnable 要执行的内容
     * @param delayTick 延时的时间
     */
    public DelayedExecution(IEventBus bus, Runnable runnable, int delayTick) {
        this.runnable = runnable;
        this.delayTick = delayTick;

        this.bus = bus;

        bus.register(this);
    }

    public DelayedExecution(Runnable runnable, int delayTick) {
        this(MinecraftForge.EVENT_BUS, runnable, delayTick);
    }

    @SubscribeEvent
    public void tick(TickEvent event) {
        if (delayTick-- <= 0) {
            runnable.run();
            bus.unregister(this);
        }
    }
}