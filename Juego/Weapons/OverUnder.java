package Juego.Weapons;

import Juego.WeaponClass;
import Juego.Abilities.BlastOff;
import Juego.Abilities.ButtstockBash;

public class OverUnder extends Weapon {
    public OverUnder() {
        super("Over-Under Shotgun", WeaponClass.SHOTGUN, 2);
        this.ability1 = new BlastOff();
        this.ability2 = new ButtstockBash();
    }
}