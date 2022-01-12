package baguchan.wealthyandgrowth.data;

import baguchan.wealthyandgrowth.WealthyAndGrowth;
import baguchan.wealthyandgrowth.register.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagGenerator extends BlockTagsProvider {
	public BlockTagGenerator(DataGenerator generator, ExistingFileHelper exFileHelper) {
		super(generator, WealthyAndGrowth.MODID, exFileHelper);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags() {
		tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.WATER_BARREL);
	}
}