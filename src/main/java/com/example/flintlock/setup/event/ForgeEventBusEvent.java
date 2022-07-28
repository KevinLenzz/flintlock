package com.example.flintlock.setup.event;

import com.example.flintlock.Flintlock;
import com.example.flintlock.setup.item.FlintlockItem;
import com.example.flintlock.setup.network.Messages;
import com.example.flintlock.setup.network.PGM2;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;

import static com.example.flintlock.setup.ClientSetup.KEYG;
import static com.example.flintlock.setup.ClientSetup.KEYR;
import static com.example.flintlock.setup.event.Flags.*;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.FORGE,value= Dist.CLIENT)
public class ForgeEventBusEvent {
    public static int timer=0;
    public static int uinum=-1;
    @SubscribeEvent
    public static void onKeyboardInput(InputEvent.KeyInputEvent event) {
        if(Minecraft.getInstance().player!=null) {
            Item theItemInHand = Minecraft.getInstance().player.getItemInHand(Minecraft.getInstance().player.getUsedItemHand()).getItem();
            if (theItemInHand instanceof FlintlockItem theFlintlock) {
                if (KEYR.isDown() && startTimer == false) {
                    assert Minecraft.getInstance().player != null;
//            Minecraft.getInstance().player.sendMessage(new TextComponent("You Press k"),Minecraft.getInstance().player.getUUID());

                    if (theFlintlock.flag) {
                        theFlintlock.flag = false;
                    } else {
                        theFlintlock.flag = true;
                    }
                }
                if (KEYG.isDown()) {
                    assert Minecraft.getInstance().player != null;
//            Minecraft.getInstance().player.sendMessage(new TextComponent("You Press k"),Minecraft.getInstance().player.getUUID());
                    if (timer == 0) {
                        startTimer = true;
                        theFlintlock.flag = false;
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void PlayerTickEvent(TickEvent.PlayerTickEvent event) {
        if (Minecraft.getInstance().player!=null&&Minecraft.getInstance().player.getItemInHand(Minecraft.getInstance().player.getUsedItemHand()).getItem()!=null) {
            Item theItemInHand = Minecraft.getInstance().player.getItemInHand(Minecraft.getInstance().player.getUsedItemHand()).getItem();
            if (theItemInHand instanceof FlintlockItem theFlintlock) {
                if (startTimer) {
                    timer++;
//                    Minecraft.getInstance().player.sendMessage(new TextComponent(timer * 100 / theFlintlock.duraTag + "%"), Minecraft.getInstance().player.getUUID());
                    if(timer * 100 / theFlintlock.duraTag<20){
                        uinum=0;
                    }else if(timer * 100 / theFlintlock.duraTag<40){
                        uinum=1;
                    }else if(timer * 100 / theFlintlock.duraTag<60){
                        uinum=2;
                    }else if(timer * 100 / theFlintlock.duraTag<80){
                        uinum=3;
                    }
                } else if (timer != 0) {
                    timer = 0;
                }
                if (timer >= theFlintlock.duraTag) {
                    startTimer = false;
                    theFlintlock.reloadflag = true;
                    uinum=-1;
                    Minecraft.getInstance().player.sendMessage(new TextComponent("reloaded"), Minecraft.getInstance().player.getUUID());
                }
                if (theFlintlock.flag && theFlintlock.reloadflag && !sent) {
                    Messages.sendToServer(new PGM2());
                    sent = true;
                }
                if (!theFlintlock.reloadflag) {
                    sent = false;
                }
            } else {
                startTimer = false;
                timer = 0;
            }
        }
    }
}
