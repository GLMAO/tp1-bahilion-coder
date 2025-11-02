package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;
import java.beans.PropertyChangeEvent;

public class Horloge implements TimerChangeListener {

    String name;
    TimerService timerService;

    public Horloge(String name, TimerService timerService) {
        this.name = name;
        this.timerService = timerService;
        
        // S'inscrire comme observateur
        timerService.addTimeChangeListener(this);
        
        System.out.println("Horloge " + name + " initialized!");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Afficher uniquement lors des changements de secondes
        if (SECONDE_PROP.equals(evt.getPropertyName())) {
            afficherHeure();
        }
    }

    public void afficherHeure() {
        if (timerService != null) {
            System.out.println(name + " affiche " + 
                timerService.getHeures() + ":" +
                timerService.getMinutes() + ":" +
                timerService.getSecondes());
        }
    }
}