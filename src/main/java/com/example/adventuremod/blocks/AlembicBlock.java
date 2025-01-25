package com.example.adventuremod.blocks;

import com.example.adventuremod.blocks.entities.AlembicEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class AlembicBlock extends Block {

    public AlembicBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) { // Sprawdzamy, czy jesteśmy po stronie serwera
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof AlembicEntity) {
                NetworkHooks.openGui((ServerPlayer) player, (AlembicEntity) blockEntity, pos);
            } else {
                throw new IllegalStateException("Brak odpowiedniego BlockEntity dla Alembica");
            }
        }
        return InteractionResult.SUCCESS;
    }


}
