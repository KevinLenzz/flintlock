package com.example.flintlock.setup.network;

import com.example.flintlock.Flintlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class Messages {

    private static SimpleChannel INSTANCE;

    // Every packet needs a unique ID (unique for this channel)
    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        // Make the channel. If needed you can do version checking here
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(Flintlock.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        // Register all our packets. We only have one right now. The new message has a unique ID, an indication
        // of how it is going to be used (from client to server) and ways to encode and decode it. Finally 'handle'
        // will actually execute when the packet is received
        net.messageBuilder(PGM2.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PGM2::new)
                .encoder(PGM2::toBytes)
                .consumer(PGM2::handle)
                .add();
        net.messageBuilder(PGM.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PGM::new)
                .encoder(PGM::toBytes)
                .consumer(PGM::handle)
                .add();
        net.messageBuilder(PGM3.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PGM3::new)
                .encoder(PGM3::toBytes)
                .consumer(PGM3::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}