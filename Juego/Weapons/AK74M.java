package Juego.Weapons;

import Juego.WeaponClass;
import Juego.Abilities.SmokeBomb;
import Juego.Abilities.Trickshot;

public class AK74M extends Weapon {
    public AK74M() {
        // mas capacidad que FAL50 porque acepta AK mags
        super("Avtomat Kalashnikova 74M", WeaponClass.ASSAULT_RIFLE, 30);
        this.ability1 = new SmokeBomb();
        this.ability2 = new Trickshot();
    }
}