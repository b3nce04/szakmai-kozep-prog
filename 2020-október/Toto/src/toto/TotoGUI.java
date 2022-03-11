package toto;

import java.util.ArrayList;
import javax.swing.JTextField;

public class TotoGUI extends javax.swing.JFrame {

    public TotoGUI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        inputBox = new javax.swing.JTextField();
        nemMegKar = new javax.swing.JCheckBox();
        helytKar = new javax.swing.JCheckBox();
        submitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Totó eredmény ellenörző");
        setResizable(false);

        jLabel1.setText("Kérem a forduló eredményeit [1, 2, X]:");

        inputBox.setText("12X12X12X12X12");
        inputBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inputBoxKeyReleased(evt);
            }
        });

        nemMegKar.setText("Nem megfelelő a karakterek száma (14)");
        nemMegKar.setEnabled(false);

        helytKar.setText("Helytelen karakter az eredményekben()");
        helytKar.setEnabled(false);

        submitButton.setText("Eredmények mentése");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(inputBox, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nemMegKar)
                    .addComponent(helytKar)
                    .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nemMegKar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(helytKar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submitButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputBoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputBoxKeyReleased
        értékCheck(((JTextField) evt.getComponent()).getText());
    }//GEN-LAST:event_inputBoxKeyReleased

    public void értékCheck (String érték) {
        // b
        érték = érték.toUpperCase();
        nemMegKar.setSelected(érték.length() != 14);
        nemMegKar.setText(("Nem megfelelő a karakterek száma (" + érték.length() + ")"));
        // c
        ArrayList<Character> hibásak = new ArrayList<>();
        for (int i = 0; i < érték.length(); i++) {
            if (érték.charAt(i) != '1' && érték.charAt(i) != '2' && érték.charAt(i) != 'X') {
                hibásak.add(érték.charAt(i));
            }
        }
        helytKar.setSelected(hibásak.size()!= 0);
        String hibásakSzöveg = "";
        for (Character character : hibásak) {
            hibásakSzöveg += character + ";";
        }
        if (hibásakSzöveg.length() > 1) {
            hibásakSzöveg = hibásakSzöveg.substring(0, hibásakSzöveg.length()-1);
        }
        helytKar.setText("Helytelen karakter az eredményekben (" + hibásakSzöveg + ")");
        // d
        boolean egyik = helytKar.isSelected();
        boolean másik = nemMegKar.isSelected();
        submitButton.setEnabled((!egyik && !másik));
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TotoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TotoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TotoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TotoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TotoGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox helytKar;
    private javax.swing.JTextField inputBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JCheckBox nemMegKar;
    private javax.swing.JButton submitButton;
    // End of variables declaration//GEN-END:variables
}
