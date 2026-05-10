package Juego;

import Juego.Abilities.*;
import Juego.Weapons.*;
import java.util.Random;

public class Personaje {
    private String nombre; // Encapsulated [cite: 24]
    private int salud = 100; // Encapsulated [cite: 25]
    public int accuracy = 100; 
    
    public Weapon primary;
    public Weapon secondary;
    public Weapon activeWeapon;
    
    public int turnsToWait = 0; 
    public String currentAction = ""; 

    public boolean hasSabotLoaded = false;
    public boolean hasBayonetFixed = false;

    public Personaje(String nombre, Weapon primary, Weapon secondary) {
        this.nombre = nombre;
        this.primary = primary;
        this.secondary = secondary;
        this.activeWeapon = primary;
    }

    // MANDATORY METHOD [cite: 13, 73]
    public boolean estaVivo() {
        return this.salud > 0;
    }

    // MANDATORY GETTERS [cite: 14, 15]
    public String getNombre() { return this.nombre; }
    public int getPuntosDeVida() { return this.salud; }

    public void takeDamage(int dmg) {
        this.salud = Math.max(0, this.salud - dmg);
    }

    public boolean isBusy() {
        return turnsToWait > 0;
    }

    public void shoot(Personaje target) {
        if (hasBayonetFixed) {
            System.out.println(">>> " + nombre + " SLASHES WITH THE BAYONET!");
            target.takeDamage(30);
            hasBayonetFixed = false;
            return; 
        }

        Random rand = new Random();
        double classMult = activeWeapon.getType().accMultiplier;
        
        if (hasSabotLoaded) {
            classMult = 1.0; 
            hasSabotLoaded = false;
        }

        double effectiveAcc = this.accuracy * classMult;
        
        if (rand.nextInt(100) < effectiveAcc) {
            int min = activeWeapon.getType().minDmg;
            int max = activeWeapon.getType().maxDmg;
            int dmg = rand.nextInt((max - min) + 1) + min;
            target.takeDamage(dmg);
            System.out.println(">>> HIT! " + dmg + " damage dealt.");
        } else {
            System.out.println(">>> MISS!");
        }
        activeWeapon.currentAmmo--;
    }

    public void swapWeapon() {
        activeWeapon = (activeWeapon == primary) ? secondary : primary;
        turnsToWait = 1; 
        currentAction = "Swapping";
    }

    public void startReload() {
        boolean hasNYR = (activeWeapon.getAbility1() instanceof NewYorkReload || 
                          activeWeapon.getAbility2() instanceof NewYorkReload);
        turnsToWait = hasNYR ? 1 : activeWeapon.getType().reloadTurns; //Me toco que colocar new york reload en personaje
        //para mi no es tan intuitivo que una clase sola de nyr, pero funciona bien así que no lo toco
        currentAction = "Reloading";
        activeWeapon.reload();
    }
}