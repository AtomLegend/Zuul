package com.fab.Creature;

import com.fab.Util.GameStats;
import com.fab.Util.Randomizer;
import com.fab.Weapon.Weapon;

public class Player extends Creature {


    public Player() {
        this.health = GameStats.playerHealth;
        this.currentWeapon = new Weapon(Randomizer.getInstance().getRandomPlayerWeaponDamage());
    }

    public String getStatString() {
        return String.format("Health : %s\nWeapon Damage: %s", this.health, this.currentWeapon.damage);
    }
}
