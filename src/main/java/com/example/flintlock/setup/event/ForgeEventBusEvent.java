package com.example.flintlock.setup.event;

import com.example.flintlock.Flintlock;
import com.example.flintlock.setup.ClientSetup;
import com.example.flintlock.setup.ItemPro.FlintlockItem;
import com.example.flintlock.setup.ItemPro.ItemBullet;
import com.example.flintlock.setup.Registration;
import com.example.flintlock.setup.entity.EntityBullet;
import com.example.flintlock.setup.network.Messages;
import com.example.flintlock.setup.network.PGM;
import com.example.flintlock.setup.network.PGM2;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import org.jline.terminal.Attributes;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.MixinEnvironment;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import java.util.List;
import java.util.function.BooleanSupplier;

import static com.example.flintlock.setup.ClientSetup.KEYG;
import static com.example.flintlock.setup.ClientSetup.KEYR;
import static com.example.flintlock.setup.Registration.SHOOT;
import static com.example.flintlock.setup.event.Flags.*;
import static com.example.flintlock.setup.network.Messages.sendToServer;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventBusEvent {
    public static int timer=0;
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
    public static void ClientTickEvent(TickEvent.ClientTickEvent event){
        if(startTimer){
            timer++;
        }else if(timer!=0){
            timer=0;
        }
        if(timer>= FlintlockItem.duraTag){
            startTimer=false;
            reloadflag=true;
            Minecraft.getInstance().player.sendMessage(new TextComponent("reloaded"),Minecraft.getInstance().player.getUUID());
        }
        if(flag&&reloadflag&&!sent){
            Messages.sendToServer(new PGM2());
            sent=true;
        }
        if(!reloadflag){
            sent=false;
        }
    }
}
