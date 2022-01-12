package baguchan.wealthyandgrowth.block;

import baguchan.wealthyandgrowth.register.ModBlocks;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class WaterBarrelBlock extends Block implements SimpleWaterloggedBlock {
	private static final VoxelShape OUTER_SHAPE = Shapes.block();
	private static final VoxelShape[] SHAPES = Util.make(new VoxelShape[9], (p_51967_) -> {
		for (int i = 0; i < 8; ++i) {
			p_51967_[i] = Shapes.join(OUTER_SHAPE, Block.box(2.0D, (double) Math.max(2, 1 + i * 2), 2.0D, 14.0D, 16.0D, 14.0D), BooleanOp.ONLY_FIRST);
		}

		p_51967_[8] = p_51967_[7];
	});
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public WaterBarrelBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void onRemove(BlockState p_60515_, Level p_60516_, BlockPos p_60517_, BlockState p_60518_, boolean p_60519_) {
		super.onRemove(p_60515_, p_60516_, p_60517_, p_60518_, p_60519_);
		if (p_60516_.getBlockState(p_60517_).getFluidState().getType() == Fluids.WATER && p_60516_.getBlockState(p_60517_).getBlock() != ModBlocks.WATER_BARREL) {
			p_60516_.setBlock(p_60517_, Blocks.AIR.defaultBlockState(), 2);
		}
	}

	public void playerWillDestroy(Level p_56212_, BlockPos p_56213_, BlockState p_56214_, Player p_56215_) {

		if (!p_56212_.isClientSide) {
			ItemStack itemstack = getMobStack(p_56212_, p_56213_, p_56214_);

			ItemEntity itementity = new ItemEntity(p_56212_, (double) p_56213_.getX() + 0.5D, (double) p_56213_.getY() + 0.5D, (double) p_56213_.getZ() + 0.5D, itemstack);
			itementity.setDefaultPickUpDelay();
			p_56212_.addFreshEntity(itementity);
		}


		super.playerWillDestroy(p_56212_, p_56213_, p_56214_, p_56215_);
	}

	public boolean placeLiquid(LevelAccessor p_152805_, BlockPos p_152806_, BlockState p_152807_, FluidState p_152808_) {
		if (!p_152807_.getValue(WATERLOGGED) && p_152808_.getType() == Fluids.WATER) {
			BlockState blockstate = p_152807_.setValue(WATERLOGGED, Boolean.valueOf(true));
			p_152805_.setBlock(p_152806_, blockstate, 3);

			return true;
		} else {
			return false;
		}
	}

	public void setPlacedBy(Level level, BlockPos pos, BlockState p_56208_, LivingEntity p_56209_, ItemStack stack) {
		addMobFromID(level, stack, pos, p_56208_);
	}

	private void addMobFromID(Level level, ItemStack stack, BlockPos pos, BlockState state) {
		level.setBlock(pos, state.setValue(WATERLOGGED, stack.getOrCreateTag().getBoolean("HasWater")), 3);
		if (stack.getTag() != null && !stack.getTag().isEmpty()) {
			ListTag listtag = stack.getOrCreateTag().getList("Mobs", 10);

			for (int i = 0; i < listtag.size(); ++i) {
				CompoundTag compoundtag = listtag.getCompound(i);
				Entity entity = EntityType.loadEntityRecursive(compoundtag.getCompound("EntityData"), level, (p_58740_) -> {
					return p_58740_;
				});
				if (entity != null) {
					entity.setPos(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F);
					level.addFreshEntity(entity);
				}
			}

		}
	}

	private ItemStack getMobStack(Level level, BlockPos pos, BlockState state) {
		ItemStack stack = new ItemStack(ModBlocks.WATER_BARREL);
		List<Mob> list = level.getEntitiesOfClass(Mob.class, new AABB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1.0D, pos.getY() + 1.0D, pos.getZ() + 1.0D));
		if (!list.isEmpty()) {
			ListTag listtag = writeMobs(list);
			stack.getOrCreateTag().put("Mobs", listtag);
		}
		stack.getOrCreateTag().putBoolean("HasWater", state.getValue(WATERLOGGED));
		return stack;
	}

	public ListTag writeMobs(List<Mob> list) {
		ListTag listtag = new ListTag();

		for (Mob mob : list) {
			if(mob instanceof Animal || mob instanceof AbstractFish) {
				CompoundTag compoundtag = new CompoundTag();
				mob.save(compoundtag);
				compoundtag.remove("UUID");
				CompoundTag compoundtag1 = new CompoundTag();
				compoundtag1.put("EntityData", compoundtag);
				listtag.add(compoundtag1);
				mob.discard();
			}
		}

		return listtag;
	}

	public VoxelShape getShape(BlockState p_51973_, BlockGetter p_51974_, BlockPos p_51975_, CollisionContext p_51976_) {
		return SHAPES[0];
	}

	public VoxelShape getCollisionShape(BlockState p_51990_, BlockGetter p_51991_, BlockPos p_51992_, CollisionContext p_51993_) {
		return SHAPES[0];
	}

	@Override
	public FluidState getFluidState(BlockState p_152844_) {
		return p_152844_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_152844_);
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_152840_) {
		p_152840_.add(WATERLOGGED);
	}
}
