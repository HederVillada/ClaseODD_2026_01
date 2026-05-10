package Juego.Abilities;

import Juego.Personaje;
import java.util.Random;

public class MagDump implements Ability {
    @Override
    public String getName() { return "Mag Dump"; }

    @Override
    public boolean isPassive() { return false; }

    @Override
    public double getAccuracyMult() { return 0.33; } // Significant accuracy penalty

    @Override
    public void execute(Personaje user, Personaje target) {
        int shotsToFire = user.activeWeapon.currentAmmo;
        
        if (shotsToFire <= 0) {
            System.out.println("! Click... The magazine is bone dry.");
            return;
        }

        System.out.println(">> " + user.getNombre() + " SCREAMS AND DRAINS THE MAG!");
        Random rand = new Random();
        
        // Calculation using the weapon type multiplier and the 0.33 ability penalty
        double finalAcc = user.accuracy * user.activeWeapon.getType().accMultiplier * 0.33;

        for (int i = 1; i <= shotsToFire; i++) {
            user.activeWeapon.currentAmmo--; 
            
            if (rand.nextInt(100) < finalAcc) {
                int min = user.activeWeapon.getType().minDmg;
                int max = user.activeWeapon.getType().maxDmg;
                int dmg = rand.nextInt((max - min) + 1) + min;
                
                target.takeDamage(dmg);
                System.out.println("   [!] Round #" + i + " CONNECTED! Dealt " + dmg + " damage.");
            } else {
                System.out.println("   [ ] Round #" + i + " missed wide.");
            }
        }
        
        System.out.println(">> LOCK TO REAR. " + user.getNombre() + " is out of ammo!");
    }
}