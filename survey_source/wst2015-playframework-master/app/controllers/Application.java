package controllers;

import models.Information;
import play.data.DynamicForm;
import play.data.Form;
import play.db.DB;
import play.mvc.*;

import views.html.*;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application extends Controller {

    public Result index() {
        return ok(index.render());
    }

    public Result getAll(){
        try {
            List<Information> list = getAllGuests();
            if (list.isEmpty())
                return ok();
            else
                return ok(results.render(list));
        } catch (SQLException e) {
            e.printStackTrace();
            return ok(fail.render("Internal server error."));
        }
    }

    public Result submit() {
        DynamicForm in = Form.form().bindFromRequest();
        String name = in.get("name");
        String age = in.get("age");
        String email = in.get("email");
        String gender = in.get("gender");
        String result = validate(name,age,email,gender);
        if (result == null) {
            int intAge = new Integer(age);
            long ts = new Date().getTime();
            String ip = request().remoteAddress();
            Information info = new Information(name,intAge,gender,email,ip,ts/1000);
            try {
                info.saveGuest();
                return ok(success.render());
            } catch (SQLException e) {
                e.printStackTrace();
                return ok(fail.render("Internal server error."));
            }
        }
        else
            return ok(fail.render(result));
    }

    public Result delete() {
        DynamicForm in = Form.form().bindFromRequest();
        Collection<String> values = in.data().values();
        for (String id:values) {
            try {
                deleteGuest(new Integer(id));
            } catch (Exception e) {
                return ok(fail.render("Internal server error."));
            }
        }
        return ok(success.render());
    }

    private String validate(String name, String age, String email, String gender){
        if (name == null || age == null || email == null || gender == null)
            return "No field may be empty!";
        if (name.trim().isEmpty() || age.trim().isEmpty() || email.trim().isEmpty() || gender.trim().isEmpty())
            return "No field may be empty!";

        if (name.length() > 30 || email.length() > 50)
            return "Name and email must not exceed the server limit.";

        Pattern nameString = Pattern.compile("\\d");
        Matcher m = nameString.matcher(name);
        if (m.find()) return "Please do not include numbers in your name!";

        Pattern ageString = Pattern.compile("^\\d{1,2}$");
        Matcher m1 = ageString.matcher(age);
        if (!m1.find()) return "Please input a valid age!";

        Pattern emailString = Pattern.compile("^[a-z0-9A-Z_.]+@[a-z0-9A-Z._-]+.[a-zA-Z]+$");
        Matcher m2 = emailString.matcher(email);
        if (!m2.find()) return "Please input a valid email!";

        return null;
    }

    private List<Information> getAllGuests() throws SQLException {
        Connection connection = DB.getConnection();
        Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT * FROM guest WHERE deleted=0";
        ResultSet resultset = statement.executeQuery(query);
        ArrayList<Information> array = new ArrayList<>();
        while (resultset.next()) {
            array.add(new Information(
                    resultset.getInt("gid"),
                    resultset.getString("name"),
                    resultset.getInt("age"),
                    resultset.getString("gender"),
                    resultset.getString("email"),
                    resultset.getString("ip"),
                    resultset.getInt("timestamp")));
        }
        connection.close();
        return array;
    }

    private void deleteGuest(int id) throws SQLException {
        Connection connection = DB.getConnection();
        String query = "UPDATE guest SET deleted=1 where gid=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1,id);
        statement.execute();
        connection.close();
    }

}
