package Juego.Abilities;

import java.util.Random;

public class Trickshot implements Ability {
    public String getName() { return "Trickshot"; }
    public boolean isPassive() { return false; }
    public double getAccuracyMult() { return 0.33; } // "Low Accuracy" rule

    public void execute(Personaje user, Personaje target) {
        if (user.activeWeapon.getCurrentAmmo() < 3) {
            System.out.println("! Insufficient ammo.");
            return;
        }

        System.out.println(">> " + user.nombre + " attempts a Trickshot!");
        Random rand = new Random();

        // Rule: Character Acc -> Weapon Class Mult -> Ability Mult
        double charAcc = user.accuracy;
        double classMult = user.activeWeapon.getType().accMultiplier;
        double abilityMult = this.getAccuracyMult();

        double finalAcc = charAcc * classMult * abilityMult;

        for (int i = 0; i < 3; i++) {
            user.activeWeapon.currentAmmo--;
            if (rand.nextInt(100) < finalAcc) {
                int dmg = rand.nextInt((user.activeWeapon.getType().maxDmg - user.activeWeapon.getType().minDmg) + 1) 
                          + user.activeWeapon.getType().minDmg;
                target.takeDamage(dmg);
                System.out.println("   [HIT] Roll vs " + (int)finalAcc + "%: Success!");
            } else {
                System.out.println("   [MISS] Roll vs " + (int)finalAcc + "%: Deviation.");
            }
        }
    }
}