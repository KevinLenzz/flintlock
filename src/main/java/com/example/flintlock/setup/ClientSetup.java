package com.example.flintlock.setup;

import com.example.flintlock.setup.event.ForgeEventBusEvent;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.lwjgl.glfw.GLFW;

import javax.swing.*;
import javax.swing.text.JTextComponent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    public static final KeyMapping KEYR = new KeyMapping("key.aim", GLFW.GLFW_KEY_R,"key.category.flintlock");
    public static final KeyMapping KEYG = new KeyMapping("key.reload",GLFW.GLFW_KEY_G,"key.category.flintlock");
    @SubscribeEvent
    public static void onClintSetup(final FMLClientSetupEvent event) {
        ClientRegistry.registerKeyBinding(KEYR);
        ClientRegistry.registerKeyBinding(KEYG);
    }
}
