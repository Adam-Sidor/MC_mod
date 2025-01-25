package com.example.adventuremod.items;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.ChatType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import com.example.adventuremod.capabilities.PlayerAlcoholProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;

import static net.minecraft.world.effect.MobEffects.CONFUSION;

public class AlcoholItem extends Item {
    public AlcoholItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.addEffect(new MobEffectInstance(CONFUSION, 200));

                player.getCapability(PlayerAlcoholProvider.PLAYER_ALCOHOL).ifPresent(alcohol -> {
                    if(alcohol.addAlcohol(1)){
                        serverPlayer.sendMessage(
                                new TextComponent("Twój poziom alkoholu wzrósł do: " + alcohol.getAlcoholLevel()),
                                ChatType.GAME_INFO,
                                serverPlayer.getUUID()
                        );
                        System.out.println("Twój poziom alkoholu wzrósł do: " + alcohol.getAlcoholLevel());
                    }
                });
            }
        }

        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
