package com.example.adventuremod.blocks.entities;

import com.example.adventuremod.blocks.AlembicBlock;
import com.example.adventuremod.menus.AlembicMenu;
import com.example.adventuremod.recipes.AlembicRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.core.particles.ParticleTypes;
import org.jetbrains.annotations.NotNull;

public class AlembicEntity extends BlockEntity implements net.minecraft.world.MenuProvider {
    private int waterLevel;
    private int fuelLevel;
    private final int fuelMaxTime = 16 * 20;
    private int fuelTime;
    private boolean canFuelStart;
    private boolean canProduce;
    private int timeLeft;
    private ItemStack waitingItem;
    private final ItemStackHandler itemHandler = new ItemStackHandler(6);
    private Direction facing;
    private int lightLevel;
    private final BlockPos pos;

    public AlembicEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALEMBIC.get(), pos, state);
        this.waterLevel = 0;
        this.fuelLevel = 0;
        this.fuelTime = 0;
        this.canProduce = true;
        this.timeLeft = 40;
        this.canFuelStart = false;
        this.lightLevel = 0;
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
        fuelManagement();
        generateParticles();
        updateLightLevel(fuelLevel*2);
    }

    public void process() {
        waterManagement();
        ItemStack input1 = itemHandler.getStackInSlot(2);
        ItemStack input2 = itemHandler.getStackInSlot(3);
        ItemStack input3 = itemHandler.getStackInSlot(4);

        if ((!input1.isEmpty() || !input2.isEmpty() || !input3.isEmpty()) && waterLevel > 0) {
            ItemStack result = AlembicRecipes.getResult(input1, input2, input3, itemHandler.getStackInSlot(5));
            if (!result.isEmpty()) {
                canFuelStart = true;
                if (fuelLevel > 0) {
                    input1.shrink(1);
                    input2.shrink(1);
                    input3.shrink(1);
                    waterLevel--;
                    setVariablesToWait(result);
                }
            } else {
                canFuelStart = false;
            }
        } else {
            canFuelStart = false;
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
    }

    private void fuelManagement() {
        if (fuelLevel > 0) {
            fuelTime++;
            if (fuelTime > fuelMaxTime) {
                fuelLevel--;
                fuelTime = 0;
            }
        }
        ItemStack fuelSlot = itemHandler.getStackInSlot(1);
        if (fuelSlot.getItem() == Items.COAL && fuelLevel <= 0 && canFuelStart) {
            fuelLevel = 8;
            fuelSlot.shrink(1);
        }
    }

    private void generateParticles(){
        if (fuelLevel > 0) {
            double x = pos.getX();
            double y = pos.getY();
            double z = pos.getZ();
            switch(facing){
                case NORTH:
                    x+=0.25;
                    z+=0.5;
                    break;
                case SOUTH:
                    x+=0.75;
                    z+=0.5;
                    break;
                case EAST:
                    x+=0.5;
                    z+=0.25;
                    break;
                case WEST:
                    x+=0.5;
                    z+=0.75;
                    break;
            }
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(
                        ParticleTypes.FLAME,  // Typ cząsteczki
                        x,
                        y+0.1,
                        z,
                        1,             // Liczba cząsteczek
                        0.001,                  // Offset X
                        0.001,                  // Offset Y
                        0.001,                  // Offset Z
                        0.01                  // Prędkość
                );
            }
        }
    }

    private void updateLightLevel(int newLightLevel) {
        if (this.lightLevel != newLightLevel) {
            setLightLevel(newLightLevel);
        }
    }

    public void setLightLevel(int lightLevel) {
        this.lightLevel = Math.min(Math.max(lightLevel, 0), 15);
        if (level != null && !level.isClientSide) {
            level.setBlock(worldPosition, getBlockState().setValue(AlembicBlock.LIGHT_LEVEL, this.lightLevel), 3);
            level.updateNeighborsAt(worldPosition, getBlockState().getBlock());
            level.getLightEngine().checkBlock(worldPosition);
        }
    }


    public Direction getFacing() {
        return this.getBlockState().getValue(AlembicBlock.FACING);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (level != null && !level.isClientSide) {
            level.setBlock(worldPosition, getBlockState().setValue(AlembicBlock.LIGHT_LEVEL, lightLevel), 3);
        }
    }

    @Override
    public @NotNull Component getDisplayName() {
        return new TextComponent("Alembic");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
        return new AlembicMenu(id, playerInventory, this);
    }

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }
}
