package Juego.Weapons;
public class FAL762 extends Weapon {
    public FAL762() {
        // mas capacidad que FAL50 porque acepta AK mags
        super("FAL 7.62x39 Soviet", WeaponClass.ASSAULT_RIFLE, 30);
        this.ability1 = new ButtstockBash();
        this.ability2 = new SuppressiveFire();
    }
}