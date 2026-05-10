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
        int shots = user.activeWeapon.currentAmmo;
        if (shots <= 0) return;

        // FIX: Use getNombre()
        System.out.println(">> " + user.getNombre() + " empties the magazine!");
        Random rand = new Random();
        double finalAcc = user.accuracy * user.activeWeapon.type.accMultiplier * 0.33;

        for (int i = 0; i < shots; i++) {
            user.activeWeapon.currentAmmo--;
            if (rand.nextInt(100) < finalAcc) {
                target.takeDamage(user.activeWeapon.type.minDmg);
            }
        }
    }
}