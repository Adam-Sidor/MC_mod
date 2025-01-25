package com.example.adventuremod;

import com.example.adventuremod.events.TickHandler;
import com.example.adventuremod.items.NewItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(AdventureMod.MODID)
public class AdventureMod {
    public static final String MODID = "adventuremod";

    public AdventureMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        NewItems.ITEMS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new TickHandler());
    }
}
