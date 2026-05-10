package Juego.Abilities;
import Juego.Personaje;

public class ButtstockBash implements Ability {
    public String getName() { return "Buttstock Bash"; }
    public boolean isPassive() { return false; }
    public double getAccuracyMult() { return 1.0; } // Guaranteed hit at close range

    public void execute(Personaje user, Personaje target) {
        System.out.println(">> " + user.getNombre() + " strikes with the butt of the weapon!");
        target.takeDamage(10);
        target.accuracy -= 15;
        // Logic for "2 turns" would need a counter in Personaje, 
        // for now, we apply the debuff.
        System.out.println("   Target dazed! Accuracy reduced.");
    }
}