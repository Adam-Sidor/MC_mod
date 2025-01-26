package com.example.adventuremod.overlay;

import com.example.adventuremod.menus.AlembicMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;

public class AlembicScreen extends AbstractContainerScreen<AlembicMenu> {
    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation("adventuremod", "textures/gui/alembic_gui.png");

    public AlembicScreen(AlembicMenu menu, Inventory inv, Component title) {
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
        int fuelLevel = menu.fuelLevel;
        int timeLeft = menu.timeLeft;
        final int maxWaterLevel = 4;
        final int maxFuelLevel = 8;
        final int maxTimeLeft = 40;

        int waterHeight = (int) ((waterLevel / (float) maxWaterLevel) * 32);
        int fuelHeight = (int) ((fuelLevel / (float) maxFuelLevel) * 14);
        int timeLeftWidth = (int) ((timeLeft / (float) maxTimeLeft) * 24);//h=17

        // Pozycja wskaźnika na GUI
        int waterX = leftPos + 20;
        int waterY = topPos + 37 + (32 - waterHeight);

        int fuelX = leftPos + 56;
        int fuelY = topPos + 36 + (14 - fuelHeight);

        int timeLeftX = leftPos + 115;
        int timeLeftY = topPos + 35;

        blit(poseStack, waterX, waterY, 176, 31, 16, waterHeight); // Tekstura wskaźnika zaczyna się od (176, 0) i ma szerokość 16 px
        blit(poseStack, fuelX, fuelY, 176, 14-fuelHeight, 14, fuelHeight);
        blit(poseStack, timeLeftX, timeLeftY, 176, 14, 24-timeLeftWidth, 17);
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, delta);
        renderTooltip(poseStack, mouseX, mouseY);
    }
}
