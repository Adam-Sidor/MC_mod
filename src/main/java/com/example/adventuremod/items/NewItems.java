package com.example.adventuremod.items;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.example.adventuremod.blocks.NewBlocks.*;

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

    public static final RegistryObject<Item> COPPER_CAN = ITEMS.register("copper_can",
            () -> new Item(new Item.Properties()
                    .tab(ALCOHOL_TAB)
                    .stacksTo(4)
            )
    );

    public static final RegistryObject<Item> COPPER_PIPE = ITEMS.register("copper_pipe",
            () -> new Item(new Item.Properties()
                    .tab(ALCOHOL_TAB)
            )
    );

    public static final RegistryObject<Item> COPPER_SPIRAL = ITEMS.register("copper_spiral",
            () -> new Item(new Item.Properties()
                    .tab(ALCOHOL_TAB)
                    .stacksTo(4)
            )
    );

    public static final RegistryObject<Item> COPPER_RADIATOR = ITEMS.register("copper_radiator",
            () -> new Item(new Item.Properties()
                    .tab(ALCOHOL_TAB)
                    .stacksTo(4)
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

    public static final RegistryObject<Item> BEER_PALE = ITEMS.register("beer_pale",
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

    public static final RegistryObject<Item> BEER_PORTER = ITEMS.register("beer_porter",
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

    public static final RegistryObject<Item> HOP_SEEDS = ITEMS.register("hop_seeds",
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

    public static final RegistryObject<Item> BARLEY_SEEDS = ITEMS.register("barley_seeds",
            () -> new ItemNameBlockItem(BARLEY_CROP.get(),
                    new Item.Properties()
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

    public static final RegistryObject<Item> GRAPE_SEEDS = ITEMS.register("grape_seeds",
            () -> new CustomSeedsItem(new Item.Properties()
                    .tab(ALCOHOL_TAB)
                    ,GRAPE_BLOCK.get()
                    , Blocks.GRASS_BLOCK
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
    public static final RegistryObject<Item> AMERICAN = ITEMS.register("american",
            () -> new AlcoholItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build()
                    )
                    .tab(ALCOHOL_TAB)
                    .stacksTo(4)
                    .rarity(Rarity.EPIC)
                    ,1
                    ,new ItemStack(BOTTLE.get())
            )
    );

    public static final RegistryObject<Item> AMERICAN_CINNAMON = ITEMS.register("american_cinnamon",
            () -> new AlcoholItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build()
                    )
                    .tab(ALCOHOL_TAB)
                    .stacksTo(4)
                    .rarity(Rarity.EPIC)
                    ,1
                    ,new ItemStack(BOTTLE.get())
            )
    );

    public static final RegistryObject<Item> AMERICAN_APPLE = ITEMS.register("american_apple",
            () -> new AlcoholItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build()
                    )
                    .tab(ALCOHOL_TAB)
                    .stacksTo(4)
                    .rarity(Rarity.EPIC)
                    ,1
                    ,new ItemStack(BOTTLE.get())
            )
    );

    public static final RegistryObject<Item> AMERICAN_HONEY = ITEMS.register("american_honey",
            () -> new AlcoholItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build()
                    )
                    .tab(ALCOHOL_TAB)
                    .stacksTo(4)
                    .rarity(Rarity.EPIC)
                    ,1
                    ,new ItemStack(BOTTLE.get())
            )
    );

    public static final RegistryObject<Item> SCOTCH = ITEMS.register("scotch",
            () -> new AlcoholItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build()
                    )
                    .tab(ALCOHOL_TAB)
                    .stacksTo(4)
                    .rarity(Rarity.EPIC)
                    ,1
                    ,new ItemStack(BOTTLE.get())
            )
    );

    public static final RegistryObject<Item> IRISH = ITEMS.register("irish",
            () -> new AlcoholItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build()
                    )
                    .tab(ALCOHOL_TAB)
                    .stacksTo(4)
                    .rarity(Rarity.EPIC)
                    ,1
                    ,new ItemStack(BOTTLE.get())
            )
    );

    public static final RegistryObject<Item> BRANDY = ITEMS.register("brandy",
            () -> new AlcoholItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build()
                    )
                    .tab(ALCOHOL_TAB)
                    .stacksTo(4)
                    .rarity(Rarity.EPIC)
                    ,1
                    ,new ItemStack(BOTTLE.get())
            )
    );

    public static final RegistryObject<Item> COGNAC = ITEMS.register("cognac",
            () -> new AlcoholItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build()
                    )
                    .tab(ALCOHOL_TAB)
                    .stacksTo(4)
                    .rarity(Rarity.EPIC)
                    ,1
                    ,new ItemStack(BOTTLE.get())
            )
    );

    public static final RegistryObject<Item> WHITE_SWEET = ITEMS.register("white_sweet",
            () -> new AlcoholItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build()
                    )
                    .tab(ALCOHOL_TAB)
                    .stacksTo(4)
                    .rarity(Rarity.EPIC)
                    ,1
                    ,new ItemStack(BOTTLE.get())
            )
    );

    public static final RegistryObject<Item> WHITE_DRY = ITEMS.register("white_dry",
            () -> new AlcoholItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build()
                    )
                    .tab(ALCOHOL_TAB)
                    .stacksTo(4)
                    .rarity(Rarity.EPIC)
                    ,1
                    ,new ItemStack(BOTTLE.get())
            )
    );

    public static final RegistryObject<Item> RED_SWEET = ITEMS.register("red_sweet",
            () -> new AlcoholItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build()
                    )
                    .tab(ALCOHOL_TAB)
                    .stacksTo(4)
                    .rarity(Rarity.EPIC)
                    ,1
                    ,new ItemStack(BOTTLE.get())
            )
    );

    public static final RegistryObject<Item> RED_DRY = ITEMS.register("red_dry",
            () -> new AlcoholItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build()
                    )
                    .tab(ALCOHOL_TAB)
                    .stacksTo(4)
                    .rarity(Rarity.EPIC)
                    ,1
                    ,new ItemStack(BOTTLE.get())
            )
    );

    public static final RegistryObject<Item> ABSINTHE = ITEMS.register("absinthe",
            () -> new AlcoholItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build()
                    )
                    .tab(ALCOHOL_TAB)
                    .stacksTo(4)
                    .rarity(Rarity.EPIC)
                    ,1
                    ,new ItemStack(BOTTLE.get())
            )
    );

    public static final RegistryObject<Item> GIN = ITEMS.register("gin",
            () -> new AlcoholItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0.3f)
                            .alwaysEat()
                            .build()
                    )
                    .tab(ALCOHOL_TAB)
                    .stacksTo(4)
                    .rarity(Rarity.EPIC)
                    ,1
                    ,new ItemStack(BOTTLE.get())
            )
    );

    public static final RegistryObject<Item> BARLEY_ROASTED = ITEMS.register("barley_roasted",
            () -> new Item(new Item.Properties()
                    .tab(ALCOHOL_TAB)
            )
    );

    public static final RegistryObject<Item> BARLEY_MALT = ITEMS.register("barley_malt",
            () -> new Item(new Item.Properties()
                    .tab(ALCOHOL_TAB)
            )
    );
}
