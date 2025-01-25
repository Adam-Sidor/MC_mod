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
        // Renderowanie GUI
        RenderSystem.setShaderTexture(0, GUI_TEXTURE); // Ustawienie tekstury GUI
        blit(poseStack, leftPos, topPos, 0, 0, imageWidth, imageHeight); // Renderowanie tekstury
    }

}
