package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;
import java.beans.PropertyChangeEvent;

public class CompteARebours implements TimerChangeListener {
    
    private String name;
    private int compteur;
    private TimerService timerService;
    
    public CompteARebours(String name, int valeurInitiale, TimerService timerService) {
        this.name = name;
        this.compteur = valeurInitiale;
        this.timerService = timerService;
        
        // S'inscrire comme observateur
        timerService.addTimeChangeListener(this);
        
        System.out.println("CompteARebours " + name + " initialisé à " + compteur);
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Réagir uniquement aux changements de secondes
        if (SECONDE_PROP.equals(evt.getPropertyName())) {
            compteur--;
            System.out.println(name + " : " + compteur);
            
            // Se désinscrire quand le compte arrive à 0
            if (compteur <= 0) {
                System.out.println(name + " terminé ! Désinscription...");
                timerService.removeTimeChangeListener(this);
            }
        }
    }
}