package Juego.Abilities;
import Juego.Personaje;

public class BlastOff implements Ability {
    public String getName() { return "Blast Off"; }
    public boolean isPassive() { return false; }
    public double getAccuracyMult() { return 1.0; }

    public void execute(Personaje user, Personaje target) {
        System.out.println(">> " + user.nombre + " fires a point-blank blast!");
        target.takeDamage(20);
        target.accuracy -= 5;
    }
}