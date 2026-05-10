package Juego.Abilities;
import Juego.Personaje;
import java.util.Random;

public class SuppressiveFire implements Ability {
    public String getName() { return "Suppressive Fire"; }
    public boolean isPassive() { return false; }
    public double getAccuracyMult() { return 0.25; } // Super Low Accuracy (1/4)

    public void execute(Personaje user, Personaje target) {
        if (user.activeWeapon.currentAmmo < 5) {
            System.out.println("! Need 5 rounds to suppress the target.");
            return;
        }
        System.out.println(">> " + user.nombre + " sprays wildly to suppress!");
        Random rand = new Random();
        double finalAcc = user.accuracy * user.activeWeapon.type.accMultiplier * 0.25;

        for (int i = 0; i < 5; i++) {
            user.activeWeapon.currentAmmo--;
            if (rand.nextInt(100) < finalAcc) {
                int dmg = rand.nextInt((user.activeWeapon.type.maxDmg - user.activeWeapon.type.minDmg) + 1) + user.activeWeapon.type.minDmg;
                target.takeDamage(dmg);
                System.out.println("   [HIT] Stray round connected!");
            }
        }
        target.accuracy -= 10;
        System.out.println("   Target is suppressed! Accuracy reduced by 10.");
    }
}