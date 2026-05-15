package Juego.Weapons;

import Juego.WeaponClass;
import Juego.Abilities.SmokeBomb;
import Juego.Abilities.ButtstockBash;

public class M4A1 extends Weapon {
    public M4A1() {
        super("M4A1 Carbine", WeaponClass.ASSAULT_RIFLE, 30);
        this.ability1 = new ButtstockBash();
        this.ability2 = new SmokeBomb();
    }
}