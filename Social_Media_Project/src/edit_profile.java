
import java.awt.Image;
import java.io.File;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class edit_profile extends javax.swing.JFrame {

    ButtonGroup bg;
    JFileChooser jfc;
    File f;

    public edit_profile() {
        initComponents();

        jfc = new JFileChooser();

        bg = new ButtonGroup();
        bg.add(male_rbt);
        bg.add(female_rbt);

        setVisible(true);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        image_lb = new javax.swing.JLabel();
        username_lb = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        email_tf = new javax.swing.JTextField();
        mobile_tf = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        female_rbt = new javax.swing.JRadioButton();
        male_rbt = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        image_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        image_lb.setText("image");
        getContentPane().add(image_lb);
        image_lb.setBounds(10, 80, 200, 180);

        username_lb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        username_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        username_lb.setText("username");
        getContentPane().add(username_lb);
        username_lb.setBounds(260, 150, 160, 40);

        jButton1.setText("choose picture");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(20, 280, 180, 23);

        jLabel3.setText("email");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(50, 360, 90, 16);
        getContentPane().add(email_tf);
        email_tf.setBounds(180, 352, 230, 30);
        getContentPane().add(mobile_tf);
        mobile_tf.setBounds(180, 392, 230, 30);

        jLabel4.setText("gender");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(50, 450, 90, 16);

        jLabel5.setText("mobile");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(50, 400, 90, 16);

        female_rbt.setText("female");
        getContentPane().add(female_rbt);
        female_rbt.setBounds(260, 450, 80, 21);

        male_rbt.setText("male");
        getContentPane().add(male_rbt);
        male_rbt.setBounds(180, 450, 70, 21);

        jButton2.setText("update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(160, 510, 140, 23);

        jButton3.setText("go back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(30, 30, 75, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        user_profile obj = new user_profile();
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int ans = jfc.showOpenDialog(this);

        if (ans == JFileChooser.APPROVE_OPTION) {
            f = jfc.getSelectedFile();
            ImageIcon ic = new ImageIcon(f.getPath());
            Image img = ic.getImage().getScaledInstance(image_lb.getWidth(),
                    image_lb.getHeight(), Image.SCALE_SMOOTH);
            ic = new ImageIcon(img);
            image_lb.setIcon(ic);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        String email = email_tf.getText();
        String mobile = mobile_tf.getText();
        String gender = "";
        if (male_rbt.isSelected()) {
            gender = "Male";
        } else {
            gender = "Female";
        }

        if (email.isEmpty() || mobile.isEmpty() || gender.isEmpty()) {
            JOptionPane.showMessageDialog(this, "all fields mandatory");
            return;
        }

        if (f == null) {
            JOptionPane.showMessageDialog(this, "plz select an image as display picture");
            return;
        }

        System.out.println("email = " + email);
        System.out.println("mobile = " + mobile);
        System.out.println("gender = " + gender);
        System.out.println("updated_photo = " + f);

        String ans = MyClient.update_user(email, mobile, gender, f);

        if (ans.equals("pass"))
            JOptionPane.showMessageDialog(this, "updated successfully");
        else
            JOptionPane.showMessageDialog(this, "updation failed");
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(edit_profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(edit_profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(edit_profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(edit_profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new edit_profile().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField email_tf;
    public javax.swing.JRadioButton female_rbt;
    public javax.swing.JLabel image_lb;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public javax.swing.JRadioButton male_rbt;
    public javax.swing.JTextField mobile_tf;
    public javax.swing.JLabel username_lb;
    // End of variables declaration//GEN-END:variables
}
