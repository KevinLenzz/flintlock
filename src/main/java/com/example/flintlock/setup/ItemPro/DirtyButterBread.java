package com.example.flintlock.setup.ItemPro;

import com.example.flintlock.setup.ModSetup;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.FoodOnAStickItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.pipeline.LightUtil;
import net.minecraftforge.common.Tags;

public class DirtyButterBread extends Item {
    public static FoodProperties fp=(new FoodProperties.Builder())
            .nutrition(4)
            .saturationMod(0.3F)
            .build();
    public DirtyButterBread(){
        super(new Properties().food(fp).tab(ModSetup.ITEM_GROUP).stacksTo(16));
    }
}
