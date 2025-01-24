package com.example.adventuremod;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(AdventureMod.MODID)
public class AdventureMod {
    public static final String MODID = "adventuremod";

    public static final CreativeModeTab ALCOHOL_TAB = new CreativeModeTab(MODID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(JAGER.get()); // Ikona zakładki
        }
    };

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> JAGER = ITEMS.register("jager",
            () -> new AlcoholItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1) // Punkty jedzenia
                            .saturationMod(0.3f) // Nasycenie
                            .alwaysEat() // Można jeść zawsze
                            .build()
                    )
                    .tab(ALCOHOL_TAB)
            )
    );
    public AdventureMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ITEMS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);

    }
}
