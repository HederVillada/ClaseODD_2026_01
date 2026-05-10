package Juego.Abilities;

import Juego.Personaje;
import java.util.Random;

public class ShotgunSpin implements Ability {
    @Override
    public String getName() { return "Shotgun Spin"; }
    @Override
    public boolean isPassive() { return false; }
    @Override
    public double getAccuracyMult() { return 0.40; }

    @Override
    public void execute(Personaje user, Personaje target) {
        // Shotguns are low capacity, so we fire up to 3 rounds if available
        int shotsToFire = Math.min(user.activeWeapon.currentAmmo, 3);
        
        if (shotsToFire <= 0) {
            System.out.println("! Click... No shells in the tube.");
            return;
        }

        System.out.println(">> " + user.getNombre() + " spins the shotgun, slamming the action!");
        Random rand = new Random();
        double finalAcc = user.accuracy * user.activeWeapon.getType().accMultiplier * 0.40;

        for (int i = 1; i <= shotsToFire; i++) {
            user.activeWeapon.currentAmmo--;
            if (rand.nextInt(100) < finalAcc) {
                int min = user.activeWeapon.getType().minDmg;
                int max = user.activeWeapon.getType().maxDmg;
                int dmg = rand.nextInt((max - min) + 1) + min;
                
                target.takeDamage(dmg);
                System.out.println("   [!] Shell #" + i + " CONNECTED! Buckshot dealt " + dmg + " damage.");
            } else {
                System.out.println("   [ ] Shell #" + i + " went wide.");
            }
        }
    }
}