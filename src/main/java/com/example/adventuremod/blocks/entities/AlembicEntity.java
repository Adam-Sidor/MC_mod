package com.example.adventuremod.blocks.entities;

import com.example.adventuremod.menus.AlembicMenu;
import com.example.adventuremod.recipes.AlembicRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

public class AlembicEntity extends BlockEntity implements net.minecraft.world.MenuProvider {
    private int waterLevel;
    private int fuelLevel;

    private boolean canProduce;
    private int timeLeft;
    ItemStack waitingItem;
    private final ItemStackHandler itemHandler = new ItemStackHandler(6);  // 6 slotów: Woda, Paliwo, 3 składniki, Wynik

    public AlembicEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALEMBIC.get(), pos, state);
        waterLevel = 0;
        fuelLevel = 0;
        canProduce = true;
        timeLeft = 1;
    }
    public void tick() {
        waitUntilFinished();
        if(canProduce)
            process();
    }
    // Metoda przetwarzająca składniki
    public void process() {
        waterManagement();
        fuelManagement();
        //System.out.println(waterLevel+" "+fuelLevel);
        ItemStack fuelSlot = itemHandler.getStackInSlot(1);   // Slot na paliwo
        if ( waterLevel>0 && fuelLevel>0) {  // Jeżeli mamy wodę i paliwo
            ItemStack input1 = itemHandler.getStackInSlot(2);  // Składnik 1
            ItemStack input2 = itemHandler.getStackInSlot(3);  // Składnik 2
            ItemStack input3 = itemHandler.getStackInSlot(4);  // Składnik 3
            if (!input1.isEmpty() || !input2.isEmpty() || !input3.isEmpty()) {
                ItemStack result = AlembicRecipes.getResult(input1, input2, input3,itemHandler.getStackInSlot(5));

                if (!result.isEmpty()) {
                    // Zużywamy składniki
                    input1.shrink(1);
                    input2.shrink(1);
                    input3.shrink(1);
                    waterLevel--;
                    fuelLevel--;
                    // Ustawiamy nowy wynik w odpowiednim slocie
                    setVariablesToWait(result);
                }
            }
        }
    }

    private void waitUntilFinished() {
        if(!canProduce){
            timeLeft--;
        }
        if (timeLeft <= 0) {
            canProduce = true;
            itemHandler.setStackInSlot(5, waitingItem);
        }
    }

    private void setVariablesToWait(ItemStack result){
        canProduce = false;
        timeLeft = 40;
        waitingItem = result;
    }

    private void waterManagement(){
        ItemStack waterSlot = itemHandler.getStackInSlot(0);
        if(waterSlot.getItem() == Items.WATER_BUCKET && waterLevel==0){
            waterLevel = 4;
            waterSlot.shrink(1);
            itemHandler.setStackInSlot(0, new ItemStack(Items.BUCKET));  // Zostawiamy puste wiaderko w slocie
        }
    }

    private void fuelManagement(){
        ItemStack fuelSlot = itemHandler.getStackInSlot(1);
        if(fuelSlot.getItem() == Items.COAL && fuelLevel==0){
            fuelLevel = 8;
            fuelSlot.shrink(1);
        }
    }

    // MenuProvider - nazwa GUI wyświetlana na ekranie
    @Override
    public Component getDisplayName() {
        return new TextComponent("Alembic");
    }

    // MenuProvider - metoda tworząca instancję menu
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
        return new AlembicMenu(id, playerInventory, this);
    }

    // Getter dla ItemHandler
    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }

}
