package com.example.adventuremod.recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static com.example.adventuremod.items.NewItems.*;

public class AlembicRecipes {
    public static ItemStack getResult(ItemStack input1, ItemStack input2, ItemStack input3,ItemStack output) {
        if(input1.getItem()!=input2.getItem()&&input2.getItem()!=input3.getItem()&&input3.getItem()!=input1.getItem()) {
            if (input1.getItem() == MUG.get() && (input2.getItem() == HOP.get() || input3.getItem() == HOP.get())) {
                if((output.getItem()==BEER.get()||output.getItem()==Items.AIR)&&output.getCount()<4) {
                    return new ItemStack(BEER.get());
                }
            } else if(input1.getItem() == BOTTLE.get()&&(input2.getItem()==Items.KELP||input3.getItem()==Items.KELP)) {
                if((output.getItem()==JAGER.get()||output.getItem()==Items.AIR)&&output.getCount()<2) {
                    return new ItemStack(JAGER.get());
                }
            }
        }
        return ItemStack.EMPTY;
    }
}
