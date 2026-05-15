package Juego.Abilities;

import Juego.Personaje;
import java.util.Random;

public class FortuneCookies implements Ability {
    public String getName() { return "Arthur Rose's Fortune Cookies"; }
    public boolean isPassive() { return false; }
    public double getAccuracyMult() { return 1.0; }

    public void execute(Personaje user, Personaje target) {
        System.out.println(">> " + user.getNombre() + " cracks open a Fortune Cookie...");
        Random rand = new Random();
        if (rand.nextBoolean()) {
            user.takeDamage(-25);
            System.out.println("   'You will find great health.' Restored 25 HP!");
        } else {
            System.out.println("   'Your luck has run out.' No healing effect.");
        }
    }
}
