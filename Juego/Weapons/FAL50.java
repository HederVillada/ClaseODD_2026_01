package Juego.Weapons;

import Juego.WeaponClass;
import Juego.Abilities.DoubleTap;
import Juego.Abilities.FixBayonet;

public class FAL50 extends Weapon {
    public FAL50() {
        super("FN FAL 50.00 Battle Rifle", WeaponClass.ASSAULT_RIFLE, 20);
        this.ability1 = new DoubleTap();
        this.ability2 = new FixBayonet();
    }
}