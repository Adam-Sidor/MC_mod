package com.example.adventuremod.blocks.entities;

import com.example.adventuremod.blocks.AlembicBlock;
import com.example.adventuremod.blocks.BrewmastersTable;
import com.example.adventuremod.menus.BrewmastersTableMenu;
import com.example.adventuremod.recipes.BrewmastersTableRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import org.jetbrains.annotations.NotNull;

import static com.example.adventuremod.blocks.BrewmastersTable.HAS_WATER;

public class BrewmastersTableEntity extends BlockEntity implements net.minecraft.world.MenuProvider {
    private int waterLevel;
    private boolean canProduce;
    private int timeLeft;
    private ItemStack waitingItem;
    private final ItemStackHandler itemHandler = new ItemStackHandler(6);

    public BrewmastersTableEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BREWMASTERS_TABLE_ENTITY.get(), pos, state);
        this.waterLevel = 0;
        this.canProduce = true;
        this.timeLeft = 40;
    }

    public ContainerData getData() {
        return new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> waterLevel;
                    case 1 -> timeLeft;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> waterLevel = value;
                    case 1 -> timeLeft = value;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        };
    }

    public void tick() {
        waitUntilFinished();
        if (canProduce) {
            process();
        }
    }

    public void process() {
        waterManagement();
        ItemStack input1 = itemHandler.getStackInSlot(1);
        ItemStack input2 = itemHandler.getStackInSlot(2);
        ItemStack input3 = itemHandler.getStackInSlot(3);
        ItemStack input4 = itemHandler.getStackInSlot(4);
        if ((!input1.isEmpty() || !input2.isEmpty() || !input3.isEmpty() || !input4.isEmpty()) && waterLevel > 0) {
            ItemStack result = BrewmastersTableRecipes.getResult(input1, input2, input3,input4, itemHandler.getStackInSlot(5));
            boolean returnBucket=false;
            if (!result.isEmpty()) {
                if(input1.getItem() == Items.WATER_BUCKET||input2.getItem() == Items.WATER_BUCKET||input3.getItem() == Items.WATER_BUCKET||input4.getItem() == Items.WATER_BUCKET)
                    returnBucket=true;
                input1.shrink(1);
                input2.shrink(1);
                input3.shrink(1);
                input4.shrink(1);
                if(returnBucket)
                    if(input1.isEmpty())
                        itemHandler.setStackInSlot(1, new ItemStack(Items.BUCKET));
                    else if(input2.isEmpty())
                        itemHandler.setStackInSlot(2, new ItemStack(Items.BUCKET));
                    else if(input3.isEmpty())
                        itemHandler.setStackInSlot(3, new ItemStack(Items.BUCKET));
                    else if(input4.isEmpty())
                        itemHandler.setStackInSlot(4, new ItemStack(Items.BUCKET));
                waterLevel--;
                setVariablesToWait(result);
            }
        }
    }

    private void waitUntilFinished() {
        if (!canProduce) {
            timeLeft--;
        }
        if (timeLeft <= 0) {
            canProduce = true;
            timeLeft = 40;
            ItemStack currentOutput = itemHandler.getStackInSlot(5);
            ItemStack output = new ItemStack(waitingItem.getItem(), currentOutput.getCount() + waitingItem.getCount());
            itemHandler.setStackInSlot(5, output);
        }
    }

    private void setVariablesToWait(ItemStack result) {
        canProduce = false;
        timeLeft = 40;
        waitingItem = result;
    }

    private void waterManagement() {
        ItemStack waterSlot = itemHandler.getStackInSlot(0);
        if (waterSlot.getItem() == Items.WATER_BUCKET && waterLevel <= 0) {
            waterLevel = 4;
            waterSlot.shrink(1);
            itemHandler.setStackInSlot(0, new ItemStack(Items.BUCKET));
        }
        if(waterLevel>0)
            level.setBlock(worldPosition, getBlockState().setValue(HAS_WATER, true), 3);
        else
            level.setBlock(worldPosition, getBlockState().setValue(HAS_WATER, false), 3);
    }

    public Direction getFacing() {
        return this.getBlockState().getValue(BrewmastersTable.FACING);
    }


    @Override
    public @NotNull Component getDisplayName() {
        return new TextComponent("Brewmaster's Table");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
        return new BrewmastersTableMenu(id, playerInventory, this);
    }

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }
}
