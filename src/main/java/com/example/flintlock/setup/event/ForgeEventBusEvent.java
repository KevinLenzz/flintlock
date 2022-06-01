package com.example.flintlock.setup.event;

import com.example.flintlock.Flintlock;
import com.example.flintlock.setup.ClientSetup;
import com.example.flintlock.setup.ItemPro.FlintlockItem;
import com.example.flintlock.setup.Registration;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import javax.swing.*;
import javax.swing.text.JTextComponent;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class ForgeEventBusEvent {
    public static int timer=0;
    public static boolean startTimer=false;
    public static final KeyMapping KEYR = new KeyMapping("key.aim",82,"key.category.flintlock");
    public static final KeyMapping KEYG = new KeyMapping("key.reload",71,"key.category.flintlock");
    public static boolean flag=false;
    public static boolean reloadflag=false;
    @SubscribeEvent
    public static void onKeyboardInput(InputEvent.KeyInputEvent event) {
        if (KEYR.isDown()&&startTimer==false) {
            assert Minecraft.getInstance().player != null;
//            Minecraft.getInstance().player.sendMessage(new TextComponent("You Press k"),Minecraft.getInstance().player.getUUID());
            if(flag){
                flag=false;
            }
            else {
                flag=true;
            }
        }
        if (KEYG.isDown()) {
            assert Minecraft.getInstance().player != null;
//            Minecraft.getInstance().player.sendMessage(new TextComponent("You Press k"),Minecraft.getInstance().player.getUUID());
            Item theItemInHand=Minecraft.getInstance().player.getItemInHand(InteractionHand.MAIN_HAND).getItem();
            if(theItemInHand instanceof FlintlockItem){
                if(timer==0){
                    startTimer=true;
                    flag=false;
                }
            }
        }
    }
    @SubscribeEvent
    public static void PlayerTickEvent(TickEvent.PlayerTickEvent event){
        if(startTimer){
            timer++;
            System.out.println(timer);
        }else if(timer!=0){
            timer=0;
        }
        if(timer>= FlintlockItem.duraTag){
            startTimer=false;
            reloadflag=true;
        }
    }
}
