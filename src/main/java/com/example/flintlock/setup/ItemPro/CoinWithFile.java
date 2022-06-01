package com.example.flintlock.setup.ItemPro;

import com.example.flintlock.setup.ModSetup;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Material;

public class CoinWithFile extends Item {
    public CoinWithFile(){
        super(new Item.Properties().tab(ModSetup.ITEM_GROUP).stacksTo(64));
    }
}
