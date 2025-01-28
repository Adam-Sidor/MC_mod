package com.example.adventuremod.blocks;

import com.example.adventuremod.items.NewItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class UpperHopBlock extends UpperTwoBlockPlant {

    public UpperHopBlock() {
        super();
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

}