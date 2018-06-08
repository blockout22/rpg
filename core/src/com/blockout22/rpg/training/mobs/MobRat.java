package com.blockout22.rpg.training.mobs;

public class MobRat extends Mob {

    public MobRat() {
        super("Rat", new Stats(5, 2));
        setAttackSpeed(5000);
        setRewardXp(15);
        setDefaultInfo();
//        setInfo("Attacks the player every " +  (getAttackSpeed() / 1000) + " seconds hitting a maximum of " + getStats().getStrength() + " and has " + getStats().getMaxhealth() + " Health.");
    }
}
