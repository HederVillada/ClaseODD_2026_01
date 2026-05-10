package Juego.Weapons;

public class OverUnder extends Weapon {
    public OverUnder() {
        super("Over-Under Shotgun", WeaponClass.SHOTGUN, 2);
        this.ability1 = new BlastOff();
        this.ability2 = new ButtstockBash();
    }
}