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
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public abstract class UpperTwoBlockPlant extends Block {
    public static final BooleanProperty HAS_FRUIT = BooleanProperty.create("has_fruit");
    public abstract Block lowerBlock();
    public abstract ItemStack seeds();
    public abstract ItemStack fruit();

    public UpperTwoBlockPlant() {
        super(BlockBehaviour.Properties.copy(Blocks.WHEAT));
        this.registerDefaultState(this.stateDefinition.any().setValue(HAS_FRUIT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HAS_FRUIT);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockState belowState = world.getBlockState(pos.below());

        if (belowState.is(lowerBlock())) {
            int age = belowState.getValue(TwoBlockPlant.AGE);
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
                else{
                    level.setBlock(pos, state.setValue(HAS_FRUIT, false), 3);
                    player.getInventory().add(fruit());
                }
            }

            return InteractionResult.SUCCESS;
        }else{
            if (!level.isClientSide) {
                boolean hasFruit = state.getValue(HAS_FRUIT);
                if (hasFruit) {
                    level.setBlock(pos, state.setValue(HAS_FRUIT, false), 3);
                    player.getInventory().add(fruit());
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
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        super.playerWillDestroy(level, pos, state, player);

        BlockPos belowPos = pos.below();
        BlockState belowState = level.getBlockState(belowPos);
        if (belowState.is(lowerBlock())) {
            level.destroyBlock(belowPos, false);
            Random rand = new Random();
            for (int i = 0; i < rand.nextInt(4); i++) {
                level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), seeds()));
            }
        }
    }

}
