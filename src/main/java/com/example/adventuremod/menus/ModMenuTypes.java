package com.example.adventuremod.menus;

import com.example.adventuremod.AdventureMod;
import com.example.adventuremod.blocks.entities.AlembicEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registry.MENU_REGISTRY, AdventureMod.MODID);

    public static final RegistryObject<MenuType<AlembicMenu>> ALEMBIC_MENU = MENU_TYPES.register("alembic_menu",
            () -> IForgeMenuType.create((id, inventory, buffer) -> {
                BlockPos pos = buffer.readBlockPos(); // Odczytujemy pozycję bloku
                BlockEntity blockEntity = inventory.player.level.getBlockEntity(pos);
                if (blockEntity instanceof AlembicEntity alembicEntity) {
                    return new AlembicMenu(id, inventory, alembicEntity);
                }
                throw new IllegalStateException("Nie udało się otworzyć menu: brak odpowiedniego BlockEntity");
            })
    );

    public static void register() {
        MENU_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
