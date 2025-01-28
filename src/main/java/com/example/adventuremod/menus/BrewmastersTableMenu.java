package com.example.adventuremod.menus;

import com.example.adventuremod.blocks.entities.BrewmastersTableEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.SlotItemHandler;

import static com.example.adventuremod.items.NewItems.*;

public class BrewmastersTableMenu extends AbstractContainerMenu {
    private final BrewmastersTableEntity blockEntity;
    private final ContainerData data;
    public int waterLevel;
    public int timeLeft;
    public BrewmastersTableMenu(int id, Inventory playerInventory, BrewmastersTableEntity blockEntity) {
        super(ModMenuTypes.BREWMASTERS_TABLE_MENU.get(), id);
        this.blockEntity = blockEntity;
        this.data = blockEntity.getData();
        this.waterLevel = data.get(0);
        this.timeLeft = data.get(1);

        // Dodanie slotów bloku
        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 0, 20, 17) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() == Items.WATER_BUCKET;
            }
        });

        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 1, 56, 17) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() == SPIRIT.get() || stack.getItem() == BEER.get()|| stack.getItem() == WHISKEY.get()|| stack.getItem() == VINE.get();
            }
        });

        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 2, 96, 25)); // Slot 3
        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 3, 96, 43)); // Slot 4

        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 4, 146, 35) { // Slot 5
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });

        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        for (int col = 0; col < 9; ++col) {
            addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
        addDataSlots();
    }

    void addDataSlots(){
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return data.get(0);
            }

            @Override
            public void set(int value) {
                waterLevel = value;
            }
        });
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return data.get(1);
            }

            @Override
            public void set(int value) {
                timeLeft = value;
            }
        });
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack stackInSlot = slot.getItem();
            itemstack = stackInSlot.copy();

            // Zakresy slotów
            int containerSlots = blockEntity.getItemHandler().getSlots();
            int playerInventoryStart = containerSlots;
            int playerHotbarStart = playerInventoryStart + 27;
            int playerInventoryEnd = playerHotbarStart + 9;

            if (index < containerSlots) {
                // Przenoszenie z bloku do ekwipunku gracza
                if (!this.moveItemStackTo(stackInSlot, playerInventoryStart, playerInventoryEnd, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // Przenoszenie z ekwipunku gracza do bloku
                if (!this.moveItemStackTo(stackInSlot, 0, containerSlots, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stackInSlot.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return blockEntity != null && blockEntity.getBlockPos().distSqr(player.blockPosition()) < 64;
    }
}
