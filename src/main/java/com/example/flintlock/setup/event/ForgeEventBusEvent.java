package com.example.flintlock.setup.event;

import com.example.flintlock.setup.item.FlintlockItem;
import com.example.flintlock.setup.network.Messages;
import com.example.flintlock.setup.network.PGM2;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.example.flintlock.setup.ClientSetup.KEYG;
import static com.example.flintlock.setup.ClientSetup.KEYR;
import static com.example.flintlock.setup.event.Flags.*;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.FORGE,value= Dist.CLIENT)
public class ForgeEventBusEvent {
    public static int timer=0;
    @SubscribeEvent
    public static void onKeyboardInput(InputEvent.KeyInputEvent event) {
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
    @SubscribeEvent
    public static void PlayerTickEvent(TickEvent.PlayerTickEvent event) {
        if (Minecraft.getInstance().player!=null&&Minecraft.getInstance().player.getItemInHand(Minecraft.getInstance().player.getUsedItemHand()).getItem()!=null) {
            Item theItemInHand = Minecraft.getInstance().player.getItemInHand(Minecraft.getInstance().player.getUsedItemHand()).getItem();
            if (theItemInHand instanceof FlintlockItem theFlintlock) {
                if (startTimer) {
                    timer++;
                    if (timer % 20 == 0) {
                        Minecraft.getInstance().player.sendMessage(new TextComponent(timer * 100 / theFlintlock.duraTag + "%"), Minecraft.getInstance().player.getUUID());
                    }
                } else if (timer != 0) {
                    timer = 0;
                }
                if (timer >= theFlintlock.duraTag) {
                    startTimer = false;
                    theFlintlock.reloadflag = true;
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
