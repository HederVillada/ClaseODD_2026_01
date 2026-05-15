package Juego;

import Juego.Weapons.*;
import java.util.Scanner;
import java.util.Random;

public class JuegoTactico {
    private Personaje jugador1;
    private Personaje jugador2;
    private static Scanner sc = new Scanner(System.in);
    private Random rand = new Random();

    public JuegoTactico (Personaje p1, Personaje p2) {
        this.jugador1 = p1;
        this.jugador2 = p2;
    }

    // aqui esta el metodo que inicia la pelea, se encarga de ejecutar el loop del juego, y de determinar el ganador al final de la pelea
    public void iniciarPelea() {
        System.out.println("\nENGAGEMENT INITIATED: " + jugador1.getNombre() + " VS " + jugador2.getNombre());

        // loop basico del juego, se ejecuta mientras ambos personajes esten vivos, y se alternan los turnos entre el jugador y la IA
        while (jugador1.estaVivo() && jugador2.estaVivo()) {
            ejecutarTurno(jugador1, jugador2, false); // turno jugador
            if (!jugador2.estaVivo()) break;
            
            ejecutarTurno(jugador2, jugador1, true);  // turno IA
        }

        // determina el ganador al final de la pelea, dependiendo de quien este vivo, o muerto, respectivamente
        System.out.println("\n" + (jugador1.estaVivo() ? ">>> " + jugador1.getNombre() + " SECURED THE SECTOR." : ">>> " + jugador1.getNombre() + " IS KIA."));
    }

    private void ejecutarTurno(Personaje p, Personaje target, boolean isAI) {
        //el UI para cada turno
        System.out.println("\n====================================================");
        System.out.println("--- " + p.getNombre() + " | HP: " + p.getPuntosDeVida() + " | ACC: " + p.accuracy + "% ---");
        System.out.println("WEAPON: " + p.activeWeapon.getName() + " [" + p.activeWeapon.currentAmmo + "/" + p.activeWeapon.maxAmmo + "]");
        
        if (p.isBusy()) {
            System.out.println(">> " + p.getNombre() + " is busy: " + p.currentAction + " (" + p.turnsToWait + " turns left)");
            p.turnsToWait--;
            return;
        }

        if (!isAI) {
            handlePlayerInput(p, target);
        } else {
            handleOpponentAI(p, target);
        }
        
        // POST-ACTION STATUS UPDATE
        System.out.println("STATUS: " + target.getNombre() + " HP is now " + target.getPuntosDeVida());
        System.out.println("====================================================");
    }

    private void handlePlayerInput(Personaje p, Personaje target) {
        System.out.println("1. SHOOT | 2. SWAP | 3. RELOAD | 4. " + p.activeWeapon.getAbility1().getName() + " | 5. " + p.activeWeapon.getAbility2().getName()); 
        String move = sc.nextLine();
        switch (move) {
            case "1": 
                System.out.println(">> " + p.getNombre() + " pulls the trigger!");
                p.shoot(target); 
                break;
            case "2": 
                p.swapWeapon(); 
                break;
            case "3": 
                p.startReload(); 
                break;
            case "4": 
                System.out.println(">> " + p.getNombre() + " ACTIVATES: " + p.activeWeapon.getAbility1().getName());
                p.activeWeapon.getAbility1().execute(p, target); 
                break;
            case "5": 
                System.out.println(">> " + p.getNombre() + " ACTIVATES: " + p.activeWeapon.getAbility2().getName());
                p.activeWeapon.getAbility2().execute(p, target); 
                break;
            default: 
                System.out.println(">> [!] INVALID INPUT: " + move + ". YOU FROZE IN COMBAT!");
        }
    }

    private void handleOpponentAI(Personaje p, Personaje target) {
        int decision = rand.nextInt(100);

        if (p.activeWeapon.currentAmmo <= 0) {
            System.out.println(">> " + p.getNombre() + " SELECTED: RELOAD");
            p.startReload();
        } 
        else if (decision < 20) {
            System.out.println(">> " + p.getNombre() + " SELECTED: " + p.activeWeapon.getAbility1().getName());
            p.activeWeapon.getAbility1().execute(p, target);
        } 
        else if (decision < 40) {
            System.out.println(">> " + p.getNombre() + " SELECTED: " + p.activeWeapon.getAbility2().getName());
            p.activeWeapon.getAbility2().execute(p, target);
        }
        else if (decision < 50) {
            System.out.println(">> " + p.getNombre() + " SELECTED: SWAP WEAPON");
            p.swapWeapon();
        }
        else {
            System.out.println(">> " + p.getNombre() + " SELECTED: SHOOT");
            p.shoot(target);
        }
    }

