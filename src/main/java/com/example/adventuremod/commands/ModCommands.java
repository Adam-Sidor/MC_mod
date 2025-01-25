package com.example.adventuremod.commands;

import com.example.adventuremod.AdventureMod;
import com.example.adventuremod.capabilities.PlayerAlcoholProvider;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.ChatType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AdventureMod.MODID)
public class ModCommands {

    @SubscribeEvent
    public static void onServerStarting(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("alcohol")
                .then(Commands.literal("show").executes(context -> {
                    ServerPlayer player = context.getSource().getPlayerOrException();
                    player.getCapability(PlayerAlcoholProvider.PLAYER_ALCOHOL).ifPresent(alcohol -> {
                        context.getSource().sendSuccess(new TextComponent("Twój poziom alkoholu to: " + alcohol.getAlcoholLevel()), false);
                    });
                    return 1;
                }))
                .then(Commands.literal("add")
                        .then(Commands.argument("amount", IntegerArgumentType.integer(1))  // Argument do dodawania
                                .executes(context -> {
                                    int amount = IntegerArgumentType.getInteger(context, "amount");
                                    ServerPlayer player = context.getSource().getPlayerOrException();
                                    player.getCapability(PlayerAlcoholProvider.PLAYER_ALCOHOL).ifPresent(alcohol -> {
                                        if(alcohol.addAlcohol(amount))
                                            context.getSource().sendSuccess(new TextComponent("Zwiększono poziom alkoholu. Twój poziom alkoholu to: " + alcohol.getAlcoholLevel()), false);
                                        else
                                            context.getSource().sendSuccess(new TextComponent("Nie udało się zwiększyć poziomu alkoholu. Twój poziom alkoholu to: " + alcohol.getAlcoholLevel()), false);
                                    });
                                    return 1;
                                })
                        ))
                .then(Commands.literal("reduce")
                        .then(Commands.argument("amount", IntegerArgumentType.integer(1))
                                .executes(context -> {
                                    int amount = IntegerArgumentType.getInteger(context, "amount");
                                    ServerPlayer player = context.getSource().getPlayerOrException();
                                    player.getCapability(PlayerAlcoholProvider.PLAYER_ALCOHOL).ifPresent(alcohol -> {
                                        if(alcohol.reduceAlcohol(amount))
                                            context.getSource().sendSuccess(new TextComponent("Zmniejszono poziom alkoholu. Twój poziom alkoholu to: " + alcohol.getAlcoholLevel()), false);
                                        else
                                            context.getSource().sendSuccess(new TextComponent("Nie udało się zmiejszyć poziomu alkoholu. Twój poziom alkoholu to: " + alcohol.getAlcoholLevel()), false);
                                    });
                                    return 1;
                                })
                        ))
                .then(Commands.literal("set")
                        .then(Commands.argument("value", IntegerArgumentType.integer(0,20))
                                .executes(context -> {
                                    int value = IntegerArgumentType.getInteger(context, "value");
                                    ServerPlayer player = context.getSource().getPlayerOrException();
                                    player.getCapability(PlayerAlcoholProvider.PLAYER_ALCOHOL).ifPresent(alcohol -> {
                                        if(alcohol.setAlcoholLevel(value))
                                            context.getSource().sendSuccess(new TextComponent("Zmieniono poziom alkoholu. Twój poziom alkoholu to: " + alcohol.getAlcoholLevel()), false);
                                        else
                                            context.getSource().sendSuccess(new TextComponent("Nie udało się zmienic poziomu alkoholu. Twój poziom alkoholu to: " + alcohol.getAlcoholLevel()), false);
                                    });
                                    return 1;
                                })
                        ))
        );
    }
}
