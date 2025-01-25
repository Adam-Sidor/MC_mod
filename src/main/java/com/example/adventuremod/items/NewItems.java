package com.example.adventuremod.items;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NewItems {

    public static final CreativeModeTab ALCOHOL_TAB = new CreativeModeTab("alcohol_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(JAGER.get());
        }
    };

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "adventuremod");

    public static final RegistryObject<Item> JAGER = ITEMS.register("jager",
            () -> new AlcoholItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build()
                    )
                    .tab(ALCOHOL_TAB)
                    .stacksTo(4)
            )
    );
}
