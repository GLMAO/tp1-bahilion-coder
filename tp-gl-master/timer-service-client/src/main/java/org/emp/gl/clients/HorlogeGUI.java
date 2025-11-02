package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.beans.PropertyChangeEvent;
import java.time.LocalDate;
import javax.swing.border.EmptyBorder;

public class HorlogeGUI extends JFrame implements TimerChangeListener {

    private JLabel labelHeure;
    private JLabel labelDate;
    private TimerService timerService;
    private boolean blink = true; // For blinking colon animation

    public HorlogeGUI(TimerService timerService) {
        this.timerService = timerService;
        timerService.addTimeChangeListener(this);

        initComponents();
        updateTime();
    }

    private void initComponents() {
        setTitle("⏰ Horloge Numérique Moderne");
        setSize(480, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // --- Main Panel with Gradient Background ---
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(25, 25, 25),
                        getWidth(), getHeight(), new Color(60, 60, 60)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        // --- Digital Clock Display ---
        labelHeure = new JLabel("00:00:00", SwingConstants.CENTER);
        labelHeure.setFont(new Font("Consolas", Font.BOLD, 70));
        labelHeure.setForeground(new Color(0, 255, 128));

        // Add a rounded background for the digital display
        JPanel clockPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 0, 0, 150));
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
                super.paintComponent(g);
            }
        };
        clockPanel.setOpaque(false);
        clockPanel.setLayout(new BorderLayout());
        clockPanel.add(labelHeure, BorderLayout.CENTER);
        mainPanel.add(clockPanel, BorderLayout.CENTER);

        // --- Date Display ---
        labelDate = new JLabel("", SwingConstants.CENTER);
        labelDate.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        labelDate.setForeground(Color.LIGHT_GRAY);
        mainPanel.add(labelDate, BorderLayout.SOUTH);

        // --- Timer for Blinking Effect ---
        new javax.swing.Timer(500, e -> {
            blink = !blink;
            updateTime();
        }).start();

        setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (SECONDE_PROP.equals(evt.getPropertyName())) {
            updateTime();
        }
    }

    private void updateTime() {
        SwingUtilities.invokeLater(() -> {
            int h = timerService.getHeures();
            int m = timerService.getMinutes();
            int s = timerService.getSecondes();

            String time = String.format("%02d%s%02d%s%02d",
                    h, blink ? ":" : " ",
                    m, blink ? ":" : " ",
                    s);

            labelHeure.setText(time);
            labelDate.setText(LocalDate.now().toString());
        });
    }
}
