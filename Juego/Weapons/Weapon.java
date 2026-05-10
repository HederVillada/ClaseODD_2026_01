package Juego.Weapons;

import Juego.WeaponClass;
import Juego.Abilities.Ability;

public abstract class Weapon {
    // CHANGED TO PUBLIC
    public String name;
    public WeaponClass type;
    public int currentAmmo;
    public int maxAmmo;
    
    public Ability ability1;
    public Ability ability2;

    public Weapon(String name, WeaponClass type, int maxAmmo) {
        this.name = name;
        this.type = type;
        this.maxAmmo = maxAmmo;
        this.currentAmmo = maxAmmo;
    }

    public Ability getAbility1() { return ability1; }
    public Ability getAbility2() { return ability2; }
    public String getName() { return name; }
    public WeaponClass getType() { return type; }
    public int getCurrentAmmo() { return currentAmmo; }
    public int getMaxAmmo() { return maxAmmo; }
    
    public void reload() { this.currentAmmo = this.maxAmmo; }
}