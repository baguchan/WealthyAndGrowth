package baguchan.wealthyandgrowth.data;

import baguchan.wealthyandgrowth.WealthyAndGrowth;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;

public class BlockstateGenerator extends BlockStateProvider {
	public BlockstateGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, WealthyAndGrowth.MODID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
	}

	public void torchBlock(Block block, Block wall) {
		ModelFile torch = models().torch(name(block), texture(name(block)));
		ModelFile torchwall = models().torchWall(name(wall), texture(name(block)));
		simpleBlock(block, torch);
		getVariantBuilder(wall).forAllStates(state ->
				ConfiguredModel.builder()
						.modelFile(torchwall)
						.rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 90) % 360)
						.build());
	}

	public void stairs(StairBlock block, Block fullBlock) {
		stairsBlock(block, texture(name(fullBlock)));
	}

	public void slab(SlabBlock block, Block fullBlock) {
		slabBlock(block, texture(name(fullBlock)), texture(name(fullBlock)));
	}

	public void crossBlock(Block block) {

		crossBlock(block, models().cross(name(block), texture(name(block))));
	}

	public void ageThreeCrossBlock(Block block) {
		getVariantBuilder(block).forAllStates(state -> {
			int age = state.getValue(BlockStateProperties.AGE_3);
			ModelFile cross_1 = models().singleTexture(name(block) + "_" + age, mcLoc("block/cross"), "cross", texture(name(block) + "_" + age));
			return ConfiguredModel.builder()
					.modelFile(cross_1)
					.build();
		});
	}

	public void crossTintBlock(Block block) {
		ModelFile tint = models().singleTexture(name(block), mcLoc("block/tinted_cross"), "cross", texture(name(block)));


		crossBlock(block, tint);
	}

	private void crossBlock(Block block, ModelFile model) {
		getVariantBuilder(block).forAllStates(state ->
				ConfiguredModel.builder()
						.modelFile(model)
						.build());
	}

	protected ResourceLocation texture(String name) {
		return modLoc("block/" + name);
	}

	protected String name(Block block) {
		return block.getRegistryName().getPath();
	}

	@Nonnull
	@Override
	public String getName() {
		return "WealthyAndGrowth blockstates and block models";
	}
}
