package com.example.adventuremod.recipes;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static com.example.adventuremod.items.NewItems.*;

public class BrewmastersTableRecipes {
    public static ItemStack getResult(ItemStack input1, ItemStack input2, ItemStack input3,ItemStack output) {
        if(input1.getItem()!=input2.getItem()&&input2.getItem()!=input3.getItem()&&input3.getItem()!=input1.getItem()) {
            if (input1.getItem() == SPIRIT.get() &&  isCorrectExtras(input2,input3,Items.WATER_BUCKET)) {
                if((output.getItem()==VODKA.get()||output.getItem()==Items.AIR)&&output.getCount()<4) {
                    return new ItemStack(VODKA.get());
                }
            }
        }
        return ItemStack.EMPTY;
    }

    private static boolean isCorrectExtras(ItemStack input2, ItemStack input3, Item correctItem) {
        return (input2.getItem() == correctItem && input3.getItem() == Items.AIR) || (input3.getItem() == correctItem && input2.getItem() == Items.AIR);
    }
}
