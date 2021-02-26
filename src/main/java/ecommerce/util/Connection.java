package ecommerce.util;

import java.sql.DriverManager;
import java.util.function.Supplier;

import static java.lang.Class.forName;

public class Connection {
    private static java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Connection.class.getName());

    public static java.sql.Connection create() {
        java.sql.Connection connection = null;
        try {

            forName("org.postgresql.Driver");

            connection = DriverManager.getConnection("jdbc:postgresql://0.0.0.0:49153/ecomdb", "akash", "akash");


        } catch (Exception E) {
            logger.info((Supplier<String>) E);
        }
        return connection;
    }


}
