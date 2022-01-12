package baguchan.wealthyandgrowth.mixin;

import baguchan.wealthyandgrowth.entity.behavior.HarvestPumpkinAndMelon;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VillagerGoalPackages.class)
public class VillagerGoalPackagesMixin {
	@Inject(at = @At("HEAD"), method = "getWorkPackage" ,cancellable = true)
	private static void getWorkPackage(VillagerProfession p_24590_, float p_24591_, CallbackInfoReturnable<ImmutableList<Pair<Integer, ? extends Behavior<? super Villager>>>> callbackInfoReturnable) {
		WorkAtPoi workatpoi;
		if (p_24590_ == VillagerProfession.FARMER) {
			workatpoi = new WorkAtComposter();
		} else {
			workatpoi = new WorkAtPoi();
		}

		callbackInfoReturnable.setReturnValue(ImmutableList.of(getMinimalLookBehavior(), Pair.of(5, new RunOne<>(ImmutableList.of(Pair.of(workatpoi, 7), Pair.of(new StrollAroundPoi(MemoryModuleType.JOB_SITE, 0.4F, p_24590_ == VillagerProfession.FARMER  ? 8 : 4), 2), Pair.of(new StrollToPoi(MemoryModuleType.JOB_SITE, 0.4F, 1, p_24590_ == VillagerProfession.FARMER  ? 14 : 10), 5), Pair.of(new StrollToPoiList(MemoryModuleType.SECONDARY_JOB_SITE, p_24591_, 1, p_24590_ == VillagerProfession.FARMER  ? 8 : 6, MemoryModuleType.JOB_SITE), 5), Pair.of(new HarvestPumpkinAndMelon(), p_24590_ == VillagerProfession.FARMER ? 2 : 5), Pair.of(new HarvestFarmland(), p_24590_ == VillagerProfession.FARMER ? 2 : 5), Pair.of(new UseBonemeal(), p_24590_ == VillagerProfession.FARMER ? 4 : 7)))), Pair.of(10, new ShowTradesToPlayer(400, 1600)), Pair.of(10, new SetLookAndInteract(EntityType.PLAYER, 4)), Pair.of(2, new SetWalkTargetFromBlockMemory(MemoryModuleType.JOB_SITE, p_24591_, 9, 100, 1200)), Pair.of(3, new GiveGiftToHero(100)), Pair.of(99, new UpdateActivityFromSchedule())));
	}

	@Shadow
	private static Pair<Integer, ? extends Behavior<? super Villager>> getMinimalLookBehavior() {
		return null;
	}
}
