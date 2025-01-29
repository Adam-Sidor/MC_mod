package com.example.adventuremod.blocks;

import com.example.adventuremod.capabilities.PlayerAlcoholProvider;
import com.example.adventuremod.items.NewItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.BlockHitResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShotGlassBlock extends Block {
    public static final IntegerProperty FILL = IntegerProperty.create("fill",0,4);

    public ShotGlassBlock(BlockBehaviour.Properties properties) {
        super(properties.randomTicks().strength(0.5F, 1.0F));
        this.registerDefaultState(this.stateDefinition.any().setValue(FILL, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FILL);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            ArrayList<Item> alcohols = new ArrayList<>();
            alcohols.add(NewItems.VODKA.get());

            int fillLevel = state.getValue(FILL);

            if (hand != InteractionHand.MAIN_HAND) {
                return InteractionResult.PASS;
            }

            ItemStack holdItem = player.getItemInHand(hand);

            if (alcohols.contains(holdItem.getItem())) {
                if (fillLevel < 4) {
                    level.setBlock(pos, state.setValue(FILL, 4), 2);
                    if (!player.isCreative()) {
                        holdItem.shrink(1);
                    }
                }
                return InteractionResult.SUCCESS;
            } else if (holdItem.isEmpty()) {
                if (fillLevel > 0) {
                    System.out.println("Pijemy");
                    level.setBlock(pos, state.setValue(FILL, fillLevel - 1), 2);
                    player.getCapability(PlayerAlcoholProvider.PLAYER_ALCOHOL).ifPresent(alcohol -> {
                        if (!alcohol.addAlcohol(4)) {
                            player.kill();
                        }
                    });
                }
                return InteractionResult.SUCCESS;
            }
        }
        return super.use(state, level, pos, player, hand, hit);
    }


    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
        level.scheduleTick(pos, this, 5);
        if(level.getBlockState(pos.below()).is(Blocks.AIR)) {
            level.destroyBlock(pos, false);
        }
        super.tick(state, level, pos, random);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> drops = super.getDrops(state, builder);
        if (drops.isEmpty()) {
            drops.add(new ItemStack(this));
        }
        return drops;
    }
}
