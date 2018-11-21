package Tests;

import com.fab.Util.GameStats;
import com.fab.Util.Randomizer;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RandomizerTest {

    @Test
    public void testEnemyHealth() {
        double health = Randomizer.getInstance().getRandomEnemyHealth();
        assertTrue(health < GameStats.enemyHealthMax && health > GameStats.enemyHealthMin);
    }

    @Test
    public void testEnemyWeaponDamage() {
        double damage = Randomizer.getInstance().getRandomEnemyWeaponDamage();
        assertTrue(damage < GameStats.enemyWeaponDamageMax && damage > GameStats.enemyWeaponDamageMin);
    }

    @Test
    public void testPlayerWeaponDamage() {
        double damage = Randomizer.getInstance().getRandomPlayerWeaponDamage();
        assertTrue(damage < GameStats.playerWeaponDamageMax && damage > GameStats.playerWeaponDamageMin);
    }
}
