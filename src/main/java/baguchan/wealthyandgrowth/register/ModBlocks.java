package baguchan.wealthyandgrowth.register;

import baguchan.wealthyandgrowth.WealthyAndGrowth;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WealthyAndGrowth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {
	public static final Block WATER_BARREL = new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD));
	@SubscribeEvent
	public static void registerBlock(RegistryEvent.Register<Block> registry) {
		registry.getRegistry().register(WATER_BARREL.setRegistryName("water_barrel"));
	}

	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> registry) {
		ModItems.register(registry, new BlockItem(WATER_BARREL, (new Item.Properties()).tab(CreativeModeTab.TAB_DECORATIONS)));
	}
}
