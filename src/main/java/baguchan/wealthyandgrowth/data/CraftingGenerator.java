package baguchan.wealthyandgrowth.data;

import baguchan.wealthyandgrowth.register.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class CraftingGenerator extends CraftingDataHelper {
	public CraftingGenerator(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		ShapelessRecipeBuilder.shapeless(ModBlocks.WATER_BARREL, 1)
				.requires(Tags.Items.BARRELS)
				.unlockedBy("has_item", has(Blocks.BARREL)).save(consumer);

	}
}
