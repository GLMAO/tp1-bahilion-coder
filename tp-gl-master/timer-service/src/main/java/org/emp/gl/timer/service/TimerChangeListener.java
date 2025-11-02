/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.gl.timer.service;

/**
 *
 * @author tina
 */

import java.beans.PropertyChangeListener;

public interface TimerChangeListener extends PropertyChangeListener {
    
    final static String DIXEME_DE_SECONDE_PROP = "dixième";
    final static String SECONDE_PROP = "seconde";
    final static String MINUTE_PROP = "minute";
    final static String HEURE_PROP = "heure";
    
    // Hérite de propertyChange(PropertyChangeEvent evt) de PropertyChangeListener
    
    // Adapter l'ancienne signature
    default void propertyChange(String prop, Object oldValue, Object newValue) {
        propertyChange(new java.beans.PropertyChangeEvent(this, prop, oldValue, newValue));
    }
}