
import VMM.DBLoader;
import java.sql.*;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
/**
 *
 * @author ASUS
 */
public class accounts extends javax.swing.JPanel {

    /**
     * Creates new form accounts
     */
    public accounts() {
        initComponents();
        setSize(330, 120);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        photo_lb = new javax.swing.JLabel();
        user_lb = new javax.swing.JLabel();
        follow_bt = new javax.swing.JButton();

        setLayout(null);

        photo_lb.setText("user photo");
        add(photo_lb);
        photo_lb.setBounds(10, 10, 100, 100);

        user_lb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        user_lb.setText("username");
        add(user_lb);
        user_lb.setBounds(150, 30, 130, 20);

        follow_bt.setText("Follow");
        follow_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                follow_btActionPerformed(evt);
            }
        });
        add(follow_bt);
        follow_bt.setBounds(140, 60, 130, 23);
    }// </editor-fold>//GEN-END:initComponents

    private void follow_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_follow_btActionPerformed
        // TODO add your handling code here:
        try {

            if (follow_bt.getText().equals("follow")) {
                //new follow in table

                String followed_by = global.username;
                String followed_to = user_lb.getText();
                System.out.println("user = " + followed_by);

                ResultSet rs = DBLoader.executeSQL("select * from followers");

                //go to last
                while (rs.next()) {
                }

                rs.moveToInsertRow();

                rs.updateString("followed_by", followed_by);
                rs.updateString("followed_to", followed_to);

                rs.insertRow();

                follow_bt.setText("unfollow");
            } else if (follow_bt.getText().equals("unfollow")) {
                //unfollow in table

                String followed_by = global.username;
                String followed_to = user_lb.getText();
                System.out.println("user = " + followed_by);

                String url = "select*from followers where followed_by = '" + followed_by
                        + "' and followed_to = '" + followed_to + "'";
                ResultSet rs = DBLoader.executeSQL(url);

                rs.next();
                rs.deleteRow();
                follow_bt.setText("follow");
            } else if (follow_bt.getText().equals("remove")) {
                String user = user_lb.getText();

                //give alert
                int ans = JOptionPane.showConfirmDialog(this, "are you sure to remove "
                        + user + " from your followers? This can't be undone", "delete confirmation", JOptionPane.YES_NO_OPTION);

                if (ans == JOptionPane.YES_OPTION) {
                    //remove row in followers table
                    String sql = "select * from followers where followed_by = '" + user
                            + "' and followed_to = '" + global.username + "'";

                    ResultSet rs = DBLoader.executeSQL(sql);
                    rs.next();
                    rs.deleteRow();

                    follow_bt.setVisible(false);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_follow_btActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton follow_bt;
    public javax.swing.JLabel photo_lb;
    public javax.swing.JLabel user_lb;
    // End of variables declaration//GEN-END:variables
}
