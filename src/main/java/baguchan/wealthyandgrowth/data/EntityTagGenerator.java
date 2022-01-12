package baguchan.wealthyandgrowth.data;

import baguchan.wealthyandgrowth.WealthyAndGrowth;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class EntityTagGenerator extends EntityTypeTagsProvider {
	public EntityTagGenerator(DataGenerator generator, ExistingFileHelper exFileHelper) {
		super(generator, WealthyAndGrowth.MODID, exFileHelper);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags() {
	}
}