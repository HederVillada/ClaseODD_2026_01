package Juego.Weapons;

import Juego.WeaponClass;
import Juego.Abilities.MagDump;
import Juego.Abilities.FortuneCookies;

public class M231 extends Weapon {
    public M231() {
        super("M231 Firing Port Weapon", WeaponClass.ASSAULT_RIFLE, 30);
        this.ability1 = new MagDump();
        this.ability2 = new FortuneCookies();
    }
}