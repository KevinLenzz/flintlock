package com.example.flintlock.setup;

import com.example.flintlock.Flintlock;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModSetup{
    public static final String GROUP_NAME = Flintlock.MODID;

    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(GROUP_NAME) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Registration.EAGLE_OF_SUGER.get());
        }
    };

    public static void init(final FMLCommonSetupEvent event) {
    }
}
