package Juego.Abilities;
import Juego.Personaje;
import java.util.Random;

public class MolotovThrow implements Ability {
    public String getName() { return "Molotov Throw"; }
    public boolean isPassive() { return false; }
    public double getAccuracyMult() { return 0.5; }

    public void execute(Personaje user, Personaje target) {
        System.out.println(">> " + user.getNombre() + " lights and tosses a Molotov!");
        Random rand = new Random();
        double finalAcc = user.accuracy * user.activeWeapon.type.accMultiplier * 0.5;

        if (rand.nextInt(100) < finalAcc) {
            int fireDamage = 15 + rand.nextInt(10);
            target.takeDamage(fireDamage);
            System.out.println("   [!] Target is engulfed in flames! " + fireDamage + " damage.");
        } else {
            System.out.println("   [ ] The bottle shatters uselessly.");
        }
    }
}