package com.example.adventuremod.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = "adventuremod", bus = Bus.FORGE, value = Dist.CLIENT)
public class CustomHUD {
    private static final ResourceLocation CUSTOM_BAR = new ResourceLocation("adventuremod", "textures/gui/custom_bar.png");

    @SubscribeEvent
    public static void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        int width = mc.getWindow().getGuiScaledWidth();
        int height = mc.getWindow().getGuiScaledHeight();
        int x = width / 2 + 10; // Dostosuj pozycję x, aby pasek był wyrównany
        int y = height - 49; // Umieść nad aktualnym paskiem jedzenia

        // Render empty bar
        RenderSystem.setShaderTexture(0, CUSTOM_BAR);
        GuiComponent.blit(event.getMatrixStack(), x, y, 0, 0, 80, 8, 80, 16);

        // Calculate fill amount based on food level
        int foodLevel = mc.player.getFoodData().getFoodLevel();
        int fill = (int)(80.0 * foodLevel / 20.0);

        // Render filled bar from the right side
        GuiComponent.blit(event.getMatrixStack(), x + 80 - fill, y, 80 - fill, 8, fill, 8, 80, 16);
    }
}
