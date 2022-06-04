package com.example.flintlock.setup;

import com.example.flintlock.Flintlock;
import com.example.flintlock.setup.network.Messages;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = Flintlock.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSetup{
    public static final String GROUP_NAME = Flintlock.MODID;

    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(GROUP_NAME) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Registration.FRENCH_CAVALRY_PISTOL.get());
        }
    };
    public static void init(final FMLCommonSetupEvent event) {
    }
    @SubscribeEvent
    public static void OnCommonSetup(FMLCommonSetupEvent event){
        Messages.register();
    }
}
