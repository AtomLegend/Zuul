package com.fab.Util;

import java.util.Random;

public class Randomizer {

    private static Randomizer instance;
    private static Random random = new Random();

    // TODO make constants

    private Randomizer() {
    }

    public static synchronized Randomizer getInstance() {
        if (instance == null) {
            instance = new Randomizer();
        }
        return instance;
    }

    public boolean getRandomEvilness() {
        return random.nextBoolean();
    }
    /**
     * Player has 100 health, so the enemy should have about half of it
     */
    public double getRandomEnemyHealth() {
        return getRandomDoubleValue(GameStats.enemyHealthMin, GameStats.enemyHealthMax);
    }

    /**
     * Get random weapon damage
     */

    public double getRandomEnemyWeaponDamage() {
        return getRandomDoubleValue(GameStats.enemyWeaponDamageMin, GameStats.enemyWeaponDamageMax);
    }

    public double getRandomPlayerWeaponDamage() {
        return getRandomDoubleValue(GameStats.playerWeaponDamageMin, GameStats.playerWeaponDamageMax);
    }

    private double getRandomDoubleValue(double rangeMin, double rangeMax) {
        return rangeMin + (rangeMax - rangeMin) * random.nextDouble();
    }
}
