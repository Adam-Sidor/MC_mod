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

    private final ItemStackHandler itemHandler = new ItemStackHandler(5);  // 5 slotów: Woda, Paliwo, 3 składniki

    public AlembicEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALEMBIC.get(), pos, state);
    }

    // Metoda przetwarzająca składniki
    public void process() {
        ItemStack waterSlot = itemHandler.getStackInSlot(0);  // Slot na wodę
        ItemStack fuelSlot = itemHandler.getStackInSlot(1);   // Slot na paliwo

        if (waterSlot.getItem() == Items.WATER_BUCKET && !fuelSlot.isEmpty()) {  // Jeżeli mamy wodę i paliwo
            ItemStack input1 = itemHandler.getStackInSlot(2);  // Składnik 1
            ItemStack input2 = itemHandler.getStackInSlot(3);  // Składnik 2
            ItemStack input3 = itemHandler.getStackInSlot(4);  // Składnik 3

            // Sprawdzamy, czy wszystkie sloty mają składniki
            if (!input1.isEmpty() && !input2.isEmpty() && !input3.isEmpty()) {
                // Tworzymy wynik na podstawie receptury
                ItemStack result = AlembicRecipes.getResult(input1, input2, input3);

                if (!result.isEmpty()) {
                    // Zużywamy składniki
                    input1.shrink(1);
                    input2.shrink(1);
                    input3.shrink(1);

                    // Ustawiamy nowy wynik w odpowiednim slocie
                    itemHandler.setStackInSlot(2, result);

                    // Zużywamy wodę i paliwo
                    waterSlot.shrink(1);
                    itemHandler.setStackInSlot(0, new ItemStack(Items.BUCKET));  // Zostawiamy puste wiaderko w slocie

                    fuelSlot.shrink(1);
                }
            }
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
