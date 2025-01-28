package com.example.adventuremod.blocks;

import com.example.adventuremod.blocks.entities.BrewmastersTableEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class BrewmastersTable extends Block implements EntityBlock {
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);

    public BrewmastersTable(Properties properties) {
        super(properties.strength(5.0F, 10.0F));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    private static final VoxelShape SHAPE_NORTH = Shapes.or(
            Block.box(11, 8, 11, 14, 9, 14),
            Block.box(11, 9, 14, 14, 13, 15),
            Block.box(11, 9, 10, 14, 13, 11),
            Block.box(14, 9, 11, 15, 13, 14),
            Block.box(10, 10, 14, 11, 12, 15),
            Block.box(10, 10, 10, 11, 12, 11),
            Block.box(14, 10, 14, 15, 12, 15),
            Block.box(14, 10, 10, 15, 12, 11),
            Block.box(10, 9, 11, 11, 13, 14),
            Block.box(1, 0, 1, 3, 6, 3),
            Block.box(13, 0, 1, 15, 6, 3),
            Block.box(13, 0, 13, 15, 6, 15),
            Block.box(1, 0, 13, 3, 6, 15),
            Block.box(0, 6, 0, 16, 8, 16),
            Block.box(2, 9, 8, 3, 10, 10),
            Block.box(5, 9, 8, 6, 10, 10),
            Block.box(3, 8, 8, 5, 9, 10),
            Block.box(3, 9, 7, 5, 10, 8),
            Block.box(3, 9, 10, 5, 10, 11),
            Block.box(2, 10, 7, 6, 14, 8),
            Block.box(2, 10, 10, 6, 14, 11),
            Block.box(5, 10, 7, 6, 14, 11),
            Block.box(2, 10, 7, 3, 14, 11),
            Block.box(3, 14, 8, 5, 15, 10),
            Block.box(3, 15, 8, 5, 16, 10),
            Block.box(8, 8, 3, 11, 9, 6),
            Block.box(8, 9, 6, 11, 15, 7),
            Block.box(8, 9, 2, 11, 15, 3),
            Block.box(11, 9, 3, 12, 15, 6),
            Block.box(7, 10, 6, 8, 14, 7),
            Block.box(7, 10, 2, 8, 14, 3),
            Block.box(11, 10, 6, 12, 14, 7),
            Block.box(11, 10, 2, 12, 14, 3),
            Block.box(7, 9, 3, 8, 15, 6),
            Block.box(12, 9, 4, 14, 10, 5),
            Block.box(12, 14, 4, 14, 15, 5),
            Block.box(14, 10, 4, 15, 14, 5),
            Block.box(8, 9, 3, 11, 14, 6)
    );


    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> drops = super.getDrops(state, builder);
        if (drops.isEmpty()) {
            drops.add(new ItemStack(this));
        }
        return drops;
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
        world.scheduleTick(pos, this, 0);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof BrewmastersTableEntity) {
            ((BrewmastersTableEntity) blockEntity).tick();
        }
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!world.isClientSide) {
            ((ServerLevel) world).scheduleTick(pos, this, 1);
        }
        super.onPlace(state, world, pos, oldState, isMoving);
    }

    @Override
    @Nullable
    public  BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BrewmastersTableEntity(blockPos, blockState);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof BrewmastersTableEntity) {
                NetworkHooks.openGui((ServerPlayer) player, (BrewmastersTableEntity) blockEntity, pos);
            } else {
                throw new IllegalStateException("Brak odpowiedniego BlockEntity dla Brewmasters table");
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACING)) {
            case EAST:
                return SHAPE_NORTH;
            case SOUTH:
                return SHAPE_NORTH;
            case WEST:
                return SHAPE_NORTH;
            default:
                return SHAPE_NORTH;
        }
    }
}
