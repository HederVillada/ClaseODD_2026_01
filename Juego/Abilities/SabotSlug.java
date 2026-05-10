package Juego.Abilities;
import Juego.Personaje;

public class SabotSlug implements Ability {
    public String getName() { return "12 Gauge Sabot Slug"; }
    public boolean isPassive() { return false; }
    public double getAccuracyMult() { return 1.0; }

    public void execute(Personaje user, Personaje target) {
        if (user.activeWeapon.currentAmmo >= user.activeWeapon.maxAmmo) {
            System.out.println("! Shotgun is already full.");
            return;
        }
        System.out.println(">> " + user.getNombre() + " is hand-loading a Sabot Slug...");
        user.turnsToWait = 1;
        user.currentAction = "Loading Slug";
        user.activeWeapon.currentAmmo++;
        user.hasSabotLoaded = true;
    }
}