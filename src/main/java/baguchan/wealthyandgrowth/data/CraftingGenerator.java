package baguchan.wealthyandgrowth.data;

import baguchan.wealthyandgrowth.register.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class CraftingGenerator extends CraftingDataHelper {
	public CraftingGenerator(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(ModBlocks.WATER_BARREL, 1)
				.pattern("W")
				.pattern("B")
				.define('W', Items.WATER_BUCKET)
				.define('B', Tags.Items.BARRELS_WOODEN)
				.unlockedBy("has_item", has(Blocks.BARREL)).save(consumer);

	}
}
