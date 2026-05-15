package Juego.Abilities;
import Juego.Personaje;

public class DanisNachos implements Ability {
    public String getName() { return "Dani's Nachos Especiales"; }
    public boolean isPassive() { return false; }
    public double getAccuracyMult() { return 1.0; }

    public void execute(Personaje user, Personaje target) {
        System.out.println(">> " + user.getNombre() + " sits down for a snack. Those are some good nachos.");
        user.takeDamage(-10); // Negative damage = Healing
        System.out.println("   Restored 10 HP. Current HP: " + user.getPuntosDeVida());
    }
}