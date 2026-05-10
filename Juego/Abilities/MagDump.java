package Juego.Abilities;

import Juego.Personaje;
import java.util.Random;

public class MagDump implements Ability {
    @Override
    public String getName() { return "Mag Dump"; }

    @Override
    public boolean isPassive() { return false; }

    @Override
    public double getAccuracyMult() { return 0.33; }

    @Override
    public void execute(Personaje user, Personaje target) {
        int shotsToFire = user.activeWeapon.currentAmmo;
        
        if (shotsToFire <= 0) {
            System.out.println("! Click... the magazine is empty.");
            return;
        }

        System.out.println(">> " + user.nombre + " dumps the mag!");
        Random rand = new Random();
        
        // This line was red because accMultiplier wasn't public in the Enum
        double finalAcc = user.accuracy * user.activeWeapon.type.accMultiplier * this.getAccuracyMult();

        for (int i = 0; i < shotsToFire; i++) {
            user.activeWeapon.currentAmmo--; 
            if (rand.nextInt(100) < finalAcc) {
                // These lines were red because minDmg/maxDmg weren't public
                int min = user.activeWeapon.type.minDmg;
                int max = user.activeWeapon.type.maxDmg;
                int dmg = rand.nextInt((max - min) + 1) + min;
                target.takeDamage(dmg);
                System.out.println("   [HIT] Bang! " + dmg + " damage.");
            } else {
                System.out.println("   [MISS] *whiz*");
            }
        }
    }
}