
//import java.awt.Dimension;
import java.awt.Image;
//import java.awt.Toolkit;
import java.io.File;
import javax.swing.*;

public class signup extends javax.swing.JFrame {

    JFileChooser jfc;
    ButtonGroup bg;
    File f;

    public signup() {
        initComponents();
        jfc = new JFileChooser();

//        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
//        int w = (int) d.getWidth();
//        int h = (int) d.getHeight();
//        photo_icon_lb.setIcon(new ImageIcon("C:\\Users\\ASUS\\Downloads\\message.png"));
        ImageIcon ic = new ImageIcon("C:\\Users\\ASUS\\Downloads\\message.png");
        Image img = ic.getImage().getScaledInstance(photo_icon_lb.getWidth(),
                photo_icon_lb.getWidth(), Image.SCALE_SMOOTH);
        ic = new ImageIcon(img);
        photo_icon_lb.setIcon(ic);

        bg = new ButtonGroup();
        bg.add(male_rb);
        bg.add(female_rb);

        setSize(800, 800);

//        setSize(w, h);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        name_lb = new javax.swing.JLabel();
        name_tf = new javax.swing.JTextField();
        email_lb = new javax.swing.JLabel();
        email_tf = new javax.swing.JTextField();
        mobile_lb = new javax.swing.JLabel();
        mobile_tf = new javax.swing.JTextField();
        pass_lb = new javax.swing.JLabel();
        pass_pf = new javax.swing.JPasswordField();
        confirm_pass_lb = new javax.swing.JLabel();
        confirm_pass_pf = new javax.swing.JPasswordField();
        gender_lb = new javax.swing.JLabel();
        female_rb = new javax.swing.JRadioButton();
        male_rb = new javax.swing.JRadioButton();
        photo_lb = new javax.swing.JLabel();
        photo_icon_lb = new javax.swing.JLabel();
        browse_bt = new javax.swing.JButton();
        submit_lb = new javax.swing.JButton();
        signup_lb = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        name_lb.setText("Name");
        getContentPane().add(name_lb);
        name_lb.setBounds(110, 90, 60, 16);
        getContentPane().add(name_tf);
        name_tf.setBounds(250, 90, 140, 30);

        email_lb.setText("Email");
        getContentPane().add(email_lb);
        email_lb.setBounds(110, 130, 70, 16);
        getContentPane().add(email_tf);
        email_tf.setBounds(250, 130, 140, 30);

        mobile_lb.setText("Mobile");
        getContentPane().add(mobile_lb);
        mobile_lb.setBounds(110, 250, 80, 16);

        mobile_tf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mobile_tfActionPerformed(evt);
            }
        });
        getContentPane().add(mobile_tf);
        mobile_tf.setBounds(250, 250, 140, 30);

        pass_lb.setText("Password");
        getContentPane().add(pass_lb);
        pass_lb.setBounds(110, 170, 80, 16);
        getContentPane().add(pass_pf);
        pass_pf.setBounds(250, 170, 140, 30);

        confirm_pass_lb.setText("Confirm Password");
        getContentPane().add(confirm_pass_lb);
        confirm_pass_lb.setBounds(110, 210, 130, 16);
        getContentPane().add(confirm_pass_pf);
        confirm_pass_pf.setBounds(250, 210, 140, 30);

        gender_lb.setText("Gender");
        getContentPane().add(gender_lb);
        gender_lb.setBounds(110, 310, 90, 16);

        female_rb.setText("female");
        getContentPane().add(female_rb);
        female_rb.setBounds(330, 310, 100, 21);

        male_rb.setText("male");
        getContentPane().add(male_rb);
        male_rb.setBounds(250, 310, 60, 21);

        photo_lb.setText("Photo");
        getContentPane().add(photo_lb);
        photo_lb.setBounds(110, 390, 70, 16);

        photo_icon_lb.setText("jLabel8");
        getContentPane().add(photo_icon_lb);
        photo_icon_lb.setBounds(220, 350, 200, 120);

        browse_bt.setText("Browse");
        browse_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browse_btActionPerformed(evt);
            }
        });
        getContentPane().add(browse_bt);
        browse_bt.setBounds(440, 390, 110, 23);

        submit_lb.setText("SUBMIT");
        submit_lb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submit_lbActionPerformed(evt);
            }
        });
        getContentPane().add(submit_lb);
        submit_lb.setBounds(250, 490, 100, 23);

        signup_lb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        signup_lb.setText("SIGNUP");
        getContentPane().add(signup_lb);
        signup_lb.setBounds(250, 40, 80, 25);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void browse_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browse_btActionPerformed
        // TODO add your handling code here:
        int ans = jfc.showOpenDialog(this);

        if (ans == JFileChooser.APPROVE_OPTION) {
            f = jfc.getSelectedFile();
            ImageIcon ic = new ImageIcon(f.getPath());
            Image img = ic.getImage().getScaledInstance(photo_icon_lb.getWidth(),
                    photo_icon_lb.getHeight(), Image.SCALE_SMOOTH);
            ic = new ImageIcon(img);
            photo_icon_lb.setIcon(ic);
        } else {
            JOptionPane.showMessageDialog(this, "cancelled :)");
        }

    }//GEN-LAST:event_browse_btActionPerformed

    private void submit_lbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submit_lbActionPerformed
        // TODO add your handling code here:
        String email = email_tf.getText();
        String name = name_tf.getText();
        String pass = pass_pf.getText();
        String confirm_pass = confirm_pass_pf.getText();
        String gender;
        if (male_rb.isSelected()) {
            gender = "male";
        } else {
            gender = "female";
        }

        String mobile = mobile_tf.getText();

//        NO NEED OF GET PHOTO
//        String photo_path = f.getPath();
        if (email.equals("") || name.equals("") || pass.equals("")
                || confirm_pass.equals("") || mobile.equals("")
                || gender.equals("") || f == null) {
            JOptionPane.showMessageDialog(this, "ENTER ALL FIELDS");
        } else {
            System.out.println("email = " + email);
            System.out.println("name = " + name);
            System.out.println("pass = " + pass);
            System.out.println("confirm_pass = " + confirm_pass);
            System.out.println("gender = " + gender);
            System.out.println("mobile = " + mobile);
//            System.out.println("photo path = " + photo_path);

            String ans = MyClient.user_signup(name, email, pass, mobile, gender, f);

            if (ans.equals("pass")) {
                JOptionPane.showMessageDialog(this, "Signup successful");

                global.username = name;
                homepage obj = new homepage();
                obj.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Email already exists");
            }

        }


    }//GEN-LAST:event_submit_lbActionPerformed

    private void mobile_tfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mobile_tfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mobile_tfActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new signup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browse_bt;
    private javax.swing.JLabel confirm_pass_lb;
    private javax.swing.JPasswordField confirm_pass_pf;
    private javax.swing.JLabel email_lb;
    private javax.swing.JTextField email_tf;
    private javax.swing.JRadioButton female_rb;
    private javax.swing.JLabel gender_lb;
    private javax.swing.JRadioButton male_rb;
    private javax.swing.JLabel mobile_lb;
    private javax.swing.JTextField mobile_tf;
    private javax.swing.JLabel name_lb;
    private javax.swing.JTextField name_tf;
    private javax.swing.JLabel pass_lb;
    private javax.swing.JPasswordField pass_pf;
    private javax.swing.JLabel photo_icon_lb;
    private javax.swing.JLabel photo_lb;
    private javax.swing.JLabel signup_lb;
    private javax.swing.JButton submit_lb;
    // End of variables declaration//GEN-END:variables
}
