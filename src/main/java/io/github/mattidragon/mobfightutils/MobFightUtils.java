package io.github.mattidragon.mobfightutils;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class MobFightUtils implements ModInitializer {
    public static final GameRules.Key<GameRules.BooleanRule> ENABLED = GameRuleRegistry.register("mobFightUtils/enabled", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(true));

    @Override
    public void onInitialize() {

    }
}
