package Juego.Abilities;

import Juego.Personaje;
import java.util.Random;

public class SuppressiveFire implements Ability {
    @Override
    public String getName() { return "Suppressive Fire"; }
    @Override
    public boolean isPassive() { return false; }
    @Override
    public double getAccuracyMult() { return 0.25; }

    @Override
    public void execute(Personaje user, Personaje target) {
        if (user.activeWeapon.currentAmmo < 5) {
            System.out.println("! Not enough ammo.");
            return;
        }
        
        // FIX: Use getNombre() instead of .nombre
        System.out.println(">> " + user.getNombre() + " LAYING DOWN SUPPRESSIVE FIRE!");
        Random rand = new Random();
        double finalAcc = user.accuracy * user.activeWeapon.type.accMultiplier * 0.25;

        for (int i = 0; i < 5; i++) {
            user.activeWeapon.currentAmmo--;
            if (rand.nextInt(100) < finalAcc) {
                target.takeDamage(user.activeWeapon.type.minDmg);
                System.out.println("   [!] Stray round hit " + target.getNombre() + "!");
            }
        }

        target.accuracy -= 10;
        System.out.println("   [STATUS] " + target.getNombre() + " is pinned! Accuracy dropped.");
    }
}