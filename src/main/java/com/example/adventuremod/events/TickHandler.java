package com.example.adventuremod.events;

import com.example.adventuremod.AdventureMod;
import com.example.adventuremod.capabilities.PlayerAlcoholProvider;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraft.world.effect.MobEffects.*;

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
            setAlcoholEffects((ServerPlayer) player);
        }
    }

    private static void setAlcoholEffects(ServerPlayer serverPlayer){
        serverPlayer.getCapability(PlayerAlcoholProvider.PLAYER_ALCOHOL).ifPresent(alcohol -> {
            switch (alcohol.getAlcoholLevel()){
                case 1,2,3,4,5:
                    serverPlayer.addEffect(new MobEffectInstance(CONFUSION,2,0,false,true,false));
                    break;
                case 6,7,8,9,10:
                    serverPlayer.addEffect(new MobEffectInstance(CONFUSION,2,1,false,true,false));
                    serverPlayer.addEffect(new MobEffectInstance(MOVEMENT_SLOWDOWN,2,1,false,true,false));
                    break;
                case 18,19,20:
                    serverPlayer.addEffect(new MobEffectInstance(CONFUSION,2,2,false,true,false));
                    serverPlayer.addEffect(new MobEffectInstance(BLINDNESS,5,2,false,true,false));
                    break;
            }
        });
    }

    private static void reduceAlcoholLevel(ServerPlayer serverPlayer) {
        serverPlayer.getCapability(PlayerAlcoholProvider.PLAYER_ALCOHOL).ifPresent(alcohol -> {
            if(alcohol.reduceAlcohol(2)){
                serverPlayer.sendMessage(
                        new TextComponent("Twój poziom alkoholu zmalał do: " + alcohol.getAlcoholLevel()),
                        ChatType.GAME_INFO,
                        serverPlayer.getUUID()
                );
            }
        });
    }
}
