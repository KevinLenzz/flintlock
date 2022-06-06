package com.example.flintlock.setup.network;

import com.example.flintlock.setup.event.Flags;
import com.example.flintlock.setup.event.Flags2;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PGM2 {
    public static final String MESSAGE_NO_MANA = "message.nomana";

    public PGM2() {
    }

    public PGM2(FriendlyByteBuf buf) {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            // Here we are server side
            Flags2.shoot=true;
        });
        ctx.setPacketHandled(true);
        return true;
    }
}
