package baguchan.wealthyandgrowth.client;

import baguchan.wealthyandgrowth.register.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@OnlyIn(Dist.CLIENT)
public class ClientRegistrar {
	public static void setup(FMLCommonSetupEvent event) {
		renderBlockColor();
		renderBlockLayer();
	}

	private static void renderBlockLayer() {
		setRenderLayer(ModBlocks.WATER_BARREL, RenderType.cutout());
	}

	private static void setRenderLayer(Block block, RenderType type) {
		ItemBlockRenderTypes.setRenderLayer(block, type::equals);
	}

	private static void renderBlockColor() {
	}
}
