package Juego.Abilities;
import Juego.Personaje;

public class SmokeBomb implements Ability {
    public String getName() { return "Smoke Bomb"; }
    public boolean isPassive() { return false; }
    public double getAccuracyMult() { return 1.0; }

    public void execute(Personaje user, Personaje target) {
        System.out.println(">> " + user.nombre + " deploys smoke! Everything is obscured.");
        user.accuracy -= 15;
        target.accuracy -= 30;
    }
}