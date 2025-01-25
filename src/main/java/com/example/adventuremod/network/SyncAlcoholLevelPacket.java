package com.example.adventuremod.network;

import com.example.adventuremod.capabilities.PlayerAlcoholProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncAlcoholLevelPacket {
    private final int alcoholLevel;

    public SyncAlcoholLevelPacket(int alcoholLevel) {
        this.alcoholLevel = alcoholLevel;
    }

    public static void encode(SyncAlcoholLevelPacket msg, FriendlyByteBuf buffer) {
        buffer.writeInt(msg.alcoholLevel);
    }

    public static SyncAlcoholLevelPacket decode(FriendlyByteBuf buffer) {
        return new SyncAlcoholLevelPacket(buffer.readInt());
    }

    public static void handle(SyncAlcoholLevelPacket msg, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        if (context.getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            context.enqueueWork(() -> {
                Minecraft.getInstance().player.getCapability(PlayerAlcoholProvider.PLAYER_ALCOHOL).ifPresent(alcohol -> {
                    alcohol.setAlcoholLevel(msg.alcoholLevel);
                });
            });
        }
        context.setPacketHandled(true);
    }
}
