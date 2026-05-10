package Juego.Weapons;

public class MAC10 extends Weapon {
    public MAC10() {
        super("Ingram MAC-10", WeaponClass.SIDEARM, 30);
        this.ability1 = new SuppressiveFire();
        this.ability2 = new MolotovThrow();
    }
}