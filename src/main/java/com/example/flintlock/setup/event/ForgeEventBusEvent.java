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
            Item theItemInHand=Minecraft.getInstance().player.getItemInHand(Minecraft.getInstance().player.getUsedItemHand()).getItem();
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
