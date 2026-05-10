package Juego.Abilities;

public interface Ability {
    String getName();
    boolean isPassive();
    double getAccuracyMult(); // New: 0.33 for Low, 0.5 for Medium, 1.0 for High
    void execute(Personaje user, Personaje target);
}