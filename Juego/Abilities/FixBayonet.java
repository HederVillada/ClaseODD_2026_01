package Juego.Abilities;
import Juego.Personaje;

public class FixBayonet implements Ability {
    public String getName() { return "Fix Bayonet"; }
    public boolean isPassive() { return false; }
    public double getAccuracyMult() { return 1.0; }

    public void execute(Personaje user, Personaje target) {
        System.out.println(">> " + user.nombre + " clicks a bayonet onto the muzzle!");
        user.turnsToWait = 1;
        user.currentAction = "Fixing Bayonet";
        user.hasBayonetFixed = true;
    }
}