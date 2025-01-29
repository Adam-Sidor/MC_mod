package com.example.adventuremod.events;

import com.example.adventuremod.AdventureMod;
import com.example.adventuremod.capabilities.PlayerAlcoholProvider;
import com.example.adventuremod.network.ModPackets;
import com.example.adventuremod.network.SyncAlcoholLevelPacket;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkDirection;
import static net.minecraft.world.effect.MobEffects.*;

@Mod.EventBusSubscriber(modid = AdventureMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TickHandler {

    private static final int TICKS_IN_A_MINUTE = 600; //30 sekund
    private static int tickCounter = 0;

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && !event.player.level.isClientSide) {
            Player player = event.player;
            tickCounter++;
            if(player.isInWater()||player.isInPowderSnow){
                tickCounter++;
            }
            if (tickCounter >= TICKS_IN_A_MINUTE) {
                tickCounter = 0;
                reduceAlcoholLevel((ServerPlayer) player);
            }
            setAlcoholEffects((ServerPlayer) player);
            syncAlcoholLevel((ServerPlayer) player);
        }
    }

    private static void setAlcoholEffects(ServerPlayer serverPlayer){
        MobEffectInstance confusion = serverPlayer.getEffect(CONFUSION);
        if (confusion != null) {
            System.out.println(confusion.getDuration());
            if(confusion.getDuration() < 100){
                applyAlcoholEffect(serverPlayer);
            }
        }else {
            applyAlcoholEffect(serverPlayer);
        }
    }

    private static void applyAlcoholEffect(ServerPlayer serverPlayer){
        serverPlayer.getCapability(PlayerAlcoholProvider.PLAYER_ALCOHOL).ifPresent(alcohol -> {
            switch (alcohol.getAlcoholLevel()){
                case 1,2,3,4,5:
                    serverPlayer.addEffect(new MobEffectInstance(CONFUSION,200,1,false,true,false));
                    break;
                case 6,7,8,9,10:
                    serverPlayer.addEffect(new MobEffectInstance(CONFUSION,200,3,false,true,false));
                    serverPlayer.addEffect(new MobEffectInstance(MOVEMENT_SLOWDOWN,200,3,false,true,false));
                    break;
                case 11,12,13,14,15:
                    serverPlayer.addEffect(new MobEffectInstance(CONFUSION,200,5,false,true,false));
                    serverPlayer.addEffect(new MobEffectInstance(MOVEMENT_SLOWDOWN,200,5,false,true,false));
                    break;
                case 16,17:
                    serverPlayer.addEffect(new MobEffectInstance(CONFUSION,200,10,false,true,false));
                    serverPlayer.addEffect(new MobEffectInstance(MOVEMENT_SLOWDOWN,200,5,false,true,false));
                    break;
                case 18,19,20:
                    serverPlayer.addEffect(new MobEffectInstance(CONFUSION,200,15,false,true,false));
                    serverPlayer.addEffect(new MobEffectInstance(BLINDNESS,200,15,false,true,false));
                    serverPlayer.addEffect(new MobEffectInstance(MOVEMENT_SLOWDOWN,200,5,false,true,false));
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

    private static void syncAlcoholLevel(ServerPlayer serverPlayer) {
        serverPlayer.getCapability(PlayerAlcoholProvider.PLAYER_ALCOHOL).ifPresent(alcohol -> {
            ModPackets.INSTANCE.sendTo(
                    new SyncAlcoholLevelPacket(alcohol.getAlcoholLevel()),
                    ((ServerPlayer) serverPlayer).connection.connection,
                    NetworkDirection.PLAY_TO_CLIENT
            );
        });
    }
}
