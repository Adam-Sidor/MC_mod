package com.example.adventuremod.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import com.example.adventuremod.blocks.GrapeBlock; // Importujemy GrapeBlock

public class UpperGrapeBlock extends Block {

    public UpperGrapeBlock() {
        super(BlockBehaviour.Properties.of(Material.PLANT));
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
}
