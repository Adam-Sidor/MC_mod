package com.example.adventuremod;

import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import static net.minecraft.world.effect.MobEffects.*;

public class AlcoholItem extends Item {
    public AlcoholItem(Properties properties) {
        super(properties);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            serverPlayer.addEffect(new MobEffectInstance(CONFUSION, 200));
        }

        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
