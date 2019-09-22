import by.training.final_task.dao.sql.*;
import by.training.final_task.entity.Category;
import by.training.final_task.entity.Role;
import by.training.final_task.entity.User;
import by.training.final_task.exception.PersistentException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/stock_gift_coupons_db?serverTimezone=UTC";
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "root");
        prop.put("autoReconnect", "true");
        prop.put("characterEncoding", "UTF-8");
        prop.put("useUnicode", "true");
        prop.put("useSSL", "false");
        Connection cn = null;

        try {
            cn = DriverManager.getConnection(url, prop);
            /*User user = new User(8, "user6", "password",
                    Role.USER, "email@mail.ru", "passtoavatar/ru",
                    "Fridrih", "Brown", 336494955,
                    LocalDate.of(2018,01,15), false);*/

            CouponUserDaoImpl comp = new CouponUserDaoImpl(cn);
            System.out.println(comp.getAllBetweenDatesCurrentUser(5, LocalDate.of(2017, 11, 26),
                    LocalDate.of(2018, 1, 2), 0, 10));
            //System.out.println(comp.getAllCouponsCurrentUser(4, 0, 10));

        } catch (SQLException e) { // для 1-го блока try
            System.err.println("DB connection error: " + e);
        } catch (PersistentException eNew) {
            eNew.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    System.err.println("Сonnection close error: " + e);
                }
            }
        }
    }
}


/*
public class Main {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/stock_gift_coupons_db?serverTimezone=UTC";
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "root");
        prop.put("autoReconnect", "true");
        prop.put("characterEncoding", "UTF-8");
        prop.put("useUnicode", "true");
        prop.put("useSSl", "false");//check
        Connection cn = null;

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            cn = DriverManager.getConnection(url, prop);
            Statement st = null;
            try {
                st = cn.createStatement();


                } finally {
                    if (rs != null) { // был ли создан ResultSet
                        rs.close();
                    } else {
                        System.err.println("ошибка во время чтения из БД");
                    }
                }
            } finally {
                if (st != null) {
                    st.close();
                } else {
                    System.err.println("Statement не создан");
                }
            }
        } catch (SQLException e) { // для 1-го блока try
            System.err.println("DB connection error: " + e);
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    System.err.println("Сonnection close error: " + e);
                }
            }
        }
    }
}*/
