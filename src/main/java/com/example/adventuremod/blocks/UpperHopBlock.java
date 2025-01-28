package com.example.adventuremod.blocks;

import com.example.adventuremod.items.NewItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public class UpperHopBlock extends UpperTwoBlockPlant {

    public static final BooleanProperty IS_GROWN = BooleanProperty.create("is_grown");

    public UpperHopBlock() {
        super();
        this.registerDefaultState(this.stateDefinition.any().setValue(IS_GROWN, false));
        this.registerDefaultState(this.stateDefinition.any().setValue(HAS_FRUIT, false));
    }

    @Override
    public Block lowerBlock() {
        return NewBlocks.HOP_BLOCK.get();
    }

    @Override
    public ItemStack seeds() {
        return new ItemStack(NewItems.HOP_SEEDS.get());
    }

    @Override
    public ItemStack fruit() {
        return new ItemStack(NewItems.HOP.get());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HAS_FRUIT, IS_GROWN);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        level.scheduleTick(pos, this, 1);
    }

    @Override
    public void tick(BlockState state, ServerLevel serverLevel, BlockPos pos, Random random) {
        serverLevel.scheduleTick(pos, this, 1);
        BlockState belowState = serverLevel.getBlockState(pos.below());
        if (belowState.is(lowerBlock())) {
            int age = belowState.getValue(TwoBlockPlant.AGE);
            if (age == 3) {
                serverLevel.setBlock(pos, state.setValue(IS_GROWN, true), 3);
            } else {
                serverLevel.setBlock(pos, state.setValue(IS_GROWN, false), 2);
            }
        }
        super.tick(state, serverLevel, pos, random);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if(state.getValue(IS_GROWN)) {
            return super.use(state, level, pos, player, hand, hit);
        }
        return InteractionResult.PASS;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random p_60554_) {
        if(state.getValue(IS_GROWN)) {
            super.randomTick(state, level, pos, p_60554_);
        }
    }
}