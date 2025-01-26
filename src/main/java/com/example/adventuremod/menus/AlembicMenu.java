package com.example.adventuremod.menus;

import com.example.adventuremod.blocks.entities.AlembicEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.SlotItemHandler;

import java.util.Arrays;

import static com.example.adventuremod.items.NewItems.BOTTLE;
import static com.example.adventuremod.items.NewItems.MUG;

public class AlembicMenu extends AbstractContainerMenu {
    private final AlembicEntity blockEntity;

    public AlembicMenu(int id, Inventory playerInventory, AlembicEntity blockEntity) {
        super(ModMenuTypes.ALEMBIC_MENU.get(), id);
        this.blockEntity = blockEntity;

        // Sloty bloku (woda, paliwo, składniki)
        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 0, 20, 17){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() == Items.WATER_BUCKET;
            }
        });

        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 1, 56, 53){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() == Items.COAL || stack.getItem() == Items.CHARCOAL;
            }
        });

        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 2, 56, 17){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() == BOTTLE.get()|| stack.getItem() == MUG.get();
            }
        });

        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 3, 96, 25)); // Slot 3
        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 4, 96, 43)); // Slot 4

        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 5, 146, 35) {  // Slot 5
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
