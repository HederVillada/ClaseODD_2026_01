package Juego;

import Juego.Abilities.*;
import Juego.Weapons.*;
import java.util.Random;

public class Personaje {
    public boolean hasSabotLoaded = false;
    public boolean hasBayonetFixed = false;
    public String nombre;
    public int salud = 100; 
    public int accuracy = 100; 
    
    public Weapon primary;
    public Weapon secondary;
    public Weapon activeWeapon;
    
    public int turnsToWait = 0; 
    public String currentAction = ""; 

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
        Random rand = new Random();
        double effectiveAcc = this.accuracy * activeWeapon.type.accMultiplier;
        
        if (rand.nextInt(100) < effectiveAcc) {
            int dmg = rand.nextInt((activeWeapon.type.maxDmg - activeWeapon.type.minDmg) + 1) 
                       + activeWeapon.type.minDmg;
            target.takeDamage(dmg);
            System.out.println(">>> HIT! " + activeWeapon.name + " dealt " + dmg + " damage.");
        } else {
            System.out.println(">>> MISS! Shot deviated from target.");
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
            currentAction = "QUICK RELOAD (Passive)";
        } else {
            turnsToWait = activeWeapon.type.reloadTurns;
            currentAction = "Reloading " + activeWeapon.name;
        }
        activeWeapon.reload();
    }
}