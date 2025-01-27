package com.example.adventuremod.blocks;

import com.example.adventuremod.items.NewItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class GrapeBlock extends TwoBlockPlant {

    public GrapeBlock() {
        super();
    }

    @Override
    protected Block upperBlock() {
        return NewBlocks.UPPER_GRAPE_BLOCK.get();
    }

    @Override
    protected ItemStack seeds() {
        return new ItemStack(NewItems.GRAPE_SEEDS.get());
    }
}