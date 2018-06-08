package com.blockout22.rpg.training.mobs;

public class MobArray {

    private final boolean isFree;
    private final Mob mob;

    public MobArray(boolean free, Mob mob){
        this.isFree = free;
        this.mob = mob;
    }

    public Mob getMob() {
        return mob;
    }

    public boolean isFree() {
        return isFree;
    }
}
