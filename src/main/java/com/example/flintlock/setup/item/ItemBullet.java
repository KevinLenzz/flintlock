package com.example.flintlock.setup.item;

import com.example.flintlock.setup.ModSetup;
import com.example.flintlock.setup.entity.EntityBullet;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemBullet extends Item{
    public ItemBullet(){
        super(new Item.Properties().tab(ModSetup.ITEM_GROUP).stacksTo(64));
    }
    public EntityBullet createArrow(Level level, ItemStack stack, LivingEntity entityIn,double _damage) {
        EntityBullet arrowentity = new EntityBullet(level, entityIn);
        arrowentity.damage=_damage;
        return arrowentity;
    }
    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.world.entity.player.Player player) {

        int enchant = net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.world.item.enchantment.Enchantments.INFINITY_ARROWS, bow);

        return enchant <= 0 ? false : this.getClass() == ItemBullet.class;

    }
}
