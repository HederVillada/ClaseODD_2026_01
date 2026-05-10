package Juego.Weapons;

import Juego.WeaponClass;
import Juego.Abilities.Ability;

public abstract class Weapon {
    //esto antes era privado, y me dio problemas, así que fue cambiado a publico
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

//este sistema es modular. Para ingresar un arma, simplemente copia y pega cualquiera de las armas y cambia sus opciones en el Main