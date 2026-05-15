package Juego.Weapons;

import Juego.WeaponClass;
import Juego.Abilities.MagDump;
import Juego.Abilities.LesliesSpecialRoast;

public class TrenchGun extends Weapon {
    public TrenchGun() {
        super("Winchester Model 1897", WeaponClass.SHOTGUN, 5);
        this.ability1 = new MagDump();
        this.ability2 = new LesliesSpecialRoast();
    }
}