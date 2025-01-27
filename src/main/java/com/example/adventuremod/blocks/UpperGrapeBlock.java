package com.example.adventuremod.blocks;

import com.example.adventuremod.items.NewItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public class UpperGrapeBlock extends Block {
    public static final BooleanProperty HAS_FRUIT = BooleanProperty.create("has_fruit");

    public UpperGrapeBlock() {
        super(BlockBehaviour.Properties.of(Material.PLANT));
        this.registerDefaultState(this.stateDefinition.any().setValue(HAS_FRUIT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HAS_FRUIT);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockState belowState = world.getBlockState(pos.below());

        if (belowState.is(NewBlocks.GRAPE_BLOCK.get())) {
            int age = belowState.getValue(GrapeBlock.AGE);
            return age == 3;
        }

        return false;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (player.getItemInHand(hand).getItem() == Items.BONE_MEAL) {
            if (!level.isClientSide) {
                boolean hasFruit = state.getValue(HAS_FRUIT);

                if (!hasFruit) {
                    level.setBlock(pos, state.setValue(HAS_FRUIT, true), 3);
                    if (!player.isCreative()) {
                        player.getItemInHand(hand).shrink(1);
                    }
                }
            }

            return InteractionResult.SUCCESS;
        }else{
            if (!level.isClientSide) {
                boolean hasFruit = state.getValue(HAS_FRUIT);

                if (hasFruit) {
                    level.setBlock(pos, state.setValue(HAS_FRUIT, false), 3);
                    ItemStack fruit = new ItemStack(NewItems.GRAPE.get());
                    player.getInventory().add(fruit);
                }
            }

            return InteractionResult.SUCCESS;
        }
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random p_60554_) {
        if (!level.isClientSide) {
            boolean hasFruit = state.getValue(HAS_FRUIT);

            if (!hasFruit) {
                level.setBlock(pos, state.setValue(HAS_FRUIT, true), 3);
            }
        }
        super.randomTick(state, level, pos, p_60554_);
    }

    @Override
    public boolean isCollisionShapeFullBlock(BlockState p_181242_, BlockGetter p_181243_, BlockPos p_181244_) {
        return false;
    }

}
