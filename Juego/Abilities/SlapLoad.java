package Juego.Abilities;
import Juego.Personaje;
import java.util.Random;

public class SlapLoad implements Ability {
    public String getName() { return "Saboted Light Armor Penetrator Load"; }
    public boolean isPassive() { return false; }
    public double getAccuracyMult() { return 1.0; } //accuracy es 1 base, colocandolo en high

    public void execute(Personaje user, Personaje target) {
        if (user.activeWeapon.currentAmmo < 1) {
            System.out.println("! No ammo to load.");
            return;
        }
        System.out.println(">> " + user.getNombre() + " slams a hot round into the chamber!");
        Random rand = new Random();
        double finalAcc = user.accuracy * user.activeWeapon.type.accMultiplier;

        user.activeWeapon.currentAmmo--;
        if (rand.nextInt(100) < finalAcc) {
            int dmg = (rand.nextInt((user.activeWeapon.type.maxDmg - user.activeWeapon.type.minDmg) + 1) + user.activeWeapon.type.minDmg) * 2;
            target.takeDamage(dmg);
            System.out.println("   [!] SLAP round dealt " + dmg + " damage!");
        } else {
            System.out.println("   [ ] The round missed and the weapon jammed!");
            user.turnsToWait = 1;
            user.currentAction = "Clearing Jam";
        }
    }
}