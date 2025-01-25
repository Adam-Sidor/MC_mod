package com.example.adventuremod.blocks;

import com.example.adventuremod.AdventureMod;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.example.adventuremod.blocks.entities.ModBlockEntities.BLOCK_ENTITIES;

public class NewBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registry.BLOCK_REGISTRY, AdventureMod.MODID);

    public static final RegistryObject<Block> ALEMBIC = BLOCKS.register("alembic",
            () -> new AlembicBlock(BlockBehaviour.Properties.of(Material.METAL))
    );

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        //BLOCK_ENTITIES.register(modEventBus);
    }

}