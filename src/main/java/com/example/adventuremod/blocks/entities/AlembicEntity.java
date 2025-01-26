package com.example.adventuremod.blocks.entities;

import com.example.adventuremod.menus.AlembicMenu;
import com.example.adventuremod.recipes.AlembicRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

public class AlembicEntity extends BlockEntity implements net.minecraft.world.MenuProvider {
    private int waterLevel;
    private int fuelLevel;
    private final int fuelMaxTime = 16*20;
    private int fuelTime;
    private boolean canFuelStart;
    private boolean canProduce;
    private int timeLeft;
    ItemStack waitingItem;
    private final ItemStackHandler itemHandler = new ItemStackHandler(6);

    public AlembicEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALEMBIC.get(), pos, state);
        this.waterLevel = 0;
        fuelLevel = 0;
        fuelTime = 0;
        canProduce = true;
        timeLeft = 40;
        canFuelStart = false;
    }

    public ContainerData getData() {
        return new ContainerData() {
            @Override
            public int get(int index) {
                switch (index){
                case 0:
                    return waterLevel;
                case 1:
                    return fuelLevel;
                case 2:
                    return timeLeft;
                }
                return 0;
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        waterLevel = value; // Zaktualizowanie wartości waterLevel
                        break;
                    case 1:
                        fuelLevel = value;
                        break;
                    case 2:
                        timeLeft = value;
                        break;
                }
            }

            @Override
            public int getCount() {
                return 3; // Tylko 1 wartość do synchronizacji
            }
        };
    }

    public void tick() {
        waitUntilFinished();
        if(canProduce)
            process();
        fuelManagement();
    }
    // Metoda przetwarzająca składniki
    public void process() {
        waterManagement();
        ItemStack input1 = itemHandler.getStackInSlot(2);  // Składnik 1
        ItemStack input2 = itemHandler.getStackInSlot(3);  // Składnik 2
        ItemStack input3 = itemHandler.getStackInSlot(4);  // Składnik 3
        if ((!input1.isEmpty() || !input2.isEmpty() || !input3.isEmpty())&&waterLevel>0) {
            ItemStack result = AlembicRecipes.getResult(input1, input2, input3,itemHandler.getStackInSlot(5));
            if(!result.isEmpty()){
                canFuelStart = true;
                if (fuelLevel>0) {
                    input1.shrink(1);
                    input2.shrink(1);
                    input3.shrink(1);
                    waterLevel--;
                    setVariablesToWait(result);
                }
            }
            else{
                canFuelStart = false;
            }
        }
        else{
            canFuelStart = false;
        }
    }


    private void waitUntilFinished() {
        if(!canProduce){
            timeLeft--;
        }
        if (timeLeft <= 0) {
            canProduce = true;
            timeLeft = 40;
            ItemStack currentOutput = itemHandler.getStackInSlot(5);
            ItemStack output = new ItemStack(waitingItem.getItem(),currentOutput.getCount()+waitingItem.getCount());
            itemHandler.setStackInSlot(5, output);
        }
    }

    private void setVariablesToWait(ItemStack result){
        canProduce = false;
        timeLeft = 40;
        waitingItem = result;
    }

    private void waterManagement(){
        ItemStack waterSlot = itemHandler.getStackInSlot(0);
        if(waterSlot.getItem() == Items.WATER_BUCKET && waterLevel<=0){
            waterLevel = 4;
            waterSlot.shrink(1);
            itemHandler.setStackInSlot(0, new ItemStack(Items.BUCKET));
        }
    }

    private void fuelManagement(){
        if(fuelLevel>0){
            fuelTime++;
            if(fuelTime>fuelMaxTime){
                fuelLevel--;
                fuelTime = 0;
            }
        }
        ItemStack fuelSlot = itemHandler.getStackInSlot(1);
        if(fuelSlot.getItem() == Items.COAL && fuelLevel<=0 && canFuelStart){
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
