package com.example.adventuremod.overlay;

import com.example.adventuremod.menus.BrewmastersTableMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class BrewmastersTableScreen extends AbstractContainerScreen<BrewmastersTableMenu> {
    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation("adventuremod", "textures/gui/brewmasters_table_gui.png");

    public BrewmastersTableScreen(BrewmastersTableMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        blit(poseStack, leftPos, topPos, 0, 0, imageWidth, imageHeight);

        renderWaterLevel(poseStack);
    }


    private void renderWaterLevel(PoseStack poseStack) {
        int waterLevel = menu.waterLevel;
        int timeLeft = menu.timeLeft;
        final int maxWaterLevel = 4;
        final int maxTimeLeft = 40;

        int waterHeight = (int) ((waterLevel / (float) maxWaterLevel) * 32);
        int timeLeftWidth = (int) ((timeLeft / (float) maxTimeLeft) * 24);//h=17

        // Pozycja wskaźnika na GUI
        int waterX = leftPos + 20;
        int waterY = topPos + 37 + (32 - waterHeight);

        int timeLeftX = leftPos + 100;
        int timeLeftY = topPos + 36;

        blit(poseStack, waterX, waterY, 176, 31, 16, waterHeight); // Tekstura wskaźnika zaczyna się od (176, 0) i ma szerokość 16 px
        blit(poseStack, timeLeftX, timeLeftY, 176, 14, 24-timeLeftWidth, 17);
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, delta);
        renderTooltip(poseStack, mouseX, mouseY);
    }
}
