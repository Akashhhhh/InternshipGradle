package ecommerce.util;

import java.sql.Connection;
import java.sql.DriverManager;

import static java.lang.Class.forName;

public class CreateConnection {
    public static Connection create(){
        java.sql.Connection connection = null;
        try {

            forName("org.postgresql.Driver");

            connection = DriverManager.getConnection("jdbc:postgresql://0.0.0.0:49153/ecomdb", "akash", "akash");



        } catch (Exception E) {
            System.out.println(E);
        }
        return connection;
    }
}
