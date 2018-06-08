package com.blockout22.rpg.training.mobs;

public class MobZombie extends Mob{
    public MobZombie() {
        super("Zombie", new Stats(15, 5));
        setAttackSpeed(3000);
        setRewardXp(25);
        setDefaultInfo();
    }
}
