package com.example.adventuremod.events;

import com.example.adventuremod.AdventureMod;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

import static com.example.adventuremod.blocks.NewBlocks.BARLEY_CROP;
import static com.example.adventuremod.items.NewItems.*;

@Mod.EventBusSubscriber(modid = AdventureMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BarleyDropHandler {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Level world = event.getPlayer().getLevel();
        BlockPos pos = event.getPos();
        BlockState state = event.getState();
        Random random = event.getPlayer().getRandom();

        if (!event.getPlayer().isCreative()) {
            if (!world.isClientSide) {
                if (state.getBlock() == BARLEY_CROP.get()){
                    ItemStack barleySeeds = new ItemStack(BARLEY_SEEDS.get());
                    world.addFreshEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, barleySeeds));
                    if(state.getValue(CropBlock.AGE) == 7){
                        ItemStack barley = new ItemStack(BARLEY.get());
                        System.out.println(barley);
                        world.addFreshEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, barley));
                        for(int i=0; i<=random.nextInt(4);i++){
                            world.addFreshEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, barleySeeds));
                        }
                    }
                }
            }
        }
    }
}
