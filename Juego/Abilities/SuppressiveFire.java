package Juego.Abilities;

import Juego.Personaje;
import java.util.Random;

public class SuppressiveFire implements Ability {
    @Override
    public String getName() { return "Suppressive Fire"; }
    @Override
    public boolean isPassive() { return false; }
    @Override
    public double getAccuracyMult() { return 0.25; }

    @Override
    public void execute(Personaje user, Personaje target) {
        int shots = Math.min(user.activeWeapon.currentAmmo, 5);
        if (shots < 1) return;

        System.out.println(">> " + user.getNombre() + " lays down heavy cover fire!");
        Random rand = new Random();
        double finalAcc = user.accuracy * user.activeWeapon.getType().accMultiplier * 0.25;

        for (int i = 1; i <= shots; i++) {
            user.activeWeapon.currentAmmo--;
            if (rand.nextInt(100) < finalAcc) {
                int dmg = user.activeWeapon.getType().minDmg;
                target.takeDamage(dmg);
                System.out.println("   [!] Round #" + i + " CONNECTED! Stray hit for " + dmg + " damage.");
            } else {
                System.out.println("   [ ] Round #" + i + " missed.");
            }
        }
        
        //debuff se da no importa si acierta o no, el objetivo queda con accuracy reducida
        //porque pues, suppressive fire literalmente significa "fuego de supresión" 
        //el objetivo queda tan acobardado que su precisión se reduce
        target.accuracy -= 10;
        System.out.println("   [STATUS] " + target.getNombre() + " is PINNED! (-10% Accuracy)");
    }
}