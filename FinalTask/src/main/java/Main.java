import by.training.final_task.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        prop.put("useSSl", "false");//check
        Connection cn = null;

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            cn = DriverManager.getConnection(url, prop);
            Statement st = null;
            try {
                st = cn.createStatement();
                ResultSet rs = null;
                try {
                    rs = st.executeQuery("SELECT * FROM user");
                    List<User> lst = new ArrayList<>();
                    while (rs.next()) {
                        long id = rs.getLong(1);
                        String login = rs.getString(2);
                        String password = rs.getString(3);
                        byte role = rs.getByte(4);
                        String email = rs.getString(5);
                        String avatar = rs.getString(6);
                        String name = rs.getString(7);
                        String sername = rs.getString(8);
                        int phone = rs.getInt(9);
                        Date date = rs.getDate(10);
                        lst.add(new User(id, login, password, role, email,
                                avatar, name, sername, phone, date));
                    }
                    if (lst.size() > 0) {
                        System.out.println(lst);
                    } else {
                        System.out.println("Not found");
                    }

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
}