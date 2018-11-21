package com.fab.Creature;

import com.fab.Util.EnemyDecision;
import com.fab.Util.Randomizer;
import com.fab.Weapon.Weapon;

public class Enemy extends Creature {

    private boolean evil;

    public Enemy() {
        this.evil = Randomizer.getInstance().getRandomEvilness();
        this.health = Randomizer.getInstance().getRandomEnemyHealth();
        this.currentWeapon = new Weapon(Randomizer.getInstance().getRandomEnemyWeaponDamage());
    }

    public EnemyDecision reactToSpeakAction() {
        if (this.evil) {
            System.out.println("Im not here to speak human, diiiee!");
            return EnemyDecision.BAD;
        } else {
            System.out.println("Glad you didn't think im evil, go on in your quest");
            return EnemyDecision.GOOD;
        }
    }

    public void reactToAttack() {
        // TODO system out print something like "attaaack"
    }
}
