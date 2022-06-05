package com.example.flintlock.setup.event;

import com.example.flintlock.Flintlock;
import com.example.flintlock.setup.particle.SmokeParticle;
import com.example.flintlock.setup.Registration;
import com.example.flintlock.setup.entity.RenderBullet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.AnimationUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Flintlock.MODID,value = Dist.CLIENT,bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvent {
    @SubscribeEvent
    public static void registerParticleFactories(final ParticleFactoryRegisterEvent event){
        Minecraft.getInstance().particleEngine.register(Registration.SMOKE_PARTICLE.get(),SmokeParticle.Provider::new);
    }
    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(Registration.BULLET.get(), RenderBullet::new);
    }
}
