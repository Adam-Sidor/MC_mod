package com.example.adventuremod.commands;

import com.example.adventuremod.AdventureMod;
import com.example.adventuremod.capabilities.PlayerAlcoholProvider;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent; // Import TextComponent dla starszych wersji API
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AdventureMod.MODID)
public class ModCommands {
    @SubscribeEvent
    public static void onServerStarting(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("alcohol_level").executes(context -> {
            ServerPlayer player = context.getSource().getPlayerOrException();
            player.getCapability(PlayerAlcoholProvider.PLAYER_ALCOHOL).ifPresent(alcohol -> {
                context.getSource().sendSuccess(new TextComponent("Twój poziom alkoholu to: " + alcohol.getAlcoholLevel()), false);
            });
            return 1;
        }));
    }
}
