package com.example.adventuremod.blocks;

import com.example.adventuremod.AdventureMod;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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

    public static final RegistryObject<Block> BARLEY_CROP = BLOCKS.register("barley_crop",
            () -> new BarleyCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));


    public static final RegistryObject<Block> GRAPE_BLOCK = BLOCKS.register("grape_block", () -> new GrapeBlock());
    public static final RegistryObject<Block> UPPER_GRAPE_BLOCK = BLOCKS.register("upper_grape_block", () -> new UpperGrapeBlock());



    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }

}
