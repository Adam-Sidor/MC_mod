package com.example.adventuremod.events;

import com.example.adventuremod.capabilities.PlayerAlcoholProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerEventHandler {
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerAlcoholProvider.PLAYER_ALCOHOL).ifPresent(oldCap -> {
                event.getPlayer().getCapability(PlayerAlcoholProvider.PLAYER_ALCOHOL).ifPresent(newCap -> {
                    newCap.setAlcoholLevel(oldCap.getAlcoholLevel());
                });
            });
        }
    }
}
