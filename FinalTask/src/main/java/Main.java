import by.training.final_task.dao.interfases.DaoFactory;
import by.training.final_task.dao.interfases.UserDAO;
import by.training.final_task.dao.sql.*;
import by.training.final_task.entity.*;
import by.training.final_task.dao.PersistentException;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.implimentation.CategoryServiceImpl;
import by.training.final_task.service.implimentation.CouponServiceImpl;
import by.training.final_task.service.implimentation.UserServiceImpl;
import by.training.final_task.service.interfaces.CategoryService;

import java.math.BigDecimal;
import java.sql.Connection;
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
            User user = new User(7, "user5", "password",
                    Role.USER, "email@mail.ru", "passtoavatar/ru",
                    "Fridrih", "Brown", 336494955,
                    LocalDate.of(2018,01,15), false);
            Coupon coupon = new Coupon(4, "Name",
                    "path", "description",
                    new BigDecimal(222.02), LocalDate.of(2019, 9, 1),
                    "address", new Category(1), new CompanyProvider(1), false);


            DaoFactory dao = new DaoFactoryImpl();

            CouponServiceImpl comp = new CouponServiceImpl(dao);
            System.out.println(comp.create(coupon));

        } catch (SQLException e) { // для 1-го блока try
            System.err.println("DB connection error: " + e);
        } catch (ServiceException newE) {
            newE.printStackTrace();
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
