package Juego.Weapons;

import Juego.WeaponClass;
import Juego.Abilities.MolotovThrow;
import Juego.Abilities.SuppressiveFire;

public class MAC10 extends Weapon {
    public MAC10() {
        super("Ingram MAC-10", WeaponClass.SIDEARM, 30);
        this.ability1 = new SuppressiveFire();
        this.ability2 = new MolotovThrow();
    }
}