package Juego.Weapons;
public class AKM extends Weapon {
    public AKM() {
        // mas capacidad que FAL50 porque acepta AK mags
        super("Avtomat Kalashnikova M", WeaponClass.ASSAULT_RIFLE, 30);
        this.ability1 = new SmokeBomb();
        this.ability2 = new DoubleTap();
    }
}