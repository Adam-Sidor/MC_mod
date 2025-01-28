package com.example.adventuremod.blocks;

import com.example.adventuremod.items.NewItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public abstract class TwoBlockPlant extends Block {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;  // Fazy od 0 do 3
    protected abstract Block upperBlock();
    protected abstract ItemStack seeds();

    public TwoBlockPlant() {
        super(BlockBehaviour.Properties.copy(Blocks.WHEAT));
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockState belowState = world.getBlockState(pos.below());
        return belowState.is(Blocks.GRASS_BLOCK);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (player.getItemInHand(hand).getItem() == Items.BONE_MEAL) {
            int age = state.getValue(AGE);

            if (age < 3) {
                level.setBlock(pos, state.setValue(AGE, age + 1), 2);
                if (!player.isCreative()) {
                    player.getItemInHand(hand).shrink(1);
                }

                if (age == 2) {
                    BlockPos upperPos = pos.above();
                    BlockState upperState = upperBlock().defaultBlockState();
                    level.setBlock(upperPos, upperState, 3);
                }
            }

            return InteractionResult.SUCCESS;
        }

        return super.use(state, level, pos, player, hand, hit);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random p_60554_) {
        if (!level.isClientSide) {
            int age = state.getValue(AGE);


            if (age < 3) {
                level.setBlock(pos, state.setValue(AGE, age + 1), 2);

                if (age == 2) {
                    BlockPos upperPos = pos.above();
                    BlockState upperState = upperBlock().defaultBlockState();
                    level.setBlock(upperPos, upperState, 3);
                }
            }

        }
        super.randomTick(state, level, pos, p_60554_);
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        super.playerWillDestroy(level, pos, state, player);
        Random rand = new Random();
        int seedsCount = 1;
        if(state.getValue(AGE)==3)
            seedsCount = rand.nextInt(4);
        for (int i = 0; i < seedsCount; i++) {
            level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), seeds()));
        }
        BlockPos upperPos = pos.above();
        BlockState upperState = level.getBlockState(upperPos);
        if (upperState.is(upperBlock())) {
            level.destroyBlock(upperPos, false);
        }
    }
}
