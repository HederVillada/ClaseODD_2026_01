package Juego.Abilities;

import Juego.Personaje;

public class LesliesSpecialRoast implements Ability {
    private boolean used = false; // Internal flag for the "Once per firearm" rule

    public String getName() { return "Leslie's Special Roast"; }
    public boolean isPassive() { return false; }
    public double getAccuracyMult() { return 1.0; }

    public void execute(Personaje user, Personaje target) {
        if (used) {
            System.out.println(">> " + user.getNombre() + " reaches for more roast, but the pot is empty!");
            return;
        }
        
        System.out.println(">> " + user.getNombre() + " consumes Leslie's Special Roast. A culinary masterpiece!");
        user.takeDamage(-50);
        used = true; // ya no se puede usar hasta que cambie de arma si es que eso es lo que se supone que hace esta habilidad
        System.out.println("   MASSIVE HEAL: +50 HP. The pot is now empty.");
    }
} //Remember, this ability can only be used once per firearm. If the character changes their active weapon, the "used" flag should reset to allow for another use of the roast.
