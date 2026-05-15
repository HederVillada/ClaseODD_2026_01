package Juego.Abilities;

import Juego.Personaje;

public class RestBrandBeer implements Ability {
    public String getName() { return "Rest Brand Beer"; }
    public boolean isPassive() { return false; }
    public double getAccuracyMult() { return 1.0; }

    public void execute(Personaje user, Personaje target) {
        if (user.accuracy + 10 > 100) {
            System.out.println(">> " + user.getNombre() + " drinks a Rest Brand Beer. It's tasteless...");
            user.takeDamage(-7);
            System.out.println("   Despite tasting like fresh cat's piss, it restored 7 HP.");
        } else {
            System.out.println(">> " + user.getNombre() + " drinks a Rest Brand Beer. Refreshing!");
            user.takeDamage(-5);
            user.accuracy += 10;
            System.out.println("   You feel like you can hit shots better now. Restored 5 HP and increased accuracy by 10%.");
        }
    }
}
