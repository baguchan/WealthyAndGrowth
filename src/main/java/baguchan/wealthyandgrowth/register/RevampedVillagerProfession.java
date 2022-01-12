package baguchan.wealthyandgrowth.register;

import baguchan.wealthyandgrowth.WealthyAndGrowth;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.Set;
import java.util.function.Predicate;

import static net.minecraft.world.entity.ai.village.poi.PoiType.getBlockStates;

@Mod.EventBusSubscriber(modid = WealthyAndGrowth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RevampedVillagerProfession {
	public static final PoiType FISHERMAN = register("fisherman", getBlockStates(ModBlocks.WATER_BARREL), 1, 1);

	private static PoiType register(String p_27375_, Set<BlockState> p_27376_, int p_27377_, int p_27378_) {
		return registerBlockStates(new PoiType(p_27375_, p_27376_, p_27377_, p_27378_));
	}

	private static PoiType register(String p_27380_, Set<BlockState> p_27381_, int p_27382_, Predicate<PoiType> p_27383_, int p_27384_) {
		return registerBlockStates(new PoiType(p_27380_, p_27381_, p_27382_, p_27383_, p_27384_));
	}

	private static PoiType registerBlockStates(PoiType p_27368_) {
		return p_27368_;
	}

	@SubscribeEvent
	public static void registerProfession(RegistryEvent.Register<PoiType> registry){
		registry.getRegistry().register(FISHERMAN.setRegistryName("fisherman"));
	}
}
