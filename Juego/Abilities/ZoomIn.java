package Juego.Abilities;

import Juego.Personaje;

public class ZoomIn implements Ability {
    @Override
    public String getName() { 
        return "Zoom In"; 
    }

    @Override
    public boolean isPassive() { 
        return false; 
    }

    @Override
    public double getAccuracyMult() { 
        return 1.0; // This is a self-buff, not a shot modifier
    }

    @Override
    public void execute(Personaje user, Personaje target) {
        // The rule: +15 Accuracy, but fail if it exceeds 100
        if (user.accuracy + 15 <= 100) {
            user.accuracy += 15;
            System.out.println(">> " + user.nombre + " stabilizes their breathing and adjusts the optics.");
            System.out.println("   [BUFF] Accuracy increased to " + user.accuracy + "%.");
        } else {
            System.out.println(">> " + user.nombre + " attempts to zoom in, but they already have a perfect sight picture.");
            System.out.println("   [FAIL] Accuracy is already at maximum (100%).");
        }
    }
}