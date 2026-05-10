package Juego.Weapons;

import Juego.WeaponClass;
import Juego.Abilities.Trickshot;
import Juego.Abilities.ZoomIn;

public class G3 extends Weapon {
    public G3() {
        super("H&K G3 First Pattern 1959", WeaponClass.BATTLE_RIFLE, 20);
        this.ability1 = new Trickshot();
        this.ability2 = new ZoomIn();
    }
}