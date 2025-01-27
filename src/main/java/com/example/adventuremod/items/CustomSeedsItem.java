package com.example.adventuremod.items;


import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;

import net.minecraft.world.item.Item;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraft.world.item.context.BlockPlaceContext;

public class CustomSeedsItem extends BlockItem {

    private Block plant;
    private Block bed;
    public CustomSeedsItem(Properties properties, Block plant,Block bed) {
        super(plant, new Item.Properties());
        this.plant=plant;
        this.bed=bed;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos pos = context.getClickedPos();
        BlockState state = context.getLevel().getBlockState(pos);

        // Sprawdzamy, czy kliknięto na Grass Block
        if (state.is(bed)) {
            BlockState plantState = plant.defaultBlockState();
            context.getLevel().setBlock(pos.above(), plantState, 3);
            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }
}
