
import javax.swing.*;
import java.awt.*;

public class story extends javax.swing.JPanel {

    String username, photo, caption;

    public story(String username, String photo, String caption) {
        initComponents();

        this.username = username;
        this.photo = photo;
        this.caption = caption;

        System.out.println("username\t" + username);
        System.out.println("photo\t" + photo);
        System.out.println("caption\t" + caption);

        ImageIcon ic = new ImageIcon("src/uploads/1724506888855.jpeg");
        Image img = ic.getImage().getScaledInstance(image_lb.getWidth(),
                image_lb.getHeight(), Image.SCALE_SMOOTH);
        ic = new ImageIcon(img);
        image_lb.setIcon(ic);

        setSize(200, 200);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        image_lb = new javax.swing.JLabel();
        username_lb = new javax.swing.JLabel();

        setLayout(null);

        image_lb.setText("img");
        image_lb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                image_lbMouseClicked(evt);
            }
        });
        add(image_lb);
        image_lb.setBounds(20, 10, 160, 130);

        username_lb.setText("username");
        username_lb.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(username_lb);
        username_lb.setBounds(60, 150, 100, 16);
    }// </editor-fold>//GEN-END:initComponents

    private void image_lbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_image_lbMouseClicked
        // TODO add your handling code here:
        show_story obj = new show_story(username, photo, caption);
        obj.setVisible(true);
    }//GEN-LAST:event_image_lbMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel image_lb;
    public javax.swing.JLabel username_lb;
    // End of variables declaration//GEN-END:variables
}
