package com.example.adventuremod.capabilities;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.LazyOptional;

public class PlayerAlcoholProvider implements ICapabilitySerializable<CompoundTag> {
    public static final Capability<IPlayerAlcohol> PLAYER_ALCOHOL = CapabilityManager.get(new CapabilityToken<>() {});

    private final IPlayerAlcohol instance = new PlayerAlcohol();
    private final LazyOptional<IPlayerAlcohol> optional = LazyOptional.of(() -> instance);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == PLAYER_ALCOHOL ? optional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("alcoholLevel", instance.getAlcoholLevel());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        instance.setAlcoholLevel(nbt.getInt("alcoholLevel"));
    }
}