    private static Weapon selectWeaponMenu() {
        while (true) {
            System.out.println("\n--- CATEGORIES ---");
            System.out.println("1. BATTLE RIFLES | 2. ASSAULT RIFLES | 3. SHOTGUNS | 4. SIDEARMS");
            System.out.print("SELECT: ");
            String cat = sc.nextLine();
            
            if (cat.equals("1")) {
                while (true) {
                    System.out.println("\n[BR] 1. G3 | 2. FAL 50.00 | 3. Browning BLR | 0. BACK");
                    String c = sc.nextLine();
                    if (c.equals("1")) return new G3();
                    if (c.equals("2")) return new FAL50();
                    if (c.equals("3")) return new BrowningBLR();
                    if (c.equals("0")) break;
                    System.out.println(">> [!] ERROR: INVALID SELECTION. TRY AGAIN.");
                }
            } else if (cat.equals("2")) {
                while (true) {
                    System.out.println("\n[AR] 1. AKM | 2. AK-74M | 3. FAL 7.62 (Soviet) | 4. M4A1 Carbine | 5. M231 Port Gun | 0. BACK");
                    String c = sc.nextLine();
                    if (c.equals("1")) return new AKM();
                    if (c.equals("2")) return new AK74M();
                    if (c.equals("3")) return new FAL762();
                    if (c.equals("4")) return new M4A1();
                    if (c.equals("5")) return new M231();
                    if (c.equals("0")) break;
                    System.out.println(">> [!] ERROR: INVALID SELECTION. TRY AGAIN.");
                }
            } else if (cat.equals("3")) {
                while (true) {
                    System.out.println("\n[SG] 1. Auto-5 | 2. OverUnder | 3. Trench Gun | 4. Double Barrel | 0. BACK");
                    String c = sc.nextLine();
                    if (c.equals("1")) return new Auto5();
                    if (c.equals("2")) return new OverUnder();
                    if (c.equals("3")) return new TrenchGun();
                    if (c.equals("4")) return new DoubleBarrel();
                    if (c.equals("0")) break;
                    System.out.println(">> [!] ERROR: INVALID SELECTION. TRY AGAIN.");
                }
            } else if (cat.equals("4")) {
                while (true) {
                    System.out.println("\n[SIDE] 1. MAC-10 | 2. M60 | 3. Bodyguard 49 | 4. Makarov | 5. Beretta 92 | 6. Astra Revolver| 0. BACK");
                    String c = sc.nextLine();
                    if (c.equals("1")) return new MAC10();
                    if (c.equals("2")) return new M60();
                    if (c.equals("3")) return new Bodyguard49();
                    if (c.equals("4")) return new Makarov();
                    if (c.equals("5")) return new Beretta();
                    if (c.equals("6")) return new Astra();
                    if (c.equals("0")) break;
                    System.out.println(">> [!] ERROR: INVALID SELECTION. TRY AGAIN.");
                }
            } else {
                System.out.println(">> [!] ERROR: CATEGORY '" + cat + "' DOES NOT EXIST.");
            }
        }
    }

    private static Weapon getRandomWeapon() {
        Random r = new Random();
        Weapon[] pool = {new AKM(), new G3(), new M60(), new Auto5(), new MAC10(), new Makarov(), new Beretta(), new Astra(), new FAL762(), new FAL50(), new Bodyguard49(), new OverUnder(), new TrenchGun(), new BrowningBLR()};
        return pool[r.nextInt(pool.length)];
    }

    public static void main(String[] args) {
        System.out.println("★ TACTICAL SIMULATOR 2026 ★");
        
        System.out.print("ENTER OPERATOR NAME: ");
        String p1Name = sc.nextLine();
        System.out.println("\n--- PLAYER 1: SELECT PRIMARY ---");
        Weapon p1P = selectWeaponMenu();
        System.out.println("\n--- PLAYER 1: SELECT SECONDARY ---");
        Weapon p1S = selectWeaponMenu();

        System.out.print("\nENTER OPPONENT NAME: ");
        String p2Name = sc.nextLine(); 
        
        System.out.println("\n--- CONFIGURE OPPONENT LOADOUT ---");
        System.out.println("1. MANUAL SELECTION | 2. RANDOMIZER");
        String setup = sc.nextLine();
        
        Weapon p2P, p2S;
        if (setup.equals("1")) {
            System.out.println("\n--- OPPONENT: SELECT PRIMARY ---");
            p2P = selectWeaponMenu();
            System.out.println("\n--- OPPONENT: SELECT SECONDARY ---");
            p2S = selectWeaponMenu();
        } else {
            p2P = getRandomWeapon();
            p2S = getRandomWeapon();
            System.out.println(">> Rerolling weapons for " + p2Name + "...");
        }

        Personaje p1 = new Personaje(p1Name, p1P, p1S);
        Personaje p2 = new Personaje(p2Name, p2P, p2S);

        JuegoTactico juego = new JuegoTactico(p1, p2);
        juego.iniciarPelea();
    }
}