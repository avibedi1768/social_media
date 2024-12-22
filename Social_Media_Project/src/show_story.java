
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author ASUS
 */
public class show_story extends javax.swing.JFrame {

    /**
     * Creates new form show_story
     */
    Timer progressBarTimer;
    int progressValue = 0;

    public show_story(String username, String photo, String caption) {
        initComponents();

        username_lb.setText(username);

        ImageIcon ic = new ImageIcon(photo);
        Image img = ic.getImage().getScaledInstance(image_lb.getWidth(), image_lb.getHeight(), Image.SCALE_SMOOTH);
        ic = new ImageIcon(img);

        image_lb.setIcon(ic);

        caption_lb.setText(caption);

        setSize(500, 550);
        setLocationRelativeTo(null);

        takeRest();
    }

    void takeRest() {
        progressBarTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                progressValue++;
                pbar.setValue(progressValue);
                if (progressValue >= 100) {
                    progressBarTimer.stop();
                    dispose();
                }
            }
        });
        progressBarTimer.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pbar = new javax.swing.JProgressBar();
        image_lb = new javax.swing.JLabel();
        caption_lb = new javax.swing.JLabel();
        username_lb = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().add(pbar);
        pbar.setBounds(0, 0, 500, 10);

        image_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        image_lb.setText("image");
        getContentPane().add(image_lb);
        image_lb.setBounds(0, 70, 490, 370);

        caption_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        caption_lb.setText("caption");
        getContentPane().add(caption_lb);
        caption_lb.setBounds(20, 450, 450, 60);

        username_lb.setText("username");
        getContentPane().add(username_lb);
        username_lb.setBounds(10, 20, 470, 16);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(show_story.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(show_story.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(show_story.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(show_story.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new show_story().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel caption_lb;
    private javax.swing.JLabel image_lb;
    private javax.swing.JProgressBar pbar;
    private javax.swing.JLabel username_lb;
    // End of variables declaration//GEN-END:variables
}
