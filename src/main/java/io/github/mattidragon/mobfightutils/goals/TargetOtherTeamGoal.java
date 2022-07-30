package io.github.mattidragon.mobfightutils.goals;

import io.github.mattidragon.mobfightutils.MobFightUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.Box;

public class TargetOtherTeamGoal extends ActiveTargetGoal<LivingEntity> {
    public TargetOtherTeamGoal(MobEntity entity) {
        super(entity, LivingEntity.class, 10, true, false, ignored -> true);
    }

    @Override
    public boolean canStart() {
        if (!mob.world.getGameRules().get(MobFightUtils.ENABLED).get())
            return false;

        if (this.mob.getScoreboardTeam() == null)
            return false;

        return super.canStart();
    }

    @Override
    protected Box getSearchBox(double distance) {
        return mob.getBoundingBox().expand(distance, distance, distance);
    }
}
