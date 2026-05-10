package Juego;

import Juego.Weapons.*;
import java.util.Scanner;
import java.util.Random;

public class JuegoTactico {
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {
        System.out.println("★ TACTICAL SIMULATOR 2026: ARMED CONTACT ★");
        
        // PLAYER SETUP
        System.out.print("ENTER OPERATOR NAME: ");
        String name = sc.nextLine();
        System.out.println("\n--- SELECT PRIMARY ---");
        Weapon p1 = selectWeaponMenu();
        System.out.println("\n--- SELECT SECONDARY ---");
        Weapon p2 = selectWeaponMenu();
        Personaje player = new Personaje(name, p1, p2);

        // OPPONENT SETUP
        System.out.println("\n--- OPPONENT CONFIGURATION ---");
        System.out.println("1. CUSTOM OPPONENT | 2. RANDOMIZED INSURGENT");
        String opChoice = sc.nextLine();
        Personaje cpu;
        
        if (opChoice.equals("1")) {
            System.out.print("ENTER OPPONENT NAME: ");
            String opName = sc.nextLine();
            System.out.println("\n--- SELECT OPPONENT PRIMARY ---");
            Weapon c1 = selectWeaponMenu();
            System.out.println("\n--- SELECT OPPONENT SECONDARY ---");
            Weapon c2 = selectWeaponMenu();
            cpu = new Personaje(opName, c1, c2);
        } else {
            cpu = new Personaje("Insurgent", getRandomWeapon(), getRandomWeapon());
        }

        System.out.println("\n[LOADOUT COMPLETE] " + player.nombre + " vs " + cpu.nombre);

        // BATTLE LOOP
        while (player.salud > 0 && cpu.salud > 0) {
            ejecutarTurno(player, cpu);
            if (cpu.salud <= 0) break;
            ejecutarTurno(cpu, player); // Using same method for CPU to show stats
        }

        System.out.println("\n" + (player.salud > 0 ? ">>> SECTOR SECURED." : ">>> OPERATOR KIA."));
    }

    private static Weapon selectWeaponMenu() {
        while (true) {
            System.out.println("\n--- CATEGORIES ---");
            System.out.println("1. BATTLE RIFLES (7.62x51mm NATO / .308 Winchester / .450 Marlin)");
            System.out.println("2. ASSAULT RIFLES (7.62x39mm / 5.45mm)");
            System.out.println("3. SHOTGUNS (12 Gauge)");
            System.out.println("4. SIDEARMS (9mm / .45 ACP / 7.62x25mm / Look, man, the M60 was added late into development, it's a sidearm for gameplay reasons)");
            String cat = sc.nextLine();
            
            if (cat.equals("1")) { // BRs
                while (true) {
                    System.out.println("\n[BATTLE RIFLES] 1. G3 | 2. FAL 50.00 | 3. Browning BLR | 0. BACK");
                    String c = sc.nextLine();
                    if (c.equals("1")) return new G3();
                    if (c.equals("2")) return new FAL50();
                    if (c.equals("3")) return new BrowningBLR();
                    if (c.equals("0")) break;
                }
            } else if (cat.equals("2")) { // ARs
                while (true) {
                    System.out.println("\n[ASSAULT RIFLES] 1. AKM | 2. AK-74M | 3. FAL 7.62 (Soviet) | 0. BACK");
                    String c = sc.nextLine();
                    if (c.equals("1")) return new AKM();
                    if (c.equals("2")) return new AK74M();
                    if (c.equals("3")) return new FAL762(); // Correctly categorized now
                    if (c.equals("0")) break;
                }
            } else if (cat.equals("3")) {
                while (true) {
                    System.out.println("\n[SHOTGUNS] 1. Auto-5 | 2. OverUnder | 0. BACK");
                    String c = sc.nextLine();
                    if (c.equals("1")) return new Auto5();
                    if (c.equals("2")) return new OverUnder();
                    if (c.equals("0")) break;
                }
            } else if (cat.equals("4")) {
                while (true) {
                    System.out.println("\n[SIDEARMS] 1. MAC-10 | 2. M60 | 3. Bodyguard 49 | 4. Makarov | 0. BACK");
                    String c = sc.nextLine();
                    if (c.equals("1")) return new MAC10();
                    if (c.equals("2")) return new M60();
                    if (c.equals("3")) return new Bodyguard49();
                    if (c.equals("4")) return new Makarov();
                    if (c.equals("0")) break;
                }
            }
        }
    }

    private static void ejecutarTurno(Personaje p, Personaje target) {
        System.out.println("\n--- " + p.nombre + " | HP: " + p.salud + " | ACC: " + p.accuracy + "% ---");
        
        if (p.isBusy()) {
            System.out.println(">> " + p.currentAction + " (" + p.turnsToWait + " turns)");
            p.turnsToWait--;
            return;
        }

        // Check if CPU or Player
        if (!p.nombre.equals("Insurgent") && !p.nombre.equals("CustomCPU")) { // Basic Player Logic
            System.out.println("1. SHOOT | 2. SWAP | 3. RELOAD | 4. " + p.activeWeapon.getAbility1().getName() + " | 5. " + p.activeWeapon.getAbility2().getName());
            String move = sc.nextLine();
            switch (move) {
                case "1": p.shoot(target); break;
                case "2": p.swapWeapon(); break;
                case "3": p.startReload(); break;
                case "4": p.activeWeapon.getAbility1().execute(p, target); break;
                case "5": p.activeWeapon.getAbility2().execute(p, target); break;
            }
        } else { // CPU Simple AI
            if (p.activeWeapon.currentAmmo > 0) p.shoot(target);
            else p.startReload();
        }
    }

    private static Weapon getRandomWeapon() {
        Weapon[] pool = {new G3(), new AKM(), new MAC10(), new Auto5(), new M60(), new Makarov()};
        return pool[rand.nextInt(pool.length)];
    }

    private static String getAbilityDesc(String abilityName) {
        // ... (Same as before)
        return "";
    }
}