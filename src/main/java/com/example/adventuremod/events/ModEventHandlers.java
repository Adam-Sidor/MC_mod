package com.example.adventuremod.events;

import com.example.adventuremod.AdventureMod;
import com.example.adventuremod.capabilities.PlayerAlcoholProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AdventureMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEventHandlers {
    @SubscribeEvent
    public static void attachPlayerCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(new ResourceLocation(AdventureMod.MODID, "alcohol"), new PlayerAlcoholProvider());
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath()) { // Przenoszenie danych po śmierci gracza
            event.getOriginal().getCapability(PlayerAlcoholProvider.PLAYER_ALCOHOL).ifPresent(oldCap -> {
                event.getEntity().getCapability(PlayerAlcoholProvider.PLAYER_ALCOHOL).ifPresent(newCap -> {
                    newCap.setAlcoholLevel(oldCap.getAlcoholLevel());
                });
            });
        }
    }
}
