package Juego.Weapons;

import Juego.WeaponClass;
import Juego.Abilities.DanisNachos;
import Juego.Abilities.ZoomIn;

public class Astra extends Weapon {
    public Astra () {
        super("Astra Terminator", WeaponClass.SIDEARM, 6);
        this.ability1 = new DanisNachos();
        this.ability2 = new ZoomIn();
    }
}