package Juego.Weapons;
public class FAL5000 extends Weapon {
    public FAL5000() {
        super("FN FAL 50.00 Battle Rifle", WeaponClass.ASSAULT_RIFLE, 20);
        this.ability1 = new DoubleTap();
        this.ability2 = new FixBayonet();
    }
}