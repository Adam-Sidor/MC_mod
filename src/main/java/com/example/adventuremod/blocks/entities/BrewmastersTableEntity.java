package com.example.adventuremod.blocks.entities;

import com.example.adventuremod.blocks.BrewmastersTable;
import com.example.adventuremod.menus.BrewmastersTableMenu;
import com.example.adventuremod.recipes.AlembicRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
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

public class BrewmastersTableEntity extends BlockEntity implements net.minecraft.world.MenuProvider {
    private int waterLevel;
    private int fuelLevel;
    private boolean canProduce;
    private int timeLeft;
    private ItemStack waitingItem;
    private final ItemStackHandler itemHandler = new ItemStackHandler(5);
    private Direction facing;
    private final BlockPos pos;

    public BrewmastersTableEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BREWMASTERS_TABLE_ENTITY.get(), pos, state);
        this.waterLevel = 0;
        this.fuelLevel = 0;
        this.canProduce = true;
        this.timeLeft = 40;
        this.pos = pos;
        this.facing = getFacing();
    }

    public ContainerData getData() {
        return new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> waterLevel;
                    case 1 -> fuelLevel;
                    case 2 -> timeLeft;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> waterLevel = value;
                    case 1 -> fuelLevel = value;
                    case 2 -> timeLeft = value;
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

        if ((!input1.isEmpty() || !input2.isEmpty() || !input3.isEmpty()) && waterLevel > 0) {
            ItemStack result = AlembicRecipes.getResult(input1, input2, input3, itemHandler.getStackInSlot(5));
            if (!result.isEmpty()) {
                if (fuelLevel > 0) {
                    input1.shrink(1);
                    input2.shrink(1);
                    input3.shrink(1);
                    waterLevel--;
                    setVariablesToWait(result);
                }
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
            ItemStack currentOutput = itemHandler.getStackInSlot(4);
            ItemStack output = new ItemStack(waitingItem.getItem(), currentOutput.getCount() + waitingItem.getCount());
            itemHandler.setStackInSlot(4, output);
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
