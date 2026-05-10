package Juego.Weapons;

import Juego.WeaponClass;
import Juego.Abilities.MagDump;
import Juego.Abilities.NewYorkReload;

public class Makarov extends Weapon {
    public Makarov() {
        super("PM Makarov", WeaponClass.SIDEARM, 8);
        this.ability1 = new MagDump();
        this.ability2 = new NewYorkReload();
    }
}