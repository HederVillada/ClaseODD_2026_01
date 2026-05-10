package Juego.Abilities;
import Juego.Personaje;
import java.util.Random;

public class SuppressiveFire implements Ability {
    public String getName() { return "Suppressive Fire"; }
    public boolean isPassive() { return false; }
    public double getAccuracyMult() { return 0.25; }

    public void execute(Personaje user, Personaje target) {
        if (user.activeWeapon.currentAmmo < 5) return;
        
        System.out.println(">> " + user.nombre + " LAYING DOWN SUPPRESSIVE FIRE!");
        Random rand = new Random();
        double finalAcc = user.accuracy * user.activeWeapon.type.accMultiplier * 0.25;

        // The damage part (Random chance)
        for (int i = 0; i < 5; i++) {
            user.activeWeapon.currentAmmo--;
            if (rand.nextInt(100) < finalAcc) {
                target.takeDamage(user.activeWeapon.type.minDmg);
                System.out.println("   [!] Stray round hit for " + user.activeWeapon.type.minDmg);
            }
        }

        // THE GUARANTEE: This happens even if all 5 shots miss
        target.accuracy -= 10;
        System.out.println("   [STATUS] " + target.nombre + " is pinned! Accuracy dropped by 10.");
    }
}