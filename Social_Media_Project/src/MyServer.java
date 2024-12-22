
import VMM.DBLoader;
import com.vmm.JHTTPServer;
import java.util.Properties;
import java.sql.*;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyServer extends JHTTPServer {

    MyServer(int portno) throws Exception {
        super(portno);

        scheduleOldStoriesDeletion();
    }

    //DELETE OLD STORIES
    public void deleteOldStories() {
        String dbURL = "jdbc:mysql://localhost:3306/social_media_project";
        String dbUser = "root";
        String dbPassword = "admin";
        String query = "delete from story_table where date_time < ?";

        try ( Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);  PreparedStatement pstmt = conn.prepareStatement(query)) {
            Timestamp cutoffTime = Timestamp.from(Instant.now().minusSeconds(24 * 60 * 60));
            pstmt.setTimestamp(1, cutoffTime);

            int rowsDeleted = pstmt.executeUpdate();
            System.out.println(rowsDeleted + " old pictures deleted");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void scheduleOldStoriesDeletion() {
        ScheduledExecutorService schedular = Executors.newScheduledThreadPool(1);
        Runnable task = this::deleteOldStories;

        //initial delay of 0 means it rns immediately
        schedular.scheduleAtFixedRate(task, 0, 1, TimeUnit.HOURS);
    }

    @Override
    public Response serve(String uri, String method, Properties header, Properties parms, Properties files) {
        System.out.println("received uri = " + uri);
        if (uri.equals("/user_signup")) {
            String ans = "";

            String email = parms.getProperty("email");
            String username = parms.getProperty("name");
            String pass = parms.getProperty("pass");
            String gender = parms.getProperty("gender");
            String mobile = parms.getProperty("mobile");
            String photo = saveFileOnServerWithRandomName(files, parms, "photo", "src/myuploads/");

            ResultSet rs = DBLoader.executeSQL("select * from user_table where username = \'" + username + "\'");

            try {
                if (rs.next()) {
                    ans = "fail";
                } else {
                    rs.moveToInsertRow();

                    rs.updateString("email", email);
                    rs.updateString("username", username);
                    rs.updateString("password", pass);
                    rs.updateString("gender", gender);
                    rs.updateString("mobile", mobile);
                    rs.updateString("photo", "src/myuploads/" + photo);

                    rs.insertRow();

                    ans = "pass";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Response res = new Response(HTTP_OK, "text/plain", ans);
            return res;

        } else if (uri.equals("/user_login")) {
            String ans = "";
//            System.out.println("inside server");

            String user = parms.getProperty("user");
            String pass = parms.getProperty("pass");
//            System.out.println("got data");

            ResultSet rs = DBLoader.executeSQL("select * from user_table where username = \'" + user + "\' and password = \'" + pass + "\'");
//            System.out.println("response received");

            try {
                if (rs.next()) {
                    ans = "pass";
                } else {
                    ans = "fail";
                }
//                System.out.println("ans from server = " + ans);
            } catch (Exception ex) {
//                System.out.println("error in myserver");
                ex.printStackTrace();
            }

            Response res = new Response(HTTP_OK, "text/plain", ans);
            return res;
        } else if (uri.equals("/get_accounts")) {
            String ans = "";
            try {
                String user = parms.getProperty("username");
                System.out.println("global username = " + global.username);
                ResultSet rs = DBLoader.executeSQL("select*from user_table where username != '" + user + "'");

                String username, photo;
                while (rs.next()) {
                    username = rs.getString("username");
                    photo = rs.getString("photo");

                    ans += username + "$" + photo + "^";
                }
                //$ for splitting within usernme and photo
                //^ for splitting btw diff objects
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Response res = new Response(HTTP_OK, "text/plain", ans);
            return res;
        } else if (uri.equals("/create_post")) {
            String ans = "";

            String username = parms.getProperty("username");
            String caption = parms.getProperty("caption");
            String photo = saveFileOnServerWithRandomName(files, parms, "photo", "src/myuploads/");

            ResultSet rs = DBLoader.executeSQL("select * from posts");

            try {
                while (rs.next()) {
                }

                rs.moveToInsertRow();

                rs.updateString("photo", "src/myuploads/" + photo);
                rs.updateString("caption", caption);
                rs.updateString("username", username);

                rs.insertRow();

                ans = "pass";

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Response res = new Response(HTTP_OK, "text/plain", ans);
            return res;
        } else if (uri.equals("/get_posts")) {
            String ans = "";

            try {
                //select from followers table first
                ResultSet rs1 = DBLoader.executeSQL("select * from followers where followed_by = '"
                        + global.username + "'");

                while (rs1.next()) {
                    String followed_to = rs1.getString("followed_to");

                    //now fetch posts of each username (where username = followed_to)
                    ResultSet rs2 = DBLoader.executeSQL("select * from posts where username = '"
                            + followed_to + "'");

                    //now update the ans:
                    while (rs2.next()) {
                        String username = rs2.getString("username");
                        String caption = rs2.getString("caption");
                        String photo = rs2.getString("photo");
                        int post_id = rs2.getInt("post_id");

                        ans += username + "$" + caption + "$" + photo + "$" + post_id + "^";
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Response res = new Response(HTTP_OK, "text.plain", ans);
            return res;
        } else if (uri.equals("/create_story")) {
            String ans = "";
            try {
                String username = parms.getProperty("username");
                String caption = parms.getProperty("caption");
                String photo = saveFileOnServerWithRandomName(files, parms, "photo", "src/myuploads/");

                ResultSet rs = DBLoader.executeSQL("select * from story_table");
                while (rs.next()) {
                }

                rs.moveToInsertRow();

                rs.updateString("username", username);
                rs.updateString("caption", caption);
                rs.updateString("photo", "src/myuploads/" + photo);

                rs.insertRow();

                ans = "pass";

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Response res = new Response(HTTP_OK, "text/plain", ans);
            return res;
        } else if (uri.equals("/get_stories")) {
            String ans = "";

            try {
                String sql = "select * from story_table where username in "
                        + "(select followed_to from followers where followed_by = '" + global.username + "')";
                ResultSet rs = DBLoader.executeSQL(sql);

                while (rs.next()) {
                    String story_id = rs.getInt("story_id") + "";
                    String username = rs.getString("username");
                    String photo = rs.getString("photo");
                    String caption = rs.getString("caption");
                    String time = rs.getTimestamp("date_time") + "";

                    ResultSet rs2 = DBLoader.executeSQL("select * from user_table where username = '" + username + "'");
                    rs2.next();
                    String user_photo = rs2.getString("photo");

                    ans += story_id + "$" + username + "$" + photo + "$" + user_photo + "$" + caption + "$" + time + "^";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Response res = new Response(HTTP_OK, "text/plain", ans);
            return res;
        } else if (uri.equals("/change_password")) {
            String ans = "fail";

            String old_pass = parms.getProperty("old_pass");
            String new_pass = parms.getProperty("new_pass");
            try {
                System.out.println("change initiated");
                ResultSet rs = DBLoader.executeSQL("select * from user_table where username = '"
                        + global.username + "' and password = '" + old_pass + "'");
                System.out.println("query done");
                if (rs.next()) {
                    System.out.println("change pass started");
                    rs.updateString("password", new_pass);
                    rs.updateRow();

                    System.out.println("change password done");

                    ans = "pass";
                } else {
                    System.out.println("pass not matching");
                    ans = "mismatch";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Response res = new Response(HTTP_OK, "text/plain", ans);
            return res;
        } else if (uri.equals("/get_self_posts")) {
            String ans = "";

            try {
                ResultSet rs = DBLoader.executeSQL("select * from posts where username = '" + global.username + "'");

                while (rs.next()) {
                    int post_id = rs.getInt("post_id");
                    String photo = rs.getString("photo");
                    String caption = rs.getString("caption");

                    ans += post_id + "$" + photo + "$" + caption + "^";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Response res = new Response(HTTP_OK, "text/plain", ans);
            return res;
        } else if (uri.equals("/delete_post")) {
            String ans = "fail";

            try {
                int post_id = Integer.parseInt(parms.getProperty("post_id"));
                ResultSet rs = DBLoader.executeSQL("select * from posts where post_id = " + post_id);
                rs.next();

                rs.deleteRow();
                ans = "pass";
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Response res = new Response(HTTP_OK, "text/plain", ans);
            return res;
        } else if (uri.equals("/update_user")) {
            String ans = "fail";

            try {
                String email = parms.getProperty("email");
                String mobile = parms.getProperty("mobile");
                String gender = parms.getProperty("gender");
                String photo = saveFileOnServerWithRandomName(files, parms, "photo", "src/myuploads");

                ResultSet rs = DBLoader.executeSQL("select * from user_table where username = '" + global.username + "'");
                rs.next();

                rs.updateString("email", email);
                rs.updateString("mobile", mobile);
                rs.updateString("gender", gender);
                rs.updateString("photo", "src/myuploads/" + photo);

                rs.updateRow();

                ans = "pass";
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Response res = new Response(HTTP_OK, "text/plain", ans);
            return res;
        } else {
            String ans = "hello world";
            Response res = new Response(HTTP_OK, "text/plain", ans);
            return res;
        }
    }

//    public static void main(String[] args) {
//        try {
//            MyServer obj = new MyServer(9000);
//            Thread.sleep(10000000);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
}
