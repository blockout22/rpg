package com.blockout22.rpg.training.mobs;

public class MobUnicorn extends Mob{
    public MobUnicorn() {
        super("Unicorn", new Stats(17, 5));
        setAttackSpeed(3000);
        setRewardXp(30);
        setDefaultInfo();
    }
}
