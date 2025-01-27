package com.example.adventuremod.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public class GrapeBlock extends Block {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;  // Fazy od 0 do 3

    public GrapeBlock() {
        super(BlockBehaviour.Properties.of(Material.PLANT));
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));  // Domyślnie roślina zaczyna w fazie 0
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);  // Rejestrujemy stan "AGE" w definicji bloku
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
                    BlockState upperState = NewBlocks.UPPER_GRAPE_BLOCK.get().defaultBlockState();
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

            // Losowy tick - roślina rośnie, jeśli nie osiągnęła fazy 3
            if (age < 3) {
                level.setBlock(pos, state.setValue(AGE, age + 1), 2);
                age = state.getValue(AGE);
            }

            // Jeśli roślina osiągnęła fazę 3, stawiamy górną część
            if (age == 3) {
                BlockPos upperPos = pos.above();  // Pozycja nad dolną rośliną
                BlockState upperState = NewBlocks.UPPER_GRAPE_BLOCK.get().defaultBlockState();
                level.setBlock(upperPos, upperState, 3);
            }
        }
        super.randomTick(state, level, pos, p_60554_);
    }

    @Override
    public boolean isCollisionShapeFullBlock(BlockState p_181242_, BlockGetter p_181243_, BlockPos p_181244_) {
        return false;
    }

}
