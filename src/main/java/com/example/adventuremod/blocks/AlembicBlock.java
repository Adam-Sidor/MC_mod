package com.example.adventuremod.blocks;

import com.example.adventuremod.blocks.entities.AlembicEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class AlembicBlock extends Block implements EntityBlock {
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);

    private static final VoxelShape SHAPE_NORTH = Shapes.or(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(11, 1, 7, 14, 6, 10),
            Block.box(3, 1, 7, 6, 14, 10),
            Block.box(2, 2, 6, 7, 7, 11),
            Block.box(2, 11, 6, 7, 12, 11),
            Block.box(4, 12, 6, 5, 13, 7),
            Block.box(11, 9, 7, 14, 10, 10),
            Block.box(12, 6, 8, 13, 15, 9),
            Block.box(4, 13, 8, 5, 16, 9),
            Block.box(5, 15, 8, 13, 16, 9),
            Block.box(2, 7, 6, 7, 8, 11)
    );

    private static final VoxelShape SHAPE_EAST = Shapes.or(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(6, 1, 11, 9, 6, 14),
            Block.box(6, 1, 3, 9, 14, 6),
            Block.box(5, 2, 2, 10, 7, 7),
            Block.box(5, 11, 2, 10, 12, 7),
            Block.box(9, 12, 4, 10, 13, 5),
            Block.box(6, 9, 11, 9, 10, 14),
            Block.box(7, 6, 12, 8, 15, 13),
            Block.box(7, 13, 4, 8, 16, 5),
            Block.box(7, 15, 5, 8, 16, 13),
            Block.box(5, 7, 2, 10, 8, 7)
    );

    private static final VoxelShape SHAPE_SOUTH = Shapes.or(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(2, 1, 6, 5, 6, 9),
            Block.box(10, 1, 6, 13, 14, 9),
            Block.box(9, 2, 5, 14, 7, 10),
            Block.box(9, 11, 5, 14, 12, 10),
            Block.box(11, 12, 9, 12, 13, 10),
            Block.box(2, 9, 6, 5, 10, 9),
            Block.box(3, 6, 7, 4, 15, 8),
            Block.box(11, 13, 7, 12, 16, 8),
            Block.box(3, 15, 7, 11, 16, 8),
            Block.box(9, 7, 5, 14, 8, 10)
    );

    private static final VoxelShape SHAPE_WEST = Shapes.or(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(7, 1, 2, 10, 6, 5),
            Block.box(7, 1, 10, 10, 14, 13),
            Block.box(6, 2, 9, 11, 7, 14),
            Block.box(6, 11, 9, 11, 12, 14),
            Block.box(6, 12, 11, 7, 13, 12),
            Block.box(7, 9, 2, 10, 10, 5),
            Block.box(8, 6, 3, 9, 15, 4),
            Block.box(8, 13, 11, 9, 16, 12),
            Block.box(8, 15, 3, 9, 16, 11),
            Block.box(6, 7, 9, 11, 8, 14)
    );

    public AlembicBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
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

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof AlembicEntity) {
                NetworkHooks.openGui((ServerPlayer) player, (AlembicEntity) blockEntity, pos);
            } else {
                throw new IllegalStateException("Brak odpowiedniego BlockEntity dla Alembica");
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACING)) {
            case EAST:
                return SHAPE_EAST;
            case SOUTH:
                return SHAPE_SOUTH;
            case WEST:
                return SHAPE_WEST;
            default:
                return SHAPE_NORTH;
        }
    }
}
