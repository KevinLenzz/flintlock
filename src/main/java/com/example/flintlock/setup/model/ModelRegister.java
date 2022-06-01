package com.example.flintlock.setup.model;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

//@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
//public class ModelRegister {
//    @SubscribeEvent
//    public static void register(EntityRenderersEvent.RegisterRenderers event) {
//        ForgeHooksClient.registerLayerDefinition(FlintlockHoldModel.LAYER_LOCATION, FlintlockHoldModel::createBodyLayer);
//    }
//    private static <E extends Entity> void registerEntityRenderer(EntityType<E> type, EntityRendererProvider<E> renderer){
//        EntityRenderers.register(type, renderer);
//    }
//}