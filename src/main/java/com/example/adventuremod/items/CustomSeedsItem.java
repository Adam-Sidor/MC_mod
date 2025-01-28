package com.example.adventuremod.items;


import com.example.adventuremod.blocks.NewBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Objects;


public class CustomSeedsItem extends Item {

    private Block plant;
    private Block bed;
    public CustomSeedsItem(Properties properties, Block plant,Block bed) {
        super(properties);
        this.plant=plant;
        this.bed=bed;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos pos = context.getClickedPos();
        BlockState state = context.getLevel().getBlockState(pos);

        if (state.is(bed)) {
            BlockState plantState = plant.defaultBlockState();
            context.getLevel().setBlock(pos.above(), plantState, 3);
            if(plant == NewBlocks.HOP_BLOCK.get()){
                BlockState upperPlantState = NewBlocks.UPPER_HOP_BLOCK.get().defaultBlockState();
                context.getLevel().setBlock(pos.above().above(), upperPlantState, 3);
            }
            if (!Objects.requireNonNull(context.getPlayer()).isCreative()) {
                context.getItemInHand().shrink(1);
            }
            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }
}
