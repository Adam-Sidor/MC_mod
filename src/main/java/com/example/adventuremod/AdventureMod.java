package com.example.adventuremod;

import com.example.adventuremod.blocks.NewBlocks;
import com.example.adventuremod.blocks.entities.ModBlockEntities;
import com.example.adventuremod.events.ClientSetupHandler;
import com.example.adventuremod.menus.ModMenuTypes;
import com.example.adventuremod.events.TickHandler;
import com.example.adventuremod.items.NewItems;
import com.example.adventuremod.network.ModPackets;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(AdventureMod.MODID)
public class AdventureMod {
    public static final String MODID = "adventuremod";

    public AdventureMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Rejestracja przedmiotów
        NewItems.ITEMS.register(modEventBus);
        // Rejestracja bloków
        NewBlocks.register(modEventBus);
        // Rejestracja BlockEntity
        ModBlockEntities.register(modEventBus);
        // Rejestracja MenuType
        ModMenuTypes.register(modEventBus);

        modEventBus.addListener(this::setup);
        // Rejestracja wydarzeń
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new TickHandler());
        // Rejestracja pakietów
        ModPackets.register();
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Ustawienia clienta
        MinecraftForge.EVENT_BUS.register(new ClientSetupHandler());
    }
}
