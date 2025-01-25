package com.example.adventuremod.menus;

import com.example.adventuremod.blocks.entities.AlembicEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class AlembicMenu extends AbstractContainerMenu {
    private final AlembicEntity blockEntity;

    public AlembicMenu(int id, Inventory playerInventory, AlembicEntity blockEntity) {
        super(ModMenuTypes.ALEMBIC_MENU.get(), id);
        this.blockEntity = blockEntity;

        // Sloty bloku (woda, paliwo, składniki)
        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 0, 20, 35));
        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 1, 50, 35));
        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 2, 80, 35));
        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 3, 110, 35));
        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 4, 140, 35));

        // Sloty gracza
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        for (int col = 0; col < 9; ++col) {
            addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return blockEntity != null && blockEntity.getBlockPos().distSqr(player.blockPosition()) < 64;
    }

    private void addPlayerSlots(Inventory playerInventory) {
        // Logika dodawania slotów gracza
    }

    private void addContainerSlots(ItemStackHandler handler) {
        // Logika dodawania slotów kontenera
    }

}
