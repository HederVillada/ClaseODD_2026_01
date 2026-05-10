package Juego.Abilities;
import Juego.Personaje;
import java.util.Random;

public class ShotgunSpin implements Ability {
    public String getName() { return "Shotgun Spin"; }
    public boolean isPassive() { return false; }
    public double getAccuracyMult() { return 0.33; }

    public void execute(Personaje user, Personaje target) {
        if (user.activeWeapon.currentAmmo < 3) {
            System.out.println("! Not enough shells for a spin.");
            return;
        }
        System.out.println(">> " + user.nombre + " spins the shotgun, firing rapidly!");
        Random rand = new Random();
        double finalAcc = user.accuracy * user.activeWeapon.type.accMultiplier * 0.33;

        for (int i = 0; i < 3; i++) {
            user.activeWeapon.currentAmmo--;
            if (rand.nextInt(100) < finalAcc) {
                int dmg = rand.nextInt(4) + 25; // 25-28 damage
                target.takeDamage(dmg);
                System.out.println("   BOOM! " + dmg + " damage.");
            } else {
                System.out.println("   *click* (Miss)");
            }
        }
    }
}