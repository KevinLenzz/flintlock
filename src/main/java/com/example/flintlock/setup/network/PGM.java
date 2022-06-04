package com.example.flintlock.setup.network;

import com.example.flintlock.setup.ItemPro.FlintlockItem;
import com.example.flintlock.setup.ItemPro.ItemBullet;
import com.example.flintlock.setup.Registration;
import com.example.flintlock.setup.entity.EntityBullet;
import com.example.flintlock.setup.event.Flags;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import static com.example.flintlock.setup.event.ForgeEventBusEvent.*;

public class PGM {
    public static final String MESSAGE_NO_MANA = "message.nomana";
    public ItemStack itemstack_send;
    public ItemStack itemstackorigin_send;
    public Level level_send;
    public LivingEntity livingEntity_send;
    public double damageTag_send;
    public float f_send;

    public PGM(ItemStack is1,ItemStack is2,Level level_,LivingEntity livingEntity_,double damageTag_,float f_) {
        itemstack_send=is1;
        itemstackorigin_send=is2;
        level_send=level_;
        livingEntity_send=livingEntity_;
        damageTag_send=damageTag_;
        f_send=f_;
    }

    public PGM(FriendlyByteBuf buf) {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            // Here we are server side
            ItemBullet arrowitem = (ItemBullet) (itemstack_send.getItem() instanceof ItemBullet ? itemstack_send.getItem() : Registration.ITEM_BULLET.get());
            EntityBullet abstractarrowentity;
            abstractarrowentity = arrowitem.createArrow(level_send, itemstack_send, livingEntity_send,damageTag_send);
            abstractarrowentity.shootFromRotation(livingEntity_send, livingEntity_send.getXRot(), livingEntity_send.getYRot(), 0.2F, f_send * 30.0F, 0.75F);
            itemstackorigin_send.hurtAndBreak(1, livingEntity_send, (p_220009_1_) -> {p_220009_1_.broadcastBreakEvent(livingEntity_send.getUsedItemHand());});
            level_send.addFreshEntity(abstractarrowentity);
        });
        ctx.setPacketHandled(true);
        return true;
    }
}
