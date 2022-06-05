package com.example.flintlock.setup.item;

import com.example.flintlock.setup.ModSetup;
import net.minecraft.world.item.Item;

public class CoinWithFile extends Item {
    public CoinWithFile(){
        super(new Item.Properties().tab(ModSetup.ITEM_GROUP).stacksTo(64));
    }
}
