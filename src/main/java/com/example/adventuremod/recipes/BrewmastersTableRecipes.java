package com.example.adventuremod.recipes;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static com.example.adventuremod.items.NewItems.*;

public class BrewmastersTableRecipes {
    private static ItemStack input1;
    private static ItemStack input2;
    private static ItemStack input3;
    private static ItemStack input4;
    static ArrayList<Item> items = new ArrayList<>();

    public static ItemStack getResult(ItemStack input1, ItemStack input2, ItemStack input3,ItemStack input4,ItemStack output) {
        BrewmastersTableRecipes.input1 = input1;
        BrewmastersTableRecipes.input2 = input2;
        BrewmastersTableRecipes.input3 = input3;
        BrewmastersTableRecipes.input4 = input4;
        items.clear();
        items.add(input1.getItem());
        items.add(input2.getItem());
        items.add(input3.getItem());
        items.add(input4.getItem());
        if(isDifferentItems()) {
            System.out.println(items);
            if (isCorrectItems(SPIRIT.get(),Items.WATER_BUCKET)) {
                if((output.getItem()==VODKA.get()||output.getItem()==Items.AIR)&&output.getCount()<4) {
                    return new ItemStack(VODKA.get());
                }
            }else if (isCorrectItems(SPIRIT.get(),Items.WATER_BUCKET,Items.WHEAT_SEEDS)) {
                if((output.getItem()==GIN.get()||output.getItem()==Items.AIR)&&output.getCount()<4) {
                    return new ItemStack(GIN.get());
                }
            }else if (isCorrectItems(SPIRIT.get(),Items.POPPY,Items.WHEAT_SEEDS)) {
                if((output.getItem()==ABSINTHE.get()||output.getItem()==Items.AIR)&&output.getCount()<4) {
                    return new ItemStack(ABSINTHE.get());
                }
            }else if (isCorrectItems(SPIRIT.get(),Items.KELP,Items.WHEAT_SEEDS )) {
                if((output.getItem()==JAGER.get()||output.getItem()==Items.AIR)&&output.getCount()<4) {
                    return new ItemStack(JAGER.get());
                }
            }else if (isCorrectItems(BEER.get(),Items.WHEAT)) {
                if((output.getItem()==BEER_PALE.get()||output.getItem()==Items.AIR)&&output.getCount()<4) {
                    return new ItemStack(BEER_PALE.get());
                }
            }else if (isCorrectItems(BEER.get(),BARLEY_ROASTED.get())) {
                if((output.getItem()==BEER_PORTER.get()||output.getItem()==Items.AIR)&&output.getCount()<4) {
                    return new ItemStack(BEER_PORTER.get());
                }
            }else if (isCorrectItems(WHISKEY.get(),Items.WHEAT)) {
                if((output.getItem()==AMERICAN.get()||output.getItem()==Items.AIR)&&output.getCount()<4) {
                    return new ItemStack(AMERICAN.get());
                }
            }else if (isCorrectItems(WHISKEY.get(),BARLEY_MALT.get())) {
                if((output.getItem()==SCOTCH.get()||output.getItem()==Items.AIR)&&output.getCount()<4) {
                    return new ItemStack(SCOTCH.get());
                }
            }else if (isCorrectItems(WHISKEY.get(),BARLEY.get())) {
                if((output.getItem()==IRISH.get()||output.getItem()==Items.AIR)&&output.getCount()<4) {
                    return new ItemStack(IRISH.get());
                }
            }else if (isCorrectItems(WHISKEY.get(),Items.WHEAT,Items.HONEYCOMB)) {
                if((output.getItem()==AMERICAN_HONEY.get()||output.getItem()==Items.AIR)&&output.getCount()<4) {
                    return new ItemStack(AMERICAN_HONEY.get());
                }
            }else if (isCorrectItems(WHISKEY.get(),Items.WHEAT,Items.APPLE)) {
                if((output.getItem()==AMERICAN_APPLE.get()||output.getItem()==Items.AIR)&&output.getCount()<4) {
                    return new ItemStack(AMERICAN_APPLE.get());
                }
            }else if (isCorrectItems(WHISKEY.get(),Items.WHEAT,Items.COCOA_BEANS)) {
                if((output.getItem()==AMERICAN_CINNAMON.get()||output.getItem()==Items.AIR)&&output.getCount()<4) {
                    return new ItemStack(AMERICAN_CINNAMON.get());
                }
            }else if (isCorrectItems(VINE.get(),Items.MELON_SLICE)) {
                if((output.getItem()==BRANDY.get()||output.getItem()==Items.AIR)&&output.getCount()<4) {
                    return new ItemStack(BRANDY.get());
                }
            }else if (isCorrectItems(VINE.get(),Items.SWEET_BERRIES)) {
                if((output.getItem()==BRANDY.get()||output.getItem()==Items.AIR)&&output.getCount()<4) {
                    return new ItemStack(BRANDY.get());
                }
            }else if (isCorrectItems(VINE.get(),Items.WHEAT_SEEDS)) {
                if((output.getItem()==COGNAC.get()||output.getItem()==Items.AIR)&&output.getCount()<4) {
                    return new ItemStack(COGNAC.get());
                }
            }else if (isCorrectItems(VINE.get(),Items.SUGAR,Items.WHITE_DYE)) {
                if((output.getItem()==WHITE_SWEET.get()||output.getItem()==Items.AIR)&&output.getCount()<4) {
                    return new ItemStack(WHITE_SWEET.get());
                }
            }else if (isCorrectItems(VINE.get(),Items.WHITE_DYE)) {
                if((output.getItem()==WHITE_DRY.get()||output.getItem()==Items.AIR)&&output.getCount()<4) {
                    return new ItemStack(WHITE_DRY.get());
                }
            }else if (isCorrectItems(VINE.get(),Items.SUGAR,Items.RED_DYE)) {
                if((output.getItem()==RED_SWEET.get()||output.getItem()==Items.AIR)&&output.getCount()<4) {
                    return new ItemStack(RED_SWEET.get());
                }
            }else if (isCorrectItems(VINE.get(),Items.RED_DYE)) {
                if((output.getItem()==RED_DRY.get()||output.getItem()==Items.AIR)&&output.getCount()<4) {
                    return new ItemStack(RED_DRY.get());
                }
            }
        }
        return ItemStack.EMPTY;
    }

