package com.example.adventuremod.overlay;

import com.example.adventuremod.capabilities.PlayerAlcoholProvider;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "adventuremod", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class CustomHUD {
    private static final ResourceLocation CUSTOM_BAR = new ResourceLocation("adventuremod", "textures/gui/custom_bar.png");

    @SubscribeEvent
    public static void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) return;

        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();
        int x = screenWidth / 2 + 10;
        int y;
        if(player.getAirSupply()<300){
            y = screenHeight - 59;
        }
        else {
            y = screenHeight - 49;
        }

        player.getCapability(PlayerAlcoholProvider.PLAYER_ALCOHOL).ifPresent(alcohol -> {
            int alcoholLevel = alcohol.getAlcoholLevel(); // Poziom alkoholu (od 0 do 20)
            if (alcoholLevel > 0) {
                RenderSystem.setShaderTexture(0, CUSTOM_BAR);
                GuiComponent.blit(event.getMatrixStack(), x, y, 0, 0, 80, 8, 80, 16);
                int fill = (int) (80.0 * alcoholLevel / 20.0); // Zakładamy maksymalny poziom alkoholu jako 20
                GuiComponent.blit(event.getMatrixStack(), x + 80 - fill, y, 80 - fill, 8, fill, 8, 80, 16);            }
        });
    }
}
