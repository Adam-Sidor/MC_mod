package com.example.adventuremod.blocks.entities;

import com.example.adventuremod.AdventureMod;
import com.example.adventuremod.blocks.NewBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, AdventureMod.MODID);

    public static final RegistryObject<BlockEntityType<AlembicEntity>> ALEMBIC = BLOCK_ENTITIES.register(
            "alembic_entity",
            () -> BlockEntityType.Builder.of(AlembicEntity::new, NewBlocks.ALEMBIC.get()).build(null)
    );

    public static final RegistryObject<BlockEntityType<BrewmastersTableEntity>> BREWMASTERS_TABLE_ENTITY = BLOCK_ENTITIES.register(
            "brewmasters_table_entity",
            () -> BlockEntityType.Builder.of(BrewmastersTableEntity::new, NewBlocks.BREWMASTERS_TABLE.get()).build(null)
    );

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}