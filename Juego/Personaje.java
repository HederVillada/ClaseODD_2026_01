package Juego;

import Juego.Abilities.*;
import Juego.Weapons.*;
import java.util.Random;

public class Personaje {
    public String nombre;
    public int salud = 100; 
    public int accuracy = 100; 
    
    public Weapon primary;
    public Weapon secondary;
    public Weapon activeWeapon;
    
    public int turnsToWait = 0; 
    public String currentAction = ""; 

    // Flags for special abilities
    public boolean hasSabotLoaded = false;
    public boolean hasBayonetFixed = false;

    public Personaje(String nombre, Weapon primary, Weapon secondary) {
        this.nombre = nombre;
        this.primary = primary;
        this.secondary = secondary;
        this.activeWeapon = primary;
    }

    public boolean isBusy() {
        return turnsToWait > 0;
    }

    public void takeDamage(int dmg) {
        this.salud = Math.max(0, this.salud - dmg);
    }

    public void shoot(Personaje target) {
        if (hasBayonetFixed) {
            System.out.println(">>> " + nombre + " LUNGES WITH THE BAYONET!");
            target.takeDamage(30);
            hasBayonetFixed = false;
            return; 
        }

        Random rand = new Random();
        double classMult = activeWeapon.getType().accMultiplier;
        
        if (hasSabotLoaded) {
            classMult = 1.0; 
            System.out.println(">> [SLUG EFFECT] Shotgun spread negated!");
            hasSabotLoaded = false;
        }

        double effectiveAcc = this.accuracy * classMult;
        
        if (rand.nextInt(100) < effectiveAcc) {
            int min = activeWeapon.getType().minDmg;
            int max = activeWeapon.getType().maxDmg;
            int dmg = rand.nextInt((max - min) + 1) + min;
            target.takeDamage(dmg);
            System.out.println(">>> HIT! " + activeWeapon.getName() + " dealt " + dmg + " damage.");
        } else {
            System.out.println(">>> MISS!");
        }
        activeWeapon.currentAmmo--;
    }

    public void swapWeapon() {
        activeWeapon = (activeWeapon == primary) ? secondary : primary;
        turnsToWait = 1; 
        currentAction = "Swapping weapons";
    }

    public void startReload() {
        boolean hasNYR = (activeWeapon.getAbility1() instanceof NewYorkReload || 
                          activeWeapon.getAbility2() instanceof NewYorkReload);

        if (hasNYR) {
            turnsToWait = 1;
            currentAction = "QUICK RELOAD";
        } else {
            turnsToWait = activeWeapon.getType().reloadTurns;
            currentAction = "Reloading";
        }
        activeWeapon.reload();
    }
}