package com.example.adventuremod.recipes;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static com.example.adventuremod.items.NewItems.*;

public class AlembicRecipes {
    public static ItemStack getResult(ItemStack input1, ItemStack input2, ItemStack input3,ItemStack output) {
        if(input1.getItem()!=input2.getItem()&&input2.getItem()!=input3.getItem()&&input3.getItem()!=input1.getItem()) {
            if (input1.getItem() == MUG.get() &&  isCorrectExtras(input2,input3,HOP.get())){
                if((output.getItem()==BEER.get()||output.getItem()==Items.AIR)&&output.getCount()<4) {
                    return new ItemStack(BEER.get());
                }
            } else if(input1.getItem() == BOTTLE.get()&&isCorrectExtras(input2,input3,Items.KELP)) {
                if((output.getItem()==JAGER.get()||output.getItem()==Items.AIR)&&output.getCount()<2) {
                    return new ItemStack(JAGER.get());
                }
            }else if(input1.getItem() == BOTTLE.get()&&isCorrectExtras(input2,input3,Items.SUGAR_CANE)) {
                if((output.getItem()==RUM.get()||output.getItem()==Items.AIR)&&output.getCount()<1) {
                    return new ItemStack(RUM.get());
                }
            }else if(input1.getItem() == BOTTLE.get()&&isCorrectExtras(input2,input3,Items.APPLE)) {
                if((output.getItem()==CIDER.get()||output.getItem()==Items.AIR)&&output.getCount()<4) {
                    return new ItemStack(CIDER.get());
                }
            }else if(input1.getItem() == BOTTLE.get()&&isCorrectExtras(input2,input3,Items.CACTUS)) {
                if((output.getItem()==TEQUILA.get()||output.getItem()==Items.AIR)&&output.getCount()<1) {
                    return new ItemStack(TEQUILA.get());
                }
            }else if(input1.getItem() == BOTTLE.get()&&isCorrectExtras(input2,input3,Items.HONEYCOMB)) {
                if((output.getItem()==MEAD.get()||output.getItem()==Items.AIR)&&output.getCount()<4) {
                    return new ItemStack(MEAD.get());
                }
            }else if(input1.getItem() == BOTTLE.get()&&isCorrectExtras(input2,input3,GRAPE.get())) {
                if((output.getItem()==VINE.get()||output.getItem()==Items.AIR)&&output.getCount()<16) {
                    return new ItemStack(VINE.get());
                }
            }else if(input1.getItem() == BOTTLE.get()&&(isCorrectExtras(input2,input3,Items.WHEAT)||isCorrectExtras(input2,input3,Items.POTATO))) {
                if((output.getItem()==SPIRIT.get()||output.getItem()==Items.AIR)&&output.getCount()<16) {
                    return new ItemStack(SPIRIT.get());
                }
            }else if(input1.getItem() == BOTTLE.get()&&isCorrectExtras(input2,input3,BARLEY.get())) {
                if((output.getItem()==WHISKEY.get()||output.getItem()==Items.AIR)&&output.getCount()<16) {
                    return new ItemStack(WHISKEY.get());
                }
            }
        }
        return ItemStack.EMPTY;
    }

    private static boolean isCorrectExtras(ItemStack input2, ItemStack input3, Item correctItem) {
        return (input2.getItem() == correctItem && input3.getItem() == Items.AIR) || (input3.getItem() == correctItem && input2.getItem() == Items.AIR);
    }
}
