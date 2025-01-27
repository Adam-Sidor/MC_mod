package com.example.adventuremod.events;
import com.example.adventuremod.AdventureMod;
import com.example.adventuremod.overlay.AlembicScreen;
import com.example.adventuremod.menus.ModMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static com.example.adventuremod.blocks.NewBlocks.*;

@Mod.EventBusSubscriber(modid = AdventureMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetupHandler {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        System.out.println("Registering AlembicScreen");
        MenuScreens.register(ModMenuTypes.ALEMBIC_MENU.get(), AlembicScreen::new);
        ItemBlockRenderTypes.setRenderLayer(BARLEY_CROP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ALEMBIC.get(), RenderType.cutout());
    }
}
