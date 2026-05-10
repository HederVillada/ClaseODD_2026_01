package Juego;

public enum WeaponClass {
    // Multiplier, MinDmg, MaxDmg, ReloadTurns
    BATTLE_RIFLE(1.0, 25, 30, 4), 
    ASSAULT_RIFLE(0.83, 23, 28, 3), 
    SIDEARM(0.66, 10, 15, 2), 
    SHOTGUN(0.52, 10, 30, 2);

    // THESE MUST BE PUBLIC
    public final double accMultiplier;
    public final int minDmg, maxDmg, reloadTurns;

    WeaponClass(double accMultiplier, int min, int max, int reload) {
        this.accMultiplier = accMultiplier;
        this.minDmg = min;
        this.maxDmg = max;
        this.reloadTurns = reload;
    }
}