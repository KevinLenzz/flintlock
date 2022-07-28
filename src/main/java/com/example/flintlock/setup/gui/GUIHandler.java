package com.example.flintlock.setup.gui;

import com.example.flintlock.setup.item.FlintlockItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraft.world.InteractionHand.MAIN_HAND;
import static net.minecraft.world.InteractionHand.OFF_HAND;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class GUIHandler {
    public static boolean flag2=true;
    private final static AimGUI AIM_GUI = new AimGUI();
    private final static ReloadHUD R_HUD = new ReloadHUD();
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
                R_HUD.render(event.getMatrixStack(),screenWidth,screenHeight);
            }
        }
    }
}
