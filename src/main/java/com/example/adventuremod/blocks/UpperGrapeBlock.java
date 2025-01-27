package com.example.adventuremod.blocks;

import com.example.adventuremod.items.NewItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class UpperGrapeBlock extends UpperTwoBlockPlant {

    public UpperGrapeBlock() {
        super();
    }

    @Override
    public Block lowerBlock() {
        return NewBlocks.GRAPE_BLOCK.get();
    }

    @Override
    public ItemStack seeds() {
        return new ItemStack(NewItems.GRAPE_SEEDS.get());
    }

    @Override
    public ItemStack fruit() {
        return new ItemStack(NewItems.GRAPE.get());
    }

}