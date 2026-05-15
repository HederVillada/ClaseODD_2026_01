package Juego.Weapons;

import Juego.WeaponClass;
import Juego.Abilities.FortuneCookies;
import Juego.Abilities.MolotovThrow;

public class Beretta extends Weapon {
    public Beretta () {
        super("Beretta 92F", WeaponClass.SIDEARM, 6);
        this.ability1 = new FortuneCookies();
        this.ability2 = new MolotovThrow();
    }
}