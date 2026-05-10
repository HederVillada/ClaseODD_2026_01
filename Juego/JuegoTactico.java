package Juego;

import Juego.Weapons.*;
import java.util.Scanner;

public class JuegoTactico {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("★ TACTICAL SIMULATOR 2026 ★");
        
        System.out.print("ENTER OPERATOR NAME: ");
        String name = sc.nextLine();

        // 1. SELECT PRIMARY
        System.out.println("\n--- SELECT PRIMARY WEAPON ---");
        Weapon primary = selectWeaponMenu();

        // 2. SELECT SECONDARY
        System.out.println("\n--- SELECT SECONDARY WEAPON ---");
        Weapon secondary = selectWeaponMenu();

        Personaje player = new Personaje(name, primary, secondary);
        
        // Mock Opponent (Randomizing weapons for the Insurgent)
        Personaje cpu = new Personaje("Insurgent", new AKM(), new Bodyguard49());

        System.out.println("\nLOADOUT COMPLETE: " + player.nombre + " vs " + cpu.nombre);
        System.out.println("MISSION START.");

        // BATTLE LOOP
        while (player.salud > 0 && cpu.salud > 0) {
            ejecutarTurno(player, cpu);
            if (cpu.salud <= 0) break;
            ejecutarTurnoCPU(cpu, player);
        }

        System.out.println("\n" + (player.salud > 0 ? ">>> MISSION SUCCESS." : ">>> OPERATOR KIA."));
    }

    private static Weapon selectWeaponMenu() {
        System.out.println("1. BATTLE RIFLES (G3, FAL 50.00, FAL 7.62)");
        System.out.println("2. ASSAULT RIFLES (AKM, AK-74M)");
        System.out.println("3. SHOTGUNS (Auto-5, OverUnder)");
        System.out.println("4. SIDEARMS (MAC-10, M60, Bodyguard49)");
        
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                System.out.println("A. G3 | B. FAL 50.00 | C. FAL 7.62");
                String br = sc.nextLine().toUpperCase();
                if (br.equals("A")) return new G3();
                if (br.equals("B")) return new FAL50();
                return new FAL762();
            case "2":
                System.out.println("A. AKM | B. AK-74M");
                String ar = sc.nextLine().toUpperCase();
                return ar.equals("A") ? new AKM() : new AK74M();
            case "3":
                System.out.println("A. Auto-5 | B. OverUnder");
                String sg = sc.nextLine().toUpperCase();
                return sg.equals("A") ? new Auto5() : new OverUnder();
            case "4":
                System.out.println("A. MAC-10 | B. M60 | C. Bodyguard49");
                String side = sc.nextLine().toUpperCase();
                if (side.equals("A")) return new MAC10();
                if (side.equals("B")) return new M60();
                return new Bodyguard49();
            default:
                System.out.println("Invalid selection. Defaulting to G3.");
                return new G3();
        }
    }

    private static void ejecutarTurno(Personaje p, Personaje target) {
        System.out.println("\n--- " + p.nombre + " | HP: " + p.salud + " | ACC: " + p.accuracy + "% ---");
        System.out.println("CURRENT: " + p.activeWeapon.getName() + " [" + p.activeWeapon.currentAmmo + "/" + p.activeWeapon.maxAmmo + "]");
        
        if (p.isBusy()) {
            System.out.println(">> " + p.currentAction + "... (" + p.turnsToWait + " turns left)");
            p.turnsToWait--;
            return;
        }

        System.out.println("1. SHOOT");
        System.out.println("2. SWAP (" + (p.activeWeapon == p.primary ? p.secondary.getName() : p.primary.getName()) + ")");
        System.out.println("3. RELOAD");
        
        // Dynamic Ability Descriptions
        System.out.print("4. " + p.activeWeapon.getAbility1().getName());
        System.out.println(" -> " + getAbilityDesc(p.activeWeapon.getAbility1().getName()));
        
        System.out.print("5. " + p.activeWeapon.getAbility2().getName());
        System.out.println(" -> " + getAbilityDesc(p.activeWeapon.getAbility2().getName()));

        String move = sc.nextLine();
        switch (move) {
            case "1": p.shoot(target); break;
            case "2": p.swapWeapon(); break;
            case "3": p.startReload(); break;
            case "4": p.activeWeapon.getAbility1().execute(p, target); break;
            case "5": p.activeWeapon.getAbility2().execute(p, target); break;
        }
    }

    private static void ejecutarTurnoCPU(Personaje cpu, Personaje target) {
        if (cpu.isBusy()) {
            cpu.turnsToWait--;
            return;
        }
        if (cpu.activeWeapon.currentAmmo > 0) {
            cpu.shoot(target);
        } else {
            cpu.startReload();
        }
    }

    // This is the "Library" of descriptions you requested
    private static String getAbilityDesc(String abilityName) {
        switch (abilityName) {
            case "Trickshot": return "Fires 3 shots at Low Accuracy.";
            case "Zoom In": return "Increases Accuracy by 15 (Max 100).";
            case "Mag Dump": return "Empties the whole mag at Low Accuracy.";
            case "New York Reload": return "[PASSIVE] Reload takes only 1 turn.";
            case "Double Tap": return "Fires 2 shots at Medium Accuracy.";
            case "Shotgun Spin": return "Rapidly fires 3 shells at Low Accuracy.";
            case "Fix Bayonet": return "Next turn: Melee slash (30 DMG, 100% ACC).";
            case "SLAP Load": return "Double Damage, but miss causes a jam.";
            case "Buttstock Bash": return "10 fixed damage + reduces enemy accuracy.";
            case "Blast Off": return "20 damage + slight enemy accuracy debuff.";
            case "Smoke Bomb": return "Massive ACC debuff to all; covers your escape.";
            case "12G Sabot Slug": return "One-turn load for high-accuracy slug.";
            case "Suppressive Fire": return "5 shots at Super Low Acc; suppresses target.";
            case "Molotov Throw": return "Incendiary bottle; ignores weapon mechanics.";
            default: return "No description available.";
        }
    }
}