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

public class NewBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registry.BLOCK_REGISTRY, AdventureMod.MODID);

    public static final RegistryObject<Block> ALEMBIC = BLOCKS.register("alembic",
            () -> new AlembicBlock(BlockBehaviour.Properties.of(Material.METAL))
    );

    public static final RegistryObject<Block> BREWMASTERS_TABLE = BLOCKS.register("brewmasters_table",
            () -> new BrewmastersTable(BlockBehaviour.Properties.of(Material.WOOD))
    );

    public static final RegistryObject<Block> BARLEY_CROP = BLOCKS.register("barley_crop",
            () -> new BarleyCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));


    public static final RegistryObject<Block> GRAPE_BLOCK = BLOCKS.register("grape_block", () -> new GrapeBlock());
    public static final RegistryObject<Block> UPPER_GRAPE_BLOCK = BLOCKS.register("upper_grape_block", () -> new UpperGrapeBlock());

    public static final RegistryObject<Block> HOP_BLOCK = BLOCKS.register("hop_block", () -> new HopBlock());
    public static final RegistryObject<Block> UPPER_HOP_BLOCK = BLOCKS.register("upper_hop_block", () -> new UpperHopBlock());

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }

}
