package org.emp.gl.core.launcher;

import java.util.Random;

import org.emp.gl.clients.CompteARebours;
import org.emp.gl.clients.Horloge ;
import org.emp.gl.clients.Horloge;
import org.emp.gl.clients.HorlogeGUI;
import org.emp.gl.timer.service.TimerService;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;

/**
 * Application principale pour lancer les horloges.
 * - Horloge GUI (interface graphique)
 * - Horloge console (texte)
 */
public class App {

    public static void main(String[] args) {
        new App().start();
    }

    /**
     * Méthode principale de démarrage.
     */
    public void start() {
        System.out.println("=== Lancement de l'application Horloge ===");

        // Initialisation du service de temps
        TimerService timerService = createTimerService();

        // Lancement des différentes horloges
        launchClockInterfaces(timerService);

        System.out.println("=== Application en cours d'exécution... ===");
    }

    /**
     * Crée et retourne une instance du service de temps.
     */
    private TimerService createTimerService() {
        System.out.println("[INFO] Initialisation du DummyTimeServiceImpl...");
        return new DummyTimeServiceImpl();
    }

    /**
     * Démarre les interfaces d’horloge (GUI et console).
     */
    private void launchClockInterfaces(TimerService timerService) {
        System.out.println("[INFO] Démarrage des horloges...");

        // Interface graphique
        HorlogeGUI horlogeGUI = new HorlogeGUI(timerService);
        System.out.println("[OK] Horloge GUI démarrée.");

        // Interface console
        Horloge horlogeConsole = new Horloge("Console", timerService);
        System.out.println("[OK] Horloge console démarrée.");
    }

    /**
     * Nettoie la console (optionnel, pour affichage plus propre).
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
