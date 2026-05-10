package Juego.Weapons;

import Juego.WeaponClass;
import Juego.Abilities.SuppressiveFire;
import Juego.Abilities.ZoomIn;

public class M60 extends Weapon {
    public M60() {
        super("Machine Gun, Caliber 7.62 mm, M60", WeaponClass.SIDEARM, 50);
        this.ability1 = new SuppressiveFire();
        this.ability2 = new ZoomIn();
    }
}