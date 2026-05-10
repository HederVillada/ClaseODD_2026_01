package Juego.Abilities;

import Juego.Personaje;

public class NewYorkReload implements Ability {
    @Override
    public String getName() { return "New York Reload"; }

    @Override
    public boolean isPassive() { return true; }

    @Override
    public double getAccuracyMult() { return 1.0; }

    @Override
    public void execute(Personaje user, Personaje target) {
        // Your requested error message
        System.out.println("ERROR: THIS ABILITY IS PASSIVE.");
    }
}