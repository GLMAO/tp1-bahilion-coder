package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class HorlogeGUI extends JFrame implements TimerChangeListener {
    
    private JLabel labelHeure;
    private JLabel labelDate;
    private TimerService timerService;
    
    public HorlogeGUI(TimerService timerService) {
        this.timerService = timerService;
        
        // S'inscrire comme observateur
        timerService.addTimeChangeListener(this);
        
        // Configuration de la fenêtre
        initComponents();
        
        // Afficher l'heure initiale
        updateTime();
    }
    
    private void initComponents() {
        // Titre de la fenêtre
        setTitle("Horloge Numérique");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrer la fenêtre
        
        // Layout principal
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(45, 45, 45));
        
        // Panel pour l'heure
        JPanel panelHeure = new JPanel();
        panelHeure.setBackground(new Color(45, 45, 45));
        
        labelHeure = new JLabel("00:00:00");
        labelHeure.setFont(new Font("Digital-7", Font.BOLD, 72));
        labelHeure.setForeground(new Color(0, 255, 0)); // Vert style digital
        labelHeure.setHorizontalAlignment(SwingConstants.CENTER);
        
        panelHeure.add(labelHeure);
        add(panelHeure, BorderLayout.CENTER);
        
        // Panel pour la date (optionnel)
        labelDate = new JLabel();
        labelDate.setFont(new Font("Arial", Font.PLAIN, 16));
        labelDate.setForeground(Color.WHITE);
        labelDate.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelDate, BorderLayout.SOUTH);
        
        // Rendre visible
        setVisible(true);
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Mettre à jour l'affichage à chaque changement de seconde
        if (SECONDE_PROP.equals(evt.getPropertyName())) {
            updateTime();
        }
    }
    
    private void updateTime() {
        // Utiliser SwingUtilities pour la thread safety
        SwingUtilities.invokeLater(() -> {
            String heure = String.format("%02d:%02d:%02d",
                timerService.getHeures(),
                timerService.getMinutes(),
                timerService.getSecondes());
            
            labelHeure.setText(heure);
            
            // Optionnel : afficher la date
            java.time.LocalDate date = java.time.LocalDate.now();
            labelDate.setText(date.toString());
        });
    }
}