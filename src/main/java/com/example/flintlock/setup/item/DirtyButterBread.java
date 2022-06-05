package com.example.flintlock.setup.item;

import com.example.flintlock.setup.ModSetup;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class DirtyButterBread extends Item {
    public static FoodProperties fp=(new FoodProperties.Builder())
            .nutrition(4)
            .saturationMod(0.3F)
            .build();
    public DirtyButterBread(){
        super(new Properties().food(fp).tab(ModSetup.ITEM_GROUP).stacksTo(16));
    }
}
