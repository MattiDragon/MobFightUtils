package io.github.mattidragon.mobfightutils.goals;

import io.github.mattidragon.mobfightutils.MobFightUtils;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.mob.MobEntity;

import java.util.EnumSet;

public class CustomRevengeGoal extends TrackTargetGoal {
    private static final TargetPredicate VALID_AVOIDABLES_PREDICATE = TargetPredicate.createAttackable().ignoreVisibility().ignoreDistanceScalingFactor();
    private int lastAttackedTime;

    public CustomRevengeGoal(MobEntity mob) {
        super(mob, true);
        this.setControls(EnumSet.of(Goal.Control.TARGET));
    }

    @Override
    public boolean canStart() {
        if (!mob.world.getGameRules().get(MobFightUtils.ENABLED).get())
            return false;
        if (mob.getLastAttackedTime() == this.lastAttackedTime || mob.getAttacker() == null)
            return false;
        return this.canTrack(mob.getAttacker(), VALID_AVOIDABLES_PREDICATE);
    }

    @Override
    public void start() {
        this.mob.setTarget(this.mob.getAttacker());
        this.target = this.mob.getTarget();
        this.lastAttackedTime = this.mob.getLastAttackedTime();
        this.maxTimeWithoutVisibility = 300;
        super.start();
    }
}

