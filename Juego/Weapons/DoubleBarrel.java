package Juego.Weapons;

import Juego.WeaponClass;
import Juego.Abilities.DoubleTap;
import Juego.Abilities.NewYorkReload;

public class DoubleBarrel extends Weapon {
    public DoubleBarrel() {
        super("Winchester Model 24 Side by Side", WeaponClass.SHOTGUN, 2);
        this.ability1 = new DoubleTap();
        this.ability2 = new NewYorkReload();
    }
}
