package Juego;

import Juego.Weapons.*;
import java.util.Scanner;
import java.util.Random;

public class JuegoTactico {
    private Personaje jugador1;
    private Personaje jugador2;
    private Scanner sc = new Scanner(System.in);
    private Random rand = new Random();

    public JuegoTactico (Personaje p1, Personaje p2) {
        this.jugador1 = p1;
        this.jugador2 = p2;
    }

    //aqui esta el metodo que inicia la pelea, se encarga de ejecutar el loop del juego, y de determinar el ganador al final de la pelea
    public void iniciarPelea() {
        System.out.println("\nENGAGEMENT INITIATED: " + jugador1.getNombre() + " VS " + jugador2.getNombre());

        //loop basico del juego, se ejecuta mientras ambos personajes esten vivos, y se alternan los turnos entre el jugador y la IA
        while (jugador1.estaVivo() && jugador2.estaVivo()) {
            ejecutarTurno(jugador1, jugador2, false); //turno jugador
            if (!jugador2.estaVivo()) break;
            
            ejecutarTurno(jugador2, jugador1, true);  //turno IA
        }

        //determina el ganador al final de la pelea, dependiendo de quien este vivo, o muerto, respectivamente
        System.out.println("\n" + (jugador1.estaVivo() ? ">>> " + jugador1.getNombre() + " SECURED THE SECTOR." : ">>> " + jugador1.getNombre() + " IS KIA."));
    }

    private void ejecutarTurno(Personaje p, Personaje target, boolean isAI) {
        System.out.println("\n--- " + p.getNombre() + " | HP: " + p.getPuntosDeVida() + " | ACC: " + p.accuracy + "% ---");
        System.out.println("WEAPON: " + p.activeWeapon.getName() + " [" + p.activeWeapon.currentAmmo + "/" + p.activeWeapon.maxAmmo + "]");
        
        if (p.isBusy()) {
            System.out.println(">> " + p.currentAction + " (" + p.turnsToWait + " turns left)");
            p.turnsToWait--;
            return;
        }

        if (!isAI) {
            handlePlayerInput(p, target);
        } else {
            handleOpponentAI(p, target);
        }
    }

    private void handlePlayerInput(Personaje p, Personaje target) {
        System.out.println("1. SHOOT | 2. SWAP | 3. RELOAD | 4. " + p.activeWeapon.getAbility1().getName() + " | 5. " + p.activeWeapon.getAbility2().getName()); //las abilidades cambian dependiendo el arma
        String move = sc.nextLine();
        switch (move) {
            case "1": p.shoot(target); break;
            case "2": p.swapWeapon(); break;
            case "3": p.startReload(); break;
            case "4": p.activeWeapon.getAbility1().execute(p, target); break;
            case "5": p.activeWeapon.getAbility2().execute(p, target); break;
            default: System.out.println(">> Hesitation. Turn skipped.");
        }
    }

    private void handleOpponentAI(Personaje p, Personaje target) {
        //DECISIONES DE LA IA
        int decision = rand.nextInt(100);

        //Recarga si esta vacio
        if (p.activeWeapon.currentAmmo <= 0) {
            System.out.println(">> " + p.getNombre() + " is dry! Reloading...");
            p.startReload();
        } 
        //Puede usar abilidad 1 o 2
        else if (decision < 20) {
            System.out.println(">> " + p.getNombre() + " uses " + p.activeWeapon.getAbility1().getName() + "!");
            p.activeWeapon.getAbility1().execute(p, target);
        } 
        else if (decision < 40) {
            System.out.println(">> " + p.getNombre() + " uses " + p.activeWeapon.getAbility2().getName() + "!");
            p.activeWeapon.getAbility2().execute(p, target);
        }
        //Puede cambiar armas
        else if (decision < 50) {
            p.swapWeapon();
            System.out.println(">> " + p.getNombre() + " is switching to " + p.activeWeapon.getName() + "!");
        }
        //Y si no, tan solo dispara. Bonito my uso de ifs, elifs, y elses, no?
        else {
            p.shoot(target);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("★ TACTICAL SIMULATOR 2026 ★");
        
        System.out.print("ENTER OPERATOR NAME: ");
        String p1Name = input.nextLine();
        
        System.out.print("ENTER OPPONENT NAME: ");
        String p2Name = input.nextLine(); //escojes el nombre del oponente, pero no es necesario, ya que el oponente es controlado por la IA

        //aqui es donde se crean los personajes, con sus armas primarias y secundarias, respectivamente
        Personaje p1 = new Personaje(p1Name, new G3(), new Makarov());
        Personaje p2 = new Personaje(p2Name, new AKM(), new Bodyguard49());

        JuegoTactico juego = new JuegoTactico(p1, p2);
        juego.iniciarPelea();
    }
}