package com.example.adventuremod.recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class AlembicRecipes {
    public static ItemStack getResult(ItemStack input1, ItemStack input2, ItemStack input3,ItemStack output) {
        // Przykład: Złoto + Diament + Szmaragd = Netherite Scrap
        if (input1.getItem() == Items.GOLD_INGOT && input2.getItem() == Items.DIAMOND && input3.getItem() == Items.EMERALD) {
            if((output.getItem()==Items.NETHERITE_SCRAP||output.getItem()==Items.AIR)&&output.getCount()<64) {
                return new ItemStack(Items.NETHERITE_SCRAP,output.getCount()+1);
            }
        }
        return ItemStack.EMPTY;
    }
}
