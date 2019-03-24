package com.hodge.daniel.jdbctutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class PersonRepository {

    public void savePerson(Person p) {

        String url = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=bereanboy&ssl=false";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);
            Statement stmt = connection.createStatement();

            String formattedDob = new SimpleDateFormat( "yyyy-MM-dd").format(p.getDob());

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO \"jdbc-tutorial\".person (id, first_name, last_name, dob, comment) VALUES(");
            sql.append(p.getId()).append(", '");
            sql.append(p.getFirstName()).append("', '");
            sql.append(p.getLastName()).append("', '");
            sql.append(formattedDob).append("', '");
            sql.append(p.getComment());
            sql.append("')");
            System.out.println(sql.toString());
            stmt.executeUpdate(sql.toString());

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
