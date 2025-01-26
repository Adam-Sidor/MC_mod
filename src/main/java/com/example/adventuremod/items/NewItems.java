package com.example.adventuremod.items;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.example.adventuremod.blocks.NewBlocks.ALEMBIC;

public class NewItems {

    public static final CreativeModeTab ALCOHOL_TAB = new CreativeModeTab("alcohol_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(JAGER.get());
        }
    };

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "adventuremod");

    public static final RegistryObject<Item> ALEMBIC_ITEM = ITEMS.register("alembic_item",
            () -> new BlockItem(ALEMBIC.get(), new Item.Properties()
                    .tab(ALCOHOL_TAB)
                    .stacksTo(1)
            )
    );


    public static final RegistryObject<Item> MUG = ITEMS.register("mug",
            () -> new Item(new Item.Properties()
                    .tab(ALCOHOL_TAB)
                    .stacksTo(4)
            )
    );

    public static final RegistryObject<Item> BOTTLE = ITEMS.register("bottle",
            () -> new Item(new Item.Properties()
                    .tab(ALCOHOL_TAB)
                    .stacksTo(16)
            )
    );

    public static final RegistryObject<Item> JAGER = ITEMS.register("jager",
            () -> new AlcoholItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build()
                    )
                    .tab(ALCOHOL_TAB)
                    .stacksTo(2)
                    .rarity(Rarity.EPIC)
                    ,2
                    ,new ItemStack(BOTTLE.get())
            )
    );

    public static final RegistryObject<Item> VODKA = ITEMS.register("vodka",
            () -> new AlcoholItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build()
                    )
                    .tab(ALCOHOL_TAB)
                    .stacksTo(2)
                    .rarity(Rarity.EPIC)
                    ,2
                    ,new ItemStack(BOTTLE.get())
            )
    );

    public static final RegistryObject<Item> BEER = ITEMS.register("beer",
            () -> new AlcoholItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build()
                    )
                    .tab(ALCOHOL_TAB)
                    .stacksTo(4)
                    .rarity(Rarity.UNCOMMON)
                    ,1
                    ,new ItemStack(MUG.get())
            )
    );

    public static final RegistryObject<Item> HOP = ITEMS.register("hop",
            () -> new Item(new Item.Properties()
                    .tab(ALCOHOL_TAB)
            )
    );

    public static final RegistryObject<Item> SPIRIT = ITEMS.register("spirit",
            () -> new AlcoholItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build()
                    )
                    .tab(ALCOHOL_TAB)
                    .stacksTo(16)
                    .rarity(Rarity.UNCOMMON)
                    ,16
                    ,new ItemStack(MUG.get())
            )
    );

    public static final RegistryObject<Item> BARLEY = ITEMS.register("barley",
            () -> new Item(new Item.Properties()
                    .tab(ALCOHOL_TAB)
            )
    );

    public static final RegistryObject<Item> CIDER = ITEMS.register("cider",
            () -> new AlcoholItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build()
                    )
                    .tab(ALCOHOL_TAB)
                    .stacksTo(4)
                    .rarity(Rarity.UNCOMMON)
                    ,1
                    ,new ItemStack(BOTTLE.get())
            )
    );

    public static final RegistryObject<Item> GRAPE = ITEMS.register("grape",
            () -> new Item(new Item.Properties()
                    .tab(ALCOHOL_TAB)
            )
    );

    public static final RegistryObject<Item> RUM = ITEMS.register("rum",
            () -> new AlcoholItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build()
                    )
                    .tab(ALCOHOL_TAB)
                    .stacksTo(1)
                    .rarity(Rarity.UNCOMMON)
                    ,1
                    ,new ItemStack(BOTTLE.get())
            )
    );

    public static final RegistryObject<Item> TEQUILA = ITEMS.register("tequila",
            () -> new AlcoholItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build()
                    )
                    .tab(ALCOHOL_TAB)
                    .stacksTo(1)
                    .rarity(Rarity.UNCOMMON)
                    ,1
                    ,new ItemStack(BOTTLE.get())
            )
    );

    public static final RegistryObject<Item> WHISKEY = ITEMS.register("whiskey",
            () -> new AlcoholItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build()
                    )
                    .tab(ALCOHOL_TAB)
                    .stacksTo(16)
                    .rarity(Rarity.UNCOMMON)
                    ,4
                    ,new ItemStack(BOTTLE.get())
            )
    );

    public static final RegistryObject<Item> VINE = ITEMS.register("vine",
            () -> new AlcoholItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build()
                    )
                    .tab(ALCOHOL_TAB)
                    .stacksTo(16)
                    .rarity(Rarity.UNCOMMON)
                    ,4
                    ,new ItemStack(BOTTLE.get())
            )
    );

    public static final RegistryObject<Item> MEAD = ITEMS.register("mead",
            () -> new AlcoholItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build()
                    )
                    .tab(ALCOHOL_TAB)
                    .stacksTo(4)
                    .rarity(Rarity.UNCOMMON)
                    ,1
                    ,new ItemStack(BOTTLE.get())
            )
    );

}
