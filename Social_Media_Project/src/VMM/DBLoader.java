package VMM;

import java.sql.*;

public class DBLoader {

    public static ResultSet executeSQL(String sql) {
        try {
            ///////////##CODE////////////////   
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("drivers loaded");

            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/social_media_project", "root", "admin");
            System.out.println("connection done");

            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println("statement done");

            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("result set created");

            return rs;
            ///////////##CODE ENDS////////////

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
