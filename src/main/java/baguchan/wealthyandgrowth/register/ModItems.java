package baguchan.wealthyandgrowth.register;

import baguchan.wealthyandgrowth.WealthyAndGrowth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.RegistryEvent;

public class ModItems {
	public static void register(RegistryEvent.Register<Item> registry, Item item, String id) {
		item.setRegistryName(new ResourceLocation(WealthyAndGrowth.MODID, id));
		registry.getRegistry().register(item);
	}

	public static void register(RegistryEvent.Register<Item> registry, Item item) {
		if (item instanceof BlockItem && item.getRegistryName() == null) {
			item.setRegistryName(((BlockItem) item).getBlock().getRegistryName());
		}
		registry.getRegistry().register(item);
	}
}
