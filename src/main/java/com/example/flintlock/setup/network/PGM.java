package com.example.flintlock.setup.network;

import com.example.flintlock.setup.event.Flags2;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.network.NetworkEvent;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.function.Supplier;

public class PGM {
    public static final String MESSAGE_NO_MANA = "message.nomana";
    ItemStack item;

    public PGM(ItemStack item) {
        this.item=item;
    }

    public PGM(FriendlyByteBuf buf) {
        item = buf.readItem();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeItem(this.item);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            // Here we are client side
//            Flags2.shoot=true;
            System.out.println(item);
            Items.CROSSBOW=item.getItem();
        });
        ctx.setPacketHandled(true);
        return true;
    }
}
