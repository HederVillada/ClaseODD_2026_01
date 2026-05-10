package Juego.Weapons;

import Juego.WeaponClass;
import Juego.Abilities.ButtstockBash;
import Juego.Abilities.DoubleTap;

public class BrowningBLR extends Weapon {
    public BrowningBLR () {
        super("Browning BLR .450 Marlin", WeaponClass.BATTLE_RIFLE, 3);
        this.ability1 = new DoubleTap();
        this.ability2 = new ButtstockBash();
    }
} 
    
