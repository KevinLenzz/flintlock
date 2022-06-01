package com.example.flintlock.setup.gui;

import com.example.flintlock.setup.ItemPro.FlintlockItem;
import com.example.flintlock.setup.ReflectUtil;
import com.example.flintlock.setup.Registration;
import com.example.flintlock.setup.event.ForgeEventBusEvent;
import net.minecraft.client.HotbarManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.GuiUtils;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.lang.reflect.Field;

import static net.minecraft.world.InteractionHand.MAIN_HAND;
import static net.minecraft.world.InteractionHand.OFF_HAND;
import static net.minecraftforge.client.gui.ForgeIngameGui.HOTBAR_ELEMENT;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class GUIHandler {
    public static boolean flag2=true;
    private final static AimGUI AIM_GUI = new AimGUI();
    @SubscribeEvent(receiveCanceled = true)
    public static void onRenderGameOverlayEvent(RenderGameOverlayEvent.Post event) throws IllegalAccessException {
//        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
//            return;
//        }
        if (Minecraft.getInstance().player == null || !(Minecraft.getInstance().player.getItemInHand(MAIN_HAND).getItem() instanceof FlintlockItem)&&!(Minecraft.getInstance().player.getItemInHand(OFF_HAND).getItem() instanceof FlintlockItem)) {
            Minecraft.getInstance().gameRenderer.setRenderHand(true);
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        int screenHeight = event.getWindow().getGuiScaledHeight();
        int screenWidth = event.getWindow().getGuiScaledWidth();
        LocalPlayer playerEntity = Minecraft.getInstance().player;
        if (event.getType() == RenderGameOverlayEvent.ElementType.LAYER) {
            if (playerEntity != null &&mc.options.getCameraType().isFirstPerson()) {
                mc.gameRenderer.setRenderHand(false);
                AIM_GUI.render(event.getMatrixStack(),screenWidth,screenHeight);
            }
        }
    }
}