    private static boolean isDifferentItems() {
        Set<Item> uniqueItems = new HashSet<>();
        int airCount = 0;
        if(input1.getItem()!=Items.AIR) uniqueItems.add(input1.getItem());
        else airCount++;
        if(input2.getItem()!=Items.AIR) uniqueItems.add(input2.getItem());
        else airCount++;
        if(input3.getItem()!=Items.AIR) uniqueItems.add(input3.getItem());
        else airCount++;
        if(input4.getItem()!=Items.AIR) uniqueItems.add(input4.getItem());
        else airCount++;

        return uniqueItems.size()+airCount==4;
    }

    private static boolean isCorrectItems(Item ingredient1, Item ingredient2, Item ingredient3, Item ingredient4) {
        if(!items.contains(ingredient1)) return false;
        if(!items.contains(ingredient2)) return false;
        if(!items.contains(ingredient3)) return false;
        if(!items.contains(ingredient4)) return false;
        return howManyItems(items) == 4;
    }
    private static boolean isCorrectItems(Item ingredient1, Item ingredient2, Item ingredient3) {
        if(!items.contains(ingredient1)) return false;
        if(!items.contains(ingredient2)) return false;
        if(!items.contains(ingredient3)) return false;
        return howManyItems(items) == 3;
    }
    private static boolean isCorrectItems(Item ingredient1, Item ingredient2) {
        if(!items.contains(ingredient1)) return false;
        if(!items.contains(ingredient2)) return false;
        return howManyItems(items) == 2;
    }
    private static boolean isCorrectItems(Item ingredient1) {
        if(!items.contains(ingredient1)) return false;
        return howManyItems(items) == 1;
    }
    private static int howManyItems(ArrayList<Item> items) {
        int count = items.size();
        for(Item item : items) {
            if(item == Items.AIR) count--;
        }
        return count;
    }
}
