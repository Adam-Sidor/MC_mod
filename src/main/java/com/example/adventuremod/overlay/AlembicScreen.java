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
        System.out.println("WW"+waterLevel);
        final int maxWaterLevel = 4;

        int waterHeight = (int) ((waterLevel / (float) maxWaterLevel) * 52); // Wysokość wskaźnika w pikselach (zakładam maksymalną wysokość 52 px)

        // Pozycja wskaźnika na GUI
        int waterX = leftPos + 20; // Pozycja X wskaźnika
        int waterY = topPos + 17 + (52 - waterHeight); // Pozycja Y wskaźnika (od dołu)

        blit(poseStack, waterX, waterY, 200, 200, 16, waterHeight); // Tekstura wskaźnika zaczyna się od (176, 0) i ma szerokość 16 px
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, delta);
        renderTooltip(poseStack, mouseX, mouseY);
    }
}
