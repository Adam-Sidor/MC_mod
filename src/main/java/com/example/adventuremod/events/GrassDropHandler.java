package com.example.adventuremod.events;

import com.example.adventuremod.AdventureMod;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

import static com.example.adventuremod.items.NewItems.*;

@Mod.EventBusSubscriber(modid = AdventureMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GrassDropHandler {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Level world = event.getPlayer().getLevel();
        BlockPos pos = event.getPos();
        BlockState state = event.getState();
        Random random = event.getPlayer().getRandom();
        if(!event.getPlayer().isCreative()){
            if (state.getBlock() == Blocks.GRASS||state.getBlock() == Blocks.TALL_GRASS) {
                if (!world.isClientSide) {
                    ItemStack hop = new ItemStack(HOP_SEEDS.get());
                    ItemStack grape = new ItemStack(GRAPE_SEEDS.get());
                    ItemStack barleySeeds = new ItemStack(BARLEY_SEEDS.get());
                    if (random.nextInt(10)==0)
                        world.addFreshEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), hop));
                    if (random.nextInt(10)==0)
                        world.addFreshEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), grape));
                    if (random.nextInt(10)==0)
                        world.addFreshEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), barleySeeds));
                }
            }
        }
    }
}