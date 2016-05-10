package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import play.db.DB;

/**
 * Created by Jasmine on 12/7/15.
 */
public class Information {

    private int id;
    private String name;
    private int age;
    private String gender;
    private String email;

    private String ip;
    private long ts;

    public Information(String name, int age, String gender, String email, String ip, long ts) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.ip = ip;
        this.ts = ts;
    }

    public Information(int id, String name, int age, String gender, String email, String ip, long ts) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.ip = ip;
        this.ts = ts;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public boolean saveGuest() throws SQLException {
        Connection connection = DB.getConnection();
        String query = "insert into guest (name, age, gender,email, ip, timestamp) values (?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1,name);
        statement.setInt(2, age);
        statement.setString(3,gender);
        statement.setString(4,email);
        statement.setString(5,ip);
        statement.setLong(6, ts);

        boolean res = statement.execute();
        connection.close();
        return res;
    }


}
