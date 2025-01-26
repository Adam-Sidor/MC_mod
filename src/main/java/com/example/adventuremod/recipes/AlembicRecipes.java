package com.example.adventuremod.recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static com.example.adventuremod.items.NewItems.BOTTLE;
import static com.example.adventuremod.items.NewItems.JAGER;

public class AlembicRecipes {
    public static ItemStack getResult(ItemStack input1, ItemStack input2, ItemStack input3,ItemStack output) {
        if(input1.getItem()!=input2.getItem()&&input2.getItem()!=input3.getItem()&&input3.getItem()!=input1.getItem()) {
            if (input1.getItem() == Items.GOLD_INGOT && input2.getItem() == Items.DIAMOND && input3.getItem() == Items.EMERALD) {
                if((output.getItem()==Items.NETHERITE_SCRAP||output.getItem()==Items.AIR)&&output.getCount()<64) {
                    return new ItemStack(Items.NETHERITE_SCRAP,output.getCount()+1);
                }
            } else if(input1.getItem() == BOTTLE.get()&&(input2.getItem()==Items.KELP||input3.getItem()==Items.KELP)) {
                if((output.getItem()==JAGER.get()||output.getItem()==Items.AIR)&&output.getCount()<2) {
                    return new ItemStack(JAGER.get(),output.getCount()+1);
                }
            }
        }
        return ItemStack.EMPTY;
    }
}
