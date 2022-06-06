package com.example.flintlock.setup.network;

import com.example.flintlock.setup.event.Flags2;
import com.example.flintlock.setup.item.FlintlockItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PGM3 {
    public static final String MESSAGE_NO_MANA = "message.nomana";

    ItemStack item;

    public PGM3(ItemStack item) {
        this.item=item;
    }

    public PGM3(FriendlyByteBuf buf) {
        item = buf.readItem();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeItem(this.item);
    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            // Here we are server side
            // Flags2.lock=true;
        });
        ctx.setPacketHandled(true);
        return true;
    }
}
