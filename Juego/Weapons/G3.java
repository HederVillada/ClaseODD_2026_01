package Juego.Weapons;

import Juego.WeaponClass;
import Juego.Abilities.ZoomIn;
import Juego.Abilities.RestBrandBeer;

public class G3 extends Weapon {
    public G3() {
        super("H&K G3 First Pattern 1959", WeaponClass.BATTLE_RIFLE, 20);
        this.ability1 = new ZoomIn();
        this.ability2 = new RestBrandBeer();
    }
}