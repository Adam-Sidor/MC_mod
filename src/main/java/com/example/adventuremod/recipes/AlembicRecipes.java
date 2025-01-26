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
            }else if(input1.getItem() == BOTTLE.get()&&(input2.getItem()==Items.SUGAR_CANE||input3.getItem()==Items.SUGAR_CANE)) {
                if((output.getItem()==RUM.get()||output.getItem()==Items.AIR)&&output.getCount()<1) {
                    return new ItemStack(RUM.get());
                }
            }else if(input1.getItem() == BOTTLE.get()&&(input2.getItem()==Items.APPLE||input3.getItem()==Items.APPLE)) {
                if((output.getItem()==CIDER.get()||output.getItem()==Items.AIR)&&output.getCount()<4) {
                    return new ItemStack(CIDER.get());
                }
            }else if(input1.getItem() == BOTTLE.get()&&(input2.getItem()==Items.CACTUS||input3.getItem()==Items.CACTUS)) {
                if((output.getItem()==TEQUILA.get()||output.getItem()==Items.AIR)&&output.getCount()<1) {
                    return new ItemStack(TEQUILA.get());
                }
            }else if(input1.getItem() == BOTTLE.get()&&(input2.getItem()==Items.HONEYCOMB||input3.getItem()==Items.HONEYCOMB)) {
                if((output.getItem()==MEAD.get()||output.getItem()==Items.AIR)&&output.getCount()<4) {
                    return new ItemStack(MEAD.get());
                }
            }else if(input1.getItem() == BOTTLE.get()&&(input2.getItem()==GRAPE.get()||input3.getItem()==GRAPE.get())) {
                if((output.getItem()==VINE.get()||output.getItem()==Items.AIR)&&output.getCount()<16) {
                    return new ItemStack(VINE.get());
                }
            }else if(input1.getItem() == BOTTLE.get()&&(input2.getItem()==Items.WHEAT||input3.getItem()==Items.WHEAT||input2.getItem()==Items.POTATO||input3.getItem()==Items.POTATO)) {
                if((output.getItem()==SPIRIT.get()||output.getItem()==Items.AIR)&&output.getCount()<16) {
                    return new ItemStack(SPIRIT.get());
                }
            }else if(input1.getItem() == BOTTLE.get()&&(input2.getItem()==BARLEY.get()||input3.getItem()==BARLEY.get())) {
                if((output.getItem()==WHISKEY.get()||output.getItem()==Items.AIR)&&output.getCount()<16) {
                    return new ItemStack(WHISKEY.get());
                }
            }
        }
        return ItemStack.EMPTY;
    }
}
