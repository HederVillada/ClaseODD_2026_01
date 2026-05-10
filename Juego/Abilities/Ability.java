package Juego.Abilities;

import Juego.Personaje;

public interface Ability {
    String getName();
    boolean isPassive();
    double getAccuracyMult();
    void execute(Personaje user, Personaje target); // Make sure this matches!
}