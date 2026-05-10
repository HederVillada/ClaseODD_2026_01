package Juego.Weapons;

import Juego.WeaponClass;
import Juego.Abilities.SabotSlug;
import Juego.Abilities.ShotgunSpin;

public class Auto5 extends Weapon {
    public Auto5() {
        super("Sawn-off Browning Auto-5", WeaponClass.SHOTGUN, 3);
        this.ability1 = new SabotSlug();
        this.ability2 = new ShotgunSpin();
    }
}