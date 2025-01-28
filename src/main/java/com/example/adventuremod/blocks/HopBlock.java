package com.example.adventuremod.blocks;

import com.example.adventuremod.items.NewItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class HopBlock extends TwoBlockPlant {

    public HopBlock() {
        super();
    }

    @Override
    protected Block upperBlock() {
        return NewBlocks.UPPER_HOP_BLOCK.get();
    }

    @Override
    protected ItemStack seeds() {
        return new ItemStack(NewItems.HOP_SEEDS.get());
    }
}