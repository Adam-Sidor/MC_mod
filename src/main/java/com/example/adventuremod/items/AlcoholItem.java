package com.example.adventuremod.items;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.ChatType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import com.example.adventuremod.capabilities.PlayerAlcoholProvider;
import net.minecraft.server.level.ServerPlayer;

public class AlcoholItem extends Item {
    private final int amountOfAlcohol;
    private final ItemStack itemStack;
    public AlcoholItem(Properties properties, int amountOfAlcohol,ItemStack itemStack) {
        super(properties);
        this.amountOfAlcohol = amountOfAlcohol;
        this.itemStack = itemStack;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        player.startUsingItem(hand);

        return InteractionResultHolder.consume(itemStack);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack item, Level level, LivingEntity player) {
        if (!level.isClientSide) {
            if (player instanceof ServerPlayer serverPlayer) {
                player.getCapability(PlayerAlcoholProvider.PLAYER_ALCOHOL).ifPresent(alcohol -> {
                    if (alcohol.addAlcohol(amountOfAlcohol)) {
                        serverPlayer.sendMessage(
                                new TextComponent("Twój poziom alkoholu wzrósł do: " + alcohol.getAlcoholLevel()),
                                ChatType.GAME_INFO,
                                serverPlayer.getUUID()
                        );
                    } else {
                        serverPlayer.kill();
                    }
                });

                // Odtwarzanie dźwięku picia
                level.playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.GENERIC_DRINK, SoundSource.PLAYERS, 1.0F, 1.0F);
                if(!serverPlayer.getInventory().add(itemStack)) {
                    serverPlayer.drop(itemStack, false);
                }
            }
        }

        return super.finishUsingItem(item, level, player);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32; // Czas trwania animacji picia w tickach (20 = 1 sekunda)
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK; // Ustawienie animacji na picie
    }

}
