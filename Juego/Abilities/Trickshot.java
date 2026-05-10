package Juego.Abilities;

import Juego.Personaje;
import java.util.Random;

public class Trickshot implements Ability {
    @Override
    public String getName() { return "Trickshot"; }

    @Override
    public boolean isPassive() { return false; }

    @Override
    public double getAccuracyMult() { return 0.33; }

    @Override
    public void execute(Personaje user, Personaje target) {
        if (user.activeWeapon.currentAmmo < 3) {
            System.out.println("! Not enough ammo for a Trickshot.");
            return;
        }

        System.out.println(">> " + user.nombre + " attempts a flashy Trickshot!");
        Random rand = new Random();
        // Uses the new public visibility we set up
        double finalAcc = user.accuracy * user.activeWeapon.type.accMultiplier * 0.33;

        for (int i = 0; i < 3; i++) {
            user.activeWeapon.currentAmmo--;
            if (rand.nextInt(100) < finalAcc) {
                int min = user.activeWeapon.type.minDmg;
                int max = user.activeWeapon.type.maxDmg;
                int dmg = rand.nextInt((max - min) + 1) + min;
                target.takeDamage(dmg);
                System.out.println("   [HIT] Bullet connected! " + dmg + " damage.");
            } else {
                System.out.println("   [MISS] The shot went wide.");
            }
        }
    }
}