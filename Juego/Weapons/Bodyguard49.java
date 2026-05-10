package Juego.Weapons;

public class Bodyguard49 extends Weapon {
    public Bodyguard49() {
        super("S&W 49 Bodyguard", WeaponClass.SIDEARM, 5);
        this.ability1 = new ZoomIn();
        this.ability2 = new DoubleTap();
    }
}