package com.example.flintlock.setup.gui;

import com.example.flintlock.Flintlock;
import com.example.flintlock.setup.event.ForgeEventBusEvent;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;

public class ReloadHUD extends GuiComponent {
    private int width;
    private int height;
    private Minecraft minecraft;
    private final ResourceLocation[] ui= {
            new ResourceLocation(Flintlock.MODID, "textures/gui/1.png"),
            new ResourceLocation(Flintlock.MODID, "textures/gui/2.png"),
            new ResourceLocation(Flintlock.MODID, "textures/gui/3.png"),
            new ResourceLocation(Flintlock.MODID, "textures/gui/4.png"),
            new ResourceLocation(Flintlock.MODID, "textures/gui/reloaded.png")
    };
    public ReloadHUD() {
        this.width = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        this.height = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        this.minecraft = Minecraft.getInstance();
    }
    public void render(PoseStack poseStack, int screenWidth, int screenHeight) {
            poseStack.pushPose();
            RenderSystem.enableBlend();
            if(ForgeEventBusEvent.uinum!=-1) {
                RenderSystem.setShaderTexture(0, ui[ForgeEventBusEvent.uinum]);
                this.blit(poseStack, screenWidth/5*4, screenHeight/8*7,0, 0, 0, screenWidth/5, screenHeight/8,screenWidth/5,screenHeight/8);
            }
    }
}
