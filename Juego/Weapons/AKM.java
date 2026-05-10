package Juego.Weapons;

import Juego.WeaponClass;
import Juego.Abilities.DoubleTap;
import Juego.Abilities.SmokeBomb;

public class AKM extends Weapon {
    public AKM() {
        // mas capacidad que FAL50 porque acepta AK mags
        super("Avtomat Kalashnikova M", WeaponClass.ASSAULT_RIFLE, 30);
        this.ability1 = new SmokeBomb();
        this.ability2 = new DoubleTap();
    }
}