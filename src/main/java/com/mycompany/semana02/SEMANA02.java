/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.semana02;

import Vista.Registro;

/**
 *
 * @author miguel
 */
public class SEMANA02 {

    public static void main(String[] args) {
        try {
            // Set HiFi Look and Feel from JTattoo
            javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SEMANA02.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SEMANA02.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SEMANA02.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SEMANA02.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

         java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new Registro().setVisible(true);
        }
    });
    }
}
