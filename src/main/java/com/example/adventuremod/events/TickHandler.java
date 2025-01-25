package com.example.adventuremod.events;

import com.example.adventuremod.AdventureMod;
import com.example.adventuremod.capabilities.PlayerAlcoholProvider;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AdventureMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TickHandler {

    private static final int TICKS_IN_A_MINUTE = 600;  // 1 minuta = 1200 ticków
    private static int tickCounter = 0;

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && !event.player.level.isClientSide) {
            Player player = event.player;
            tickCounter++;
            if(player.isInWater()){
                tickCounter++;
            }
            if (tickCounter >= TICKS_IN_A_MINUTE) {
                tickCounter = 0;
                reduceAlcoholLevel((ServerPlayer) player);
            }
        }
    }

    private static void reduceAlcoholLevel(ServerPlayer serverPlayer) {
        serverPlayer.getCapability(PlayerAlcoholProvider.PLAYER_ALCOHOL).ifPresent(alcohol -> {
            if(alcohol.getAlcoholLevel()>0){
                alcohol.reduceAlcohol(2);

                serverPlayer.sendMessage(
                        new TextComponent("Twój poziom alkoholu zmalał do: " + alcohol.getAlcoholLevel()),
                        ChatType.GAME_INFO,
                        serverPlayer.getUUID()
                );
                if(alcohol.getAlcoholLevel()<0){
                    alcohol.setAlcoholLevel(0);
                }
            }
        });
    }
}
