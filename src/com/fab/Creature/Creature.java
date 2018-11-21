package com.fab.Creature;

import com.fab.Weapon.Weapon;

public class Creature {

    public double health;
    public boolean alive = true;
    public Weapon currentWeapon;

    /**
     * Deals damage to creature, creature might die of it.
     * @param damage damage that is dealt from opponent
     */
    public void getHit(double damage) {
        if (damage >= this.health) {
            this.health = 0;
            this.alive = false;
        } else {
            this.health -= damage;
        }
    }

}
