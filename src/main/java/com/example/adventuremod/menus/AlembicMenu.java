package com.example.adventuremod.menus;

import com.example.adventuremod.blocks.entities.AlembicEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraft.world.inventory.ContainerData;

import static com.example.adventuremod.items.NewItems.BOTTLE;
import static com.example.adventuremod.items.NewItems.MUG;

public class AlembicMenu extends AbstractContainerMenu {
    private final AlembicEntity blockEntity;
    private final ContainerData data;
    public int waterLevel;
    public AlembicMenu(int id, Inventory playerInventory, AlembicEntity blockEntity) {
        super(ModMenuTypes.ALEMBIC_MENU.get(), id);
        this.blockEntity = blockEntity;
        this.data = blockEntity.getData();
        this.waterLevel = data.get(0);

        // Dodanie slotów bloku
        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 0, 20, 17) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() == Items.WATER_BUCKET;
            }
        });

        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 1, 56, 53) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() == Items.COAL || stack.getItem() == Items.CHARCOAL;
            }
        });

        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 2, 56, 17) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() == BOTTLE.get() || stack.getItem() == MUG.get();
            }
        });

        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 3, 96, 25)); // Slot 3
        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 4, 96, 43)); // Slot 4

        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 5, 146, 35) { // Slot 5
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

        // Dodanie synchronizacji poziomu wody
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return data.get(0);
            }

            @Override
            public void set(int value) {
                waterLevel = value; // Update the waterLevel field
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
            int containerSlots = blockEntity.getItemHandler().getSlots(); // Sloty bloku
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
