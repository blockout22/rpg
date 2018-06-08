package com.blockout22.rpg.training.mobs.bosses;

import com.blockout22.rpg.training.mobs.Mob;
import com.blockout22.rpg.training.mobs.Stats;

public class MobHollowFalcon extends Mob {
    public MobHollowFalcon() {
        super("The Hollow Falcon", new Stats(100, 35));
        setAttackSpeed(2500);
        setRewardXp(150);
        setDefaultInfo();
    }
}
