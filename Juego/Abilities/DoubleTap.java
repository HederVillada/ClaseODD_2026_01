package Juego.Abilities;

import Juego.Personaje;
import java.util.Random;

public class DoubleTap implements Ability {
    @Override
    public String getName() { return "Double Tap"; }
    @Override
    public boolean isPassive() { return false; }
    @Override
    public double getAccuracyMult() { return 0.70; } // Better accuracy than a Mag Dump

    @Override
    public void execute(Personaje user, Personaje target) {
        if (user.activeWeapon.currentAmmo < 2) {
            System.out.println("! Not enough ammo for a Double Tap.");
            return;
        }

        System.out.println(">> " + user.getNombre() + " places two quick shots!");
        Random rand = new Random();
        double finalAcc = user.accuracy * user.activeWeapon.getType().accMultiplier * 0.70;

        for (int i = 1; i <= 2; i++) {
            user.activeWeapon.currentAmmo--;
            if (rand.nextInt(100) < finalAcc) {
                int min = user.activeWeapon.getType().minDmg;
                int max = user.activeWeapon.getType().maxDmg;
                int dmg = rand.nextInt((max - min) + 1) + min;
                
                target.takeDamage(dmg);
                System.out.println("   [!] Round #" + i + " CONNECTED! Dealt " + dmg + " damage.");
            } else {
                System.out.println("   [ ] Round #" + i + " missed.");
            }
        }
    }
}