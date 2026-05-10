package Juego.Abilities;
import Juego.Personaje;
import java.util.Random;

public class DoubleTap implements Ability {
    public String getName() { return "Double Tap"; }
    public boolean isPassive() { return false; }
    public double getAccuracyMult() { return 0.5; } // Medium Accuracy

    public void execute(Personaje user, Personaje target) {
        if (user.activeWeapon.currentAmmo < 2) {
            System.out.println("! Need 2 bullets for a Double Tap.");
            return;
        }
        System.out.println(">> " + user.nombre + " fires twice!");
        Random rand = new Random();
        double finalAcc = user.accuracy * user.activeWeapon.type.accMultiplier * 0.5;

        for (int i = 0; i < 2; i++) {
            user.activeWeapon.currentAmmo--;
            if (rand.nextInt(100) < finalAcc) {
                int dmg = rand.nextInt((user.activeWeapon.type.maxDmg - user.activeWeapon.type.minDmg) + 1) + user.activeWeapon.type.minDmg;
                target.takeDamage(dmg);
                System.out.println("   Hit!");
            } else {
                System.out.println("   Miss.");
            }
        }
    }
}