package by.training.final_task.dao.sql;

import by.training.final_task.dao.interfases.AbstractConnectionManager;
import by.training.final_task.dao.interfases.CouponUserDAO;
import by.training.final_task.entity.*;
import by.training.final_task.dao.PersistentException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class CouponUserDaoImpl extends AbstractDao<CouponUser>
        implements CouponUserDAO {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String GET_ALL_COUPONS_CURRENT_USER =
            "SELECT coupon.id, coupon.category_id, coupon.company_provider_id,"
                    + " coupon.name, coupon.picture, coupon.description, coupon.price, "
                    + "coupon.adding_date_time, coupon.holding_address, coupon_user.id,"
                    + " coupon_user.registration_date_time, user.blocking FROM coupon "
                    + "JOIN coupon_user ON coupon_user.coupon_id = coupon.id"
                    + " JOIN user ON user.id = coupon_user.user_id WHERE user.id = ?"
                    + " LIMIT ? OFFSET ?";
    private static final String GET_ALL_COUPONS_BETWEEN_DATES_FOR_CURRENT_USER =
            "SELECT coupon.id, coupon.category_id, coupon.company_provider_id,"
                    + " coupon.name, coupon.picture, coupon.description, coupon.price, "
                    + "coupon.adding_date_time, coupon.holding_address, coupon_user.id,"
                    + " coupon_user.registration_date_time, user.blocking FROM coupon"
                    + " JOIN coupon_user ON coupon_user.coupon_id = coupon.id"
                    + " JOIN user ON user.id = coupon_user.user_id WHERE user.id = ?"
                    + " AND (coupon_user.registration_date_time BETWEEN ? AND ?)"
                    + " LIMIT ? OFFSET ?";
    private static final String GET_AMOUNT_COUPONS_FOR_CURRENT_USER =
            "SELECT COUNT(coupon.id) FROM coupon JOIN coupon_user cu"
                    + " ON coupon.id = cu.coupon_id JOIN user u ON cu.user_id = u.id"
                    + " WHERE u.id = ?";
    private static final String
            GET_AMOUNT_COUPONS_FOR_CURRENT_USER_BETWEEN_DATES =
            "SELECT COUNT(coupon.id) FROM coupon JOIN coupon_user cu"
                    + " ON coupon.id = cu.coupon_id JOIN user u ON cu.user_id = u.id"
                    + " WHERE u.id = ? AND cu.registration_date_time BETWEEN ? AND ?";
    private static final String GET_ALL_AMOUNT_COUPONS =
            "SELECT COUNT(coupon_user.id) FROM coupon_user";
    private static final String ADD_COUPON_USER = "INSERT INTO coupon_user "
            + "(registration_date_time, coupon_id, user_id) VALUES (?,?,?)";
    private static final String GET_COUPON_USER = "SELECT coupon_user.id, "
            + "coupon_user.registration_date_time, coupon_user.coupon_id, "
            + "coupon_user.user_id FROM coupon_user WHERE coupon_user.id = ?";
    private static final String GET_ALL_COUPON_USERS = "SELECT coupon_user.id, "
            + "coupon_user.registration_date_time, coupon_user.coupon_id, "
            + "coupon_user.user_id FROM coupon_user ORDER BY id"
            + " LIMIT ? OFFSET ?";
    private static final String UPDATE_COUPON_USER = "UPDATE coupon_user SET "
            + "coupon_user.id=?, coupon_user.registration_date_time=?, "
            + "coupon_user.coupon_id=?, coupon_user.user_id=?"
            + " WHERE coupon_user.id = ?";

    public CouponUserDaoImpl(final AbstractConnectionManager newConnection) {
        super(newConnection);
    }

    //+
    @Override
    public List<Coupon> getAllCouponsCurrentUser(final long userId,
                                                 final int offset,
                                                 final int limit)
            throws PersistentException {
        List<Coupon> coupons = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_ALL_COUPONS_CURRENT_USER)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    coupons.add(takeNewCoupon(resultSet));
                }
            }
            return coupons;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    //+
    @Override
    public List<Coupon> getAllBetweenDatesCurrentUser(final long userId,
                                                      final LocalDate startDate,
                                                      final LocalDate endDate,
                                                      final int offset,
                                                      final int limit)
            throws PersistentException {
        List<Coupon> coupons = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(
                        GET_ALL_COUPONS_BETWEEN_DATES_FOR_CURRENT_USER)) {
            preparedStatement.setLong(1, userId);
            Date startingDate = Date.valueOf(startDate);
            LOGGER.log(Level.DEBUG, startingDate);
            preparedStatement.setDate(2, startingDate);
            Date endingDate = Date.valueOf(endDate);
            LOGGER.log(Level.DEBUG, endingDate);
            preparedStatement.setDate(3, endingDate);
            preparedStatement.setInt(4, limit);
            preparedStatement.setInt(5, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    coupons.add(takeNewCoupon(resultSet));
                }
            }
            return coupons;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    //+
    @Override
    public int getCountCouponNameCurrentUser(final long userId)
            throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_AMOUNT_COUPONS_FOR_CURRENT_USER)) {
            preparedStatement.setLong(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1);
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    //+
    @Override
    public int getCountBetweenDatesCurrentUser(long userId, LocalDate startDate,
                                               LocalDate endDate)
            throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(
                        GET_AMOUNT_COUPONS_FOR_CURRENT_USER_BETWEEN_DATES)) {
            preparedStatement.setLong(1, userId);
            Date startingDate = Date.valueOf(startDate);
            LOGGER.log(Level.DEBUG, startingDate);
            preparedStatement.setDate(2, startingDate);
            Date endingDate = Date.valueOf(endDate);
            LOGGER.log(Level.DEBUG, endingDate);
            preparedStatement.setDate(3, endingDate);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1);
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    //+
    @Override
    public int getAllCount() throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_ALL_AMOUNT_COUPONS)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1);
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public int create(final CouponUser newCouponUser)
            throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(ADD_COUPON_USER, PreparedStatement
                        .RETURN_GENERATED_KEYS)) {
            setPreparedStatement(newCouponUser, preparedStatement);
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            } catch (SQLException newE) {
                LOGGER.log(Level.WARN, newE.getMessage(), newE);
                throw new PersistentException("Could not get generated keys!\n"
                        + newE.getMessage(), newE);
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException("Fail in adding row!\n"
                    + newE.getMessage(), newE);
        }
        return 0;
    }

    //+
    @Override
    public CouponUser get() throws PersistentException {
        return get(1);
    }

    //+
    @Override
    public CouponUser get(final long id) throws PersistentException {
        CouponUser couponUser = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_COUPON_USER)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    couponUser = takeNewCouponUser(resultSet);
                }
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
        return couponUser;
    }

    //+
    @Override
    public List<CouponUser> getAll(final int offset, final int limit)
            throws PersistentException {
        List<CouponUser> couponUsers = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_ALL_COUPON_USERS)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    couponUsers.add(takeNewCouponUser(resultSet));
                }
            }
            return couponUsers;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public boolean update(final CouponUser element) throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(UPDATE_COUPON_USER)) {
            setPreparedStatement(element, preparedStatement);
            preparedStatement.setLong(5, element.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE);
            throw new PersistentException("Fail in updating coupon_user!\n"
                    + newE.getMessage(), newE);
        }
    }

    @Override
    public boolean delete(final CouponUser element) throws PersistentException {
        LOGGER.log(Level.WARN, "Invalid operation to delete"
                + " coupon user information.");
        throw new PersistentException("Invalid operation to delete "
                + " coupon user information.");
    }

    @Override
    public boolean delete(final long id) throws PersistentException {
        LOGGER.log(Level.WARN,
                "Invalid operation to delete  coupon user in.");
        throw new PersistentException(
                "Invalid operation to delete  coupon user information.");
    }

    private Coupon takeNewCoupon(final ResultSet newResultSet)
            throws SQLException {
        long id = newResultSet.getLong("id");
        Category category = new Category();
        CompanyProvider companyProvider = new CompanyProvider();
        long category_id = newResultSet.getLong("category_id");
        long company_provider_id = newResultSet
                .getLong("company_provider_id");
        category.setId(category_id);
        companyProvider.setId(company_provider_id);
        String name = newResultSet.getNString("name");
        String pathToPicture = newResultSet.getNString("picture");
        String description = newResultSet.getNString("description");
        BigDecimal price = newResultSet.getBigDecimal("price");
        LocalDate adding_date_time = newResultSet
                .getDate("adding_date_time").toLocalDate();
        String holding_address = newResultSet
                .getNString("holding_address");
        boolean blocking = newResultSet.getBoolean("blocking");
        return new Coupon(id, name, pathToPicture, description, price,
                adding_date_time, holding_address, category, companyProvider,
                blocking);
    }

    private void setPreparedStatement(final CouponUser coupUser,
                                      final PreparedStatement preparedStatement)
            throws SQLException {
        preparedStatement.setDate(1,
                Date.valueOf(coupUser.getRegistrationDateTime()));
        preparedStatement.setLong(2,
                coupUser.getCoupon().getId());
        preparedStatement.setLong(3, coupUser.getUser().getId());
    }

    private CouponUser takeNewCouponUser(final ResultSet newSet)
            throws SQLException {
        long id = newSet.getLong("id");
        LocalDate reg_date = newSet
                .getDate("registration_date_time").toLocalDate();
        long couponId = newSet.getLong("coupon_id");
        long user_id = newSet.getLong("user_id");
        Coupon coupon = new Coupon();
        User user = new User();
        return new CouponUser(id, reg_date, coupon, user);
    }
}
