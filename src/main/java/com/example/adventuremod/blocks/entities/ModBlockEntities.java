package com.example.adventuremod.blocks.entities;

import com.example.adventuremod.AdventureMod;
import com.example.adventuremod.blocks.NewBlocks;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ModBlockEntities {
    // Tworzymy DeferredRegister dla BlockEntity
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registry.BLOCK_ENTITY_TYPE_REGISTRY, AdventureMod.MODID);

    // Rejestracja BlockEntity dla Alembica
    public static final RegistryObject<BlockEntityType<AlembicEntity>> ALEMBIC = BLOCK_ENTITIES.register("alembic",
            () -> BlockEntityType.Builder.of(AlembicEntity::new, NewBlocks.ALEMBIC.get()).build(null));

    // Funkcja rejestrująca BlockEntity
    public static void register() {

        // Teraz rejestrujemy BlockEntity
        BLOCK_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
