package com.example.flintlock.setup.gui;

import com.example.flintlock.Flintlock;
import com.example.flintlock.setup.Registration;
import com.example.flintlock.setup.event.Flags;
import com.example.flintlock.setup.item.FlintlockItem;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.GuiUtils;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static net.minecraft.client.gui.GuiComponent.blit;

public class AimGUI extends GuiComponent {
    private int width;
    private int height;
    private Minecraft minecraft;
    private final ResourceLocation[] l={
            new ResourceLocation(Flintlock.MODID, "textures/gui/pistoll.png"),
            new ResourceLocation(Flintlock.MODID, "textures/gui/flint_musketl.png"),
            new ResourceLocation(Flintlock.MODID, "textures/gui/officers_pistoll.png"),
            new ResourceLocation(Flintlock.MODID, "textures/gui/silver_plated_pistoll.png"),
            new ResourceLocation(Flintlock.MODID, "textures/gui/american_flintlockl.png"),
            new ResourceLocation(Flintlock.MODID, "textures/gui/french_cavalry_pistoll.png")
    };
    private final ResourceLocation[] r={
            new ResourceLocation(Flintlock.MODID, "textures/gui/pistolr.png"),
            new ResourceLocation(Flintlock.MODID, "textures/gui/flint_musketr.png"),
            new ResourceLocation(Flintlock.MODID, "textures/gui/officers_pistolr.png"),
            new ResourceLocation(Flintlock.MODID, "textures/gui/silver_plated_pistolr.png"),
            new ResourceLocation(Flintlock.MODID, "textures/gui/american_flintlockr.png"),
            new ResourceLocation(Flintlock.MODID, "textures/gui/french_cavalry_pistolr.png")
    };
    private final ResourceLocation[] ui={
            new ResourceLocation(Flintlock.MODID, "textures/gui/pistolui.png"),
            new ResourceLocation(Flintlock.MODID, "textures/gui/flint_musketui.png"),
            new ResourceLocation(Flintlock.MODID, "textures/gui/officers_pistolui.png"),
            new ResourceLocation(Flintlock.MODID, "textures/gui/silver_plated_pistolui.png"),
            new ResourceLocation(Flintlock.MODID, "textures/gui/american_flintlockui.png"),
            new ResourceLocation(Flintlock.MODID, "textures/gui/french_cavalry_pistolui.png")
    };
    public AimGUI() {
        this.width = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        this.height = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        this.minecraft = Minecraft.getInstance();
    }

    public void render(PoseStack poseStack,int screenWidth, int screenHeight) {
        poseStack.pushPose();
        RenderSystem.enableBlend();
        Item theItemM=Minecraft.getInstance().player.getItemInHand(InteractionHand.MAIN_HAND).getItem();
        Item theItemO=Minecraft.getInstance().player.getItemInHand(InteractionHand.OFF_HAND).getItem();
        if (Minecraft.getInstance().player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == Registration.PISTOLITEM.get()) {
            if(theItemM instanceof FlintlockItem theFlintlock) {
                if (theFlintlock.flag) {
                    RenderSystem.setShaderTexture(0, ui[0]);
                } else {
                    RenderSystem.setShaderTexture(0, r[0]);
                }
            }
        } else if (Minecraft.getInstance().player.getItemInHand(InteractionHand.OFF_HAND).getItem() == Registration.PISTOLITEM.get()) {
            if(theItemM instanceof FlintlockItem theFlintlock) {
                if (theFlintlock.flag) {
                    RenderSystem.setShaderTexture(0, ui[0]);
                } else {
                    RenderSystem.setShaderTexture(0, l[0]);
                }
            }
        }
        if (Minecraft.getInstance().player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == Registration.FLINT_MUSKETITEM.get()) {
            if(theItemM instanceof FlintlockItem theFlintlock) {
                if (theFlintlock.flag) {
                    RenderSystem.setShaderTexture(0, ui[1]);
                } else {
                    RenderSystem.setShaderTexture(0, r[1]);
                }
            }
        } else if (Minecraft.getInstance().player.getItemInHand(InteractionHand.OFF_HAND).getItem() == Registration.FLINT_MUSKETITEM.get()) {
            if(theItemM instanceof FlintlockItem theFlintlock) {
                if (theFlintlock.flag) {
                    RenderSystem.setShaderTexture(0, ui[1]);
                } else {
                    RenderSystem.setShaderTexture(0, l[1]);
                }
            }
        }
        if (Minecraft.getInstance().player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == Registration.OFFICERS_PISTOL.get()) {
            if(theItemM instanceof FlintlockItem theFlintlock) {
                if (theFlintlock.flag) {
                    RenderSystem.setShaderTexture(0, ui[2]);
                } else {
                    RenderSystem.setShaderTexture(0, r[2]);
                }
            }
        } else if (Minecraft.getInstance().player.getItemInHand(InteractionHand.OFF_HAND).getItem() == Registration.OFFICERS_PISTOL.get()) {
            if(theItemM instanceof FlintlockItem theFlintlock) {
                if (theFlintlock.flag) {
                    RenderSystem.setShaderTexture(0, ui[2]);
                } else {
                    RenderSystem.setShaderTexture(0, l[2]);
                }
            }
        }
        if (Minecraft.getInstance().player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == Registration.SILVER_PLATED_PISTOL.get()) {
            if(theItemM instanceof FlintlockItem theFlintlock) {
                if (theFlintlock.flag) {
                    RenderSystem.setShaderTexture(0, ui[3]);
                } else {
                    RenderSystem.setShaderTexture(0, r[3]);
                }
            }
        } else if (Minecraft.getInstance().player.getItemInHand(InteractionHand.OFF_HAND).getItem() == Registration.SILVER_PLATED_PISTOL.get()) {
            if(theItemM instanceof FlintlockItem theFlintlock) {
                if (theFlintlock.flag) {
                    RenderSystem.setShaderTexture(0, ui[3]);
                } else {
                    RenderSystem.setShaderTexture(0, l[3]);
                }
            }
        }
        if (Minecraft.getInstance().player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == Registration.AMERICAN_FLINTLOCK.get()) {
            if(theItemM instanceof FlintlockItem theFlintlock) {
                if (theFlintlock.flag) {
                    RenderSystem.setShaderTexture(0, ui[4]);
                } else {
                    RenderSystem.setShaderTexture(0, r[4]);
                }
            }
        } else if (Minecraft.getInstance().player.getItemInHand(InteractionHand.OFF_HAND).getItem() == Registration.AMERICAN_FLINTLOCK.get()) {
            if(theItemM instanceof FlintlockItem theFlintlock) {
                if (theFlintlock.flag) {
                    RenderSystem.setShaderTexture(0, ui[4]);
                } else {
                    RenderSystem.setShaderTexture(0, l[4]);
                }
            }
        }
        if (Minecraft.getInstance().player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == Registration.FRENCH_CAVALRY_PISTOL.get()) {
            if(theItemM instanceof FlintlockItem theFlintlock) {
                if (theFlintlock.flag) {
                    RenderSystem.setShaderTexture(0, ui[5]);
                } else {
                    RenderSystem.setShaderTexture(0, r[5]);
                }
            }
        } else if (Minecraft.getInstance().player.getItemInHand(InteractionHand.OFF_HAND).getItem() == Registration.FRENCH_CAVALRY_PISTOL.get()) {
            if(theItemM instanceof FlintlockItem theFlintlock) {
                if (theFlintlock.flag) {
                    RenderSystem.setShaderTexture(0, ui[5]);
                } else {
                    RenderSystem.setShaderTexture(0, l[5]);
                }
            }
        }
        this.blit(poseStack, 0, 0,0, 0, 0, screenWidth, screenHeight,screenWidth,screenHeight);
        //坐标x 坐标y 坐标z(无用) 图片x 图片y 图片在屏幕上渲染到的x 图片在屏幕上渲染到的y 缩放width 缩放height
    }
}
