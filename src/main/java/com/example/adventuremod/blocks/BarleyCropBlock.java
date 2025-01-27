package com.example.adventuremod.blocks;

import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.Item;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.levelgen.RandomSource;


import java.util.Random;

import static com.example.adventuremod.items.NewItems.BARLEY_SEEDS;


public class BarleyCropBlock extends CropBlock {

    public BarleyCropBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected Item getBaseSeedId() {
        return BARLEY_SEEDS.get();
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random) {
        if (!world.isAreaLoaded(pos, 1)) return; // Optymalizacja
        if (world.getRawBrightness(pos.above(), 0) >= 9) {
            int age = this.getAge(state);
            if (age < this.getMaxAge()) {
                float growthChance = getGrowthSpeed(this, world, pos);
                if (random.nextInt((int) (25.0F / growthChance) + 1) == 0) {
                    world.setBlock(pos, this.getStateForAge(age + 1), 2);
                }
            }
        }
        super.randomTick(state, world, pos, random);
    }
}
