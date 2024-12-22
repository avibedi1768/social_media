
import VMM.DBLoader;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.sql.*;

public class homepage extends javax.swing.JFrame {

    public homepage() {
        initComponents();

        String ans = MyClient.get_accounts(global.username);
        System.out.println(ans);

        //GET_ACCOUNTS FUNC IN HERE
//        String ans = "";
//        try {
//            ResultSet rs = DBLoader.executeSQL("select*from user_table where username != '" + global.username + "'");
//
//            while (rs.next()) {
//                String user = rs.getString("username");
//                String photo = rs.getString("photo");
//
//                ans += user + "$" + photo + "^";
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        //FOR RIGHT PANEL: USERS
        StringTokenizer st = new StringTokenizer(ans, "^");
        int n = st.countTokens();
        int x = 10, y = 10;

        for (int i = 1; i <= n; i++) {
            StringTokenizer temp = new StringTokenizer(st.nextToken(), "$");
            String name = temp.nextToken();
            String photo = temp.nextToken();

            accounts objUser = new accounts();
            //FOR USERNAME:
            objUser.user_lb.setText(name);

            //FOR BUTTON: follow/unfollow
            try {
                ResultSet rs = DBLoader.executeSQL("select * from followers where followed_by = '"
                        + global.username + "' and followed_to = '" + name + "'");
                if (rs.next()) {
                    objUser.follow_bt.setText("unfollow");
                } else {
                    objUser.follow_bt.setText("follow");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            //FOR IMAGE:
            //image icon bnya pehle
            ImageIcon ic = new ImageIcon(photo);
            //fir onu resize kita
            Image img = ic.getImage().getScaledInstance(objUser.photo_lb.getWidth(),
                    objUser.photo_lb.getHeight(), Image.SCALE_SMOOTH);
            //fir nvi image icon bnaya
            ic = new ImageIcon(img);
            objUser.photo_lb.setIcon(ic);

            objUser.setBounds(x, y, 330, 120);

            jPanel7.add(objUser);

            y += 150;

            objUser.repaint();
            jPanel7.repaint();
            jPanel7.revalidate();

        }
        jPanel7.setPreferredSize(new Dimension(388, y + 50));

        //FOR LOWER MIDDLE PANEL: POSTS
        get_posts();

        //FOR UPPER MIDDLE PANEL: STORIES
        get_stories();

        setVisible(true);
        setSize(1920, 1080);
        setLocationRelativeTo(null);
    }

    public void get_posts() {
        int x = 50;
        int y = 50;
        String posts = MyClient.get_posts();

        StringTokenizer st = new StringTokenizer(posts, "^");
        int n = st.countTokens();

        for (int i = 1; i <= n; i++) {
            StringTokenizer st2 = new StringTokenizer(st.nextToken(), "$");
            String un = st2.nextToken();
            String cp = st2.nextToken();
            String ph = st2.nextToken();
            int pi = Integer.parseInt(st2.nextToken());

            post objPost = new post();
            objPost.user_lb.setText(un);
            objPost.caption_lb.setText(cp);

            ImageIcon ic = new ImageIcon(ph);
            Image img = ic.getImage().getScaledInstance(objPost.img_lb.getWidth(),
                    objPost.img_lb.getHeight(), Image.SCALE_SMOOTH);
            ic = new ImageIcon(img);
            objPost.img_lb.setIcon(ic);

            //for like button: (like/unlike)
            ResultSet rs = DBLoader.executeSQL("select*from like_post where post_id = " + pi
                    + " and username = '" + global.username + "'");
            try {
                if (rs.next()) {    //already liked
                    objPost.like_bt.setText("unlike");
                } else {    //not liked
                    objPost.like_bt.setText("like");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            objPost.post_id_lb.setText(pi + "");

            //like count:
            rs = DBLoader.executeSQL("select*from like_post where post_id = " + pi);
            int count = 0;
            try {
                while (rs.next()) {
                    count++;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            objPost.like_count_lb.setText(count + "");
            System.out.println("like count for post_id: " + pi + " is " + count);

            objPost.setBounds(x, y, 500, 500);

            jPanel4.add(objPost);
            y += 550;

            objPost.repaint();
            jPanel4.repaint();
            jPanel4.revalidate();
        }
        jPanel4.setPreferredSize(new Dimension(1100, y + 50));
    }

    public void get_stories() {
        String ans = MyClient.get_stories();
        System.out.println("stories = " + ans);

        StringTokenizer st = new StringTokenizer(ans, "^");
        int n = st.countTokens();
        int x = 20, y = 20;

        for (int i = 1; i <= n; i++) {
            StringTokenizer st2 = new StringTokenizer(st.nextToken(), "$");
            String story_id = st2.nextToken();
            String username = st2.nextToken();
            String photo = st2.nextToken();
            String user_photo = st2.nextToken();
            String caption = st2.nextToken();
            String time = st2.nextToken();

            System.out.println("story_id\t" + story_id);
            System.out.println("username\t" + username);
            System.out.println("photo\t" + photo);
            System.out.println("user_photo\t" + user_photo);
            System.out.println("caption\t" + caption);
            System.out.println("time\t" + time);

            System.out.println("");

            //now add story jpanel to top middle jpanel
            story obj = new story(username, photo, caption);
            obj.username_lb.setText(username);

            ImageIcon ic = new ImageIcon(user_photo);
            Image img = ic.getImage().getScaledInstance(obj.image_lb.getWidth(),
                    obj.image_lb.getHeight(), Image.SCALE_SMOOTH);
            ic = new ImageIcon(img);
            obj.image_lb.setIcon(ic);

            obj.setBounds(x, y, 200, 200);
            jPanel3.add(obj);
            x += 250;
            obj.repaint();
            jPanel3.repaint();
            jPanel3.revalidate();
        }
        jPanel3.setPreferredSize(new Dimension(x + 50, 250));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        create_post_bt = new javax.swing.JButton();
        create_story_bt = new javax.swing.JButton();
        change_password_bt = new javax.swing.JButton();
        user_profile_bt = new javax.swing.JButton();
        logout_bt = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 500));
        setSize(new java.awt.Dimension(800, 800));
        getContentPane().setLayout(null);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1138, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 248, Short.MAX_VALUE)
        );

        jScrollPane3.setViewportView(jPanel3);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(390, 0, 1140, 250);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1138, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 828, Short.MAX_VALUE)
        );

        jScrollPane5.setViewportView(jPanel4);

        getContentPane().add(jScrollPane5);
        jScrollPane5.setBounds(390, 250, 1140, 830);

        jPanel1.setLayout(null);

        create_post_bt.setText("Create new post");
        create_post_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_post_btActionPerformed(evt);
            }
        });
        jPanel1.add(create_post_bt);
        create_post_bt.setBounds(120, 220, 130, 23);

        create_story_bt.setText("create story");
        create_story_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_story_btActionPerformed(evt);
            }
        });
        jPanel1.add(create_story_bt);
        create_story_bt.setBounds(120, 280, 130, 23);

        change_password_bt.setText("change password");
        change_password_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                change_password_btActionPerformed(evt);
            }
        });
        jPanel1.add(change_password_bt);
        change_password_bt.setBounds(120, 340, 130, 23);

        user_profile_bt.setText("user profile");
        user_profile_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_profile_btActionPerformed(evt);
            }
        });
        jPanel1.add(user_profile_bt);
        user_profile_bt.setBounds(120, 400, 130, 23);

        logout_bt.setText("logout");
        logout_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logout_btActionPerformed(evt);
            }
        });
        jPanel1.add(logout_bt);
        logout_bt.setBounds(120, 460, 130, 23);

        jScrollPane1.setViewportView(jPanel1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(0, 0, 390, 1080);

        jPanel7.setLayout(null);
        jScrollPane4.setViewportView(jPanel7);

        getContentPane().add(jScrollPane4);
        jScrollPane4.setBounds(1530, 0, 390, 1080);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void create_post_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_post_btActionPerformed
        // TODO add your handling code here:
        create_post obj = new create_post();
        obj.setVisible(true);
        dispose();
    }//GEN-LAST:event_create_post_btActionPerformed

    private void create_story_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_story_btActionPerformed
        // TODO add your handling code here:
        create_story obj = new create_story();
        obj.setVisible(true);
        dispose();
    }//GEN-LAST:event_create_story_btActionPerformed

    private void change_password_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_change_password_btActionPerformed
        // TODO add your handling code here:
        change_password obj = new change_password();
        obj.setVisible(true);
        dispose();
    }//GEN-LAST:event_change_password_btActionPerformed

    private void user_profile_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_profile_btActionPerformed
        // TODO add your handling code here:
        user_profile obj = new user_profile();
        obj.setVisible(true);
        dispose();

    }//GEN-LAST:event_user_profile_btActionPerformed

    private void logout_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logout_btActionPerformed
        // TODO add your handling code here:
        int ans = JOptionPane.showConfirmDialog(this, "are you sure want to logout?",
                "delete confirmation", JOptionPane.YES_NO_OPTION);

        if (ans == JOptionPane.YES_OPTION) {
            global.username = "";
            getStarted obj = new getStarted();
            dispose();
        }
    }//GEN-LAST:event_logout_btActionPerformed

//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new homepage().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton change_password_bt;
    private javax.swing.JButton create_post_bt;
    private javax.swing.JButton create_story_bt;
    private javax.swing.JPanel jPanel1;
    private static javax.swing.JPanel jPanel3;
    private static javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton logout_bt;
    private javax.swing.JButton user_profile_bt;
    // End of variables declaration//GEN-END:variables
}
