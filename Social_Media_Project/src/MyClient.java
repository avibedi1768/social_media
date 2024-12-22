
import com.mashape.unirest.http.*;
import java.io.File;

public class MyClient {

    public static String user_signup(String name, String email, String pass,
            String mobile, String gender, File photo) {
        String ans = "";

        try {
            String url = "http://127.0.0.1:9000/user_signup";

            HttpResponse<String> res = Unirest.post(url)
                    .queryString("email", email)
                    .queryString("name", name)
                    .queryString("pass", pass)
                    .queryString("gender", gender)
                    .queryString("mobile", mobile)
                    .field("photo", photo)
                    .asString();

            ans = res.getBody();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ans;
    }

    public static String user_login(String user, String pass) {
        String ans = "";

        try {
            String url = "http://127.0.0.1:9000/user_login";
//            System.out.println("url in client = " + url);

            HttpResponse<String> res = Unirest.get(url)
                    .queryString("user", user)
                    .queryString("pass", pass)
                    .asString();
//            System.out.println("response sent from client");

            ans = res.getBody();
//            System.out.println("ans from client = " + ans);
        } catch (Exception ex) {
//            System.out.println("error in myclient");
            ex.printStackTrace();
        }

        return ans;
    }

    public static String get_accounts(String username) {
        String ans = "";

        try {
            String url = "http://127.0.0.1:9000/get_accounts";
            HttpResponse<String> res = Unirest.get(url)
                    .queryString("username", username)
                    .asString();
            ans = res.getBody();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ans;
    }

    public static String create_post(String username, String caption, File photo) {
        String ans = "";

        try {
            String url = "http://127.0.0.1:9000/create_post";
            HttpResponse<String> res = Unirest.post(url)
                    .queryString("username", username)
                    .queryString("caption", caption)
                    .field("photo", photo)
                    .asString();

            ans = res.getBody();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ans;
    }

    public static String get_posts() {
        String ans = "";
        try {
            String url = "http://127.0.0.1:9000/get_posts";
            HttpResponse<String> res = Unirest.get(url).asString();

            ans = res.getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ans;
    }

    public static String create_story(String username, File photo, String caption) {
        String ans = "";
        try {
            String url = "http://127.0.0.1:9000/create_story";
            HttpResponse<String> res = Unirest.post(url)
                    .queryString("username", username)
                    .queryString("caption", caption)
                    .field("photo", photo)
                    .asString();
            ans = res.getBody();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ans;
    }

    public static String get_stories() {
        String ans = "";

        try {
            String url = "http://127.0.0.1:9000/get_stories";

            HttpResponse<String> res = Unirest.get(url).asString();
            ans = res.getBody();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ans;
    }

    public static String change_password(String old_pass, String new_pass) {
        String ans = "";

        try {
            String url = "http://127.0.0.1:9000/change_password";

            HttpResponse<String> res = Unirest.get(url)
                    .queryString("old_pass", old_pass)
                    .queryString("new_pass", new_pass)
                    .asString();
            ans = res.getBody();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ans;
    }

    public static String get_self_posts() {
        String ans = "";

        try {
            String url = "http://127.0.0.1:9000/get_self_posts";
            HttpResponse<String> res = Unirest.get(url).asString();
            ans = res.getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ans;
    }

    public static String delete_post(int post_id) {
        String ans = "";

        try {
            String url = "http://127.0.0.1:9000/delete_post";
            HttpResponse<String> res = Unirest.get(url)
                    .queryString("post_id", post_id + "")
                    .asString();

            ans = res.getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ans;
    }

    public static String update_user(String email, String mobile, String gender, File photo) {
        String ans = "";

        try {
            String url = "http://127.0.0.1:9000/update_user";
            HttpResponse<String> res = Unirest.post(url)
                    .queryString("email", email)
                    .queryString("mobile", mobile)
                    .queryString("gender", gender)
                    .field("photo", photo)
                    .asString();

            ans = res.getBody();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ans;
    }
}
