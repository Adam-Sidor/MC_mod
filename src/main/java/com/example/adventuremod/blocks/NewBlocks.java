package com.example.adventuremod.blocks;

import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class NewBlocks {
    // Tworzymy DeferredRegister dla bloków
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registry.BLOCK_REGISTRY, "adventuremod");

    // Rejestracja Alembica
    public static final RegistryObject<Block> ALEMBIC = BLOCKS.register("alembic",
            () -> new AlembicBlock(BlockBehaviour.Properties.of(Material.METAL)));

    // Funkcja rejestrująca bloki
    /* static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }*/
}
