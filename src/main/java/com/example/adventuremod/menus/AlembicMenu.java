package com.example.adventuremod.menus;

import com.example.adventuremod.blocks.entities.AlembicEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class AlembicMenu extends AbstractContainerMenu {
    private final AlembicEntity blockEntity;

    public AlembicMenu(int id, Inventory playerInventory, AlembicEntity blockEntity) {
        super(ModMenuTypes.ALEMBIC_MENU.get(), id);
        this.blockEntity = blockEntity;

        // Sloty bloku (woda, paliwo, składniki)
        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 0, 10, 35)); // Slot 0
        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 1, 30, 35)); // Slot 1
        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 2, 50, 35)); // Slot 2
        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 3, 70, 35)); // Slot 3
        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 4, 90, 35)); // Slot 4

        // Slot na wynik (gdzie nie można nic włożyć)
        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 5, 120, 35) {  // Slot 5
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });

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
}
