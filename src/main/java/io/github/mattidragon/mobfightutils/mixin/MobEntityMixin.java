package io.github.mattidragon.mobfightutils.mixin;

import io.github.mattidragon.mobfightutils.goals.CustomRevengeGoal;
import io.github.mattidragon.mobfightutils.goals.TargetOtherTeamGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.PrioritizedGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public class MobEntityMixin {
    @Shadow @Final protected GoalSelector targetSelector;

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/MobEntity;initGoals()V", shift = At.Shift.AFTER))
    private void mobFightUtils$injectGoals(EntityType<?> entityType, World world, CallbackInfo ci) {
        var goals = targetSelector.getGoals();

        goals.add(new PrioritizedGoal(0, new CustomRevengeGoal((MobEntity) (Object) this)));
        goals.add(new PrioritizedGoal(10, new TargetOtherTeamGoal((MobEntity) (Object) this)));
    }
}
