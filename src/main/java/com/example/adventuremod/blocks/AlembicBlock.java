package com.example.adventuremod.blocks;

import com.example.adventuremod.blocks.entities.AlembicEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class AlembicBlock extends Block  implements EntityBlock {

    public AlembicBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, Random random) {
        world.scheduleTick(pos, this, 1);

        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof AlembicEntity) {
            ((AlembicEntity) blockEntity).tick();
        }
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!world.isClientSide) {
            ((ServerLevel) world).scheduleTick(pos, this, 20);
        }
        super.onPlace(state, world, pos, oldState, isMoving);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new AlembicEntity(blockPos, blockState);
    }

    // Obsługa interakcji z blokiem
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof AlembicEntity) {
                // Otwieramy GUI Alembica
                NetworkHooks.openGui((ServerPlayer) player, (AlembicEntity) blockEntity, pos);
            } else {
                throw new IllegalStateException("Brak odpowiedniego BlockEntity dla Alembica");
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.or(
                Block.box(0, 0, 0, 16, 1, 16),
                Block.box(11, 1, 7, 14, 6, 10),
                Block.box(3, 1, 7, 6, 14, 10),
                Block.box(2, 2, 6, 7, 7, 11),
                Block.box(2, 11, 6, 7, 12, 11),
                Block.box(4, 12, 6,5, 13, 7),
                Block.box(11, 9, 7,14, 10, 10),
                Block.box(12, 6, 8,13, 15, 9),
                Block.box(4, 13, 8,5, 16, 9),
                Block.box(5, 15, 8,13, 16, 9),
                Block.box(2, 7, 6,7, 8, 11)
        );
    }
}
