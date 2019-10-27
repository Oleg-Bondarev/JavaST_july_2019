package by.training.final_task.dao.sql;

import by.training.final_task.dao.interfases.AbstractConnectionManager;
import by.training.final_task.dao.interfases.CouponDAO;
import by.training.final_task.entity.Category;
import by.training.final_task.entity.CompanyProvider;
import by.training.final_task.entity.Coupon;
import by.training.final_task.dao.PersistentException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Represent implementation of the CouponDAO.
 * Contains database queries and methods for database queries.
 * */
public class CouponDaoImpl extends AbstractDao<Coupon> implements CouponDAO {
    /**
     * Class logger.
     * */
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String ADD_COUPON =
            "INSERT INTO coupon (category_id, company_provider_id, name,"
            + " picture, description, price, adding_date_time, holding_address,"
            + " blocking) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_COUPON =
            "UPDATE coupon SET category_id=?, company_provider_id=?, name=?,"
            + " picture=?, description=?, price=?, adding_date_time=?,"
            + " holding_address=?, blocking=?  WHERE coupon.id=?";
    private static final String UPDATE_COUPON_STATUS =
            "UPDATE coupon SET blocking = true WHERE coupon.id=?";
    /*private static final String DELETE_COUPON = "DELETE FROM coupon
     WHERE id = ?";*/
    private static final String GET_COUPON =
            "SELECT coupon.id, coupon.category_id, coupon.company_provider_id,"
            + " coupon.name, coupon.picture, coupon.description, coupon.price, "
            + "coupon.adding_date_time, coupon.holding_address, coupon.blocking"
            + " FROM coupon WHERE coupon.id = ?";
    private static final String GET_IF_AVAILABLE =
            "SELECT coupon.id, coupon.category_id, coupon.company_provider_id,"
            + " coupon.name, coupon.picture, coupon.description, coupon.price, "
            + "coupon.adding_date_time, coupon.holding_address, coupon.blocking"
            + " FROM coupon WHERE coupon.id = ? AND coupon.blocking=false";
    private static final String GET_ALL_COUPONS =
            "SELECT coupon.id, coupon.category_id, coupon.company_provider_id,"
            + " coupon.name, coupon.picture, coupon.description, coupon.price, "
            + "coupon.adding_date_time, coupon.holding_address, coupon.blocking"
            + " FROM coupon WHERE coupon.blocking = false ORDER BY id"
            + " LIMIT ? OFFSET ?";
    private static final String GET_ALL_COUPONS_BY_COMPANY_NAME =
            "SELECT coupon.id, coupon.category_id, coupon.company_provider_id,"
            + " coupon.name, coupon.picture, coupon.description, coupon.price, "
            + "coupon.adding_date_time, coupon.holding_address, coupon.blocking"
            + " FROM coupon JOIN company_provider cp"
            + " on coupon.company_provider_id = cp.id WHERE cp.name = ?"
            + " AND coupon.blocking=false ORDER BY id LIMIT ? OFFSET ?";
    private static final String GET_ALL_COUPONS_BY_CATEGORY =
            "SELECT coupon.id, coupon.category_id, coupon.company_provider_id,"
            + " coupon.name, coupon.picture, coupon.description, coupon.price, "
            + "coupon.adding_date_time, coupon.holding_address, coupon.blocking"
            + " FROM coupon WHERE coupon.category_id = ?"
            + " AND coupon.blocking=false LIMIT ? OFFSET ?";
    private static final String GET_ALL_COUPONS_BY_NAME =
            "SELECT coupon.id, coupon.category_id, coupon.company_provider_id,"
            + " coupon.name, coupon.picture, coupon.description, coupon.price, "
            + "coupon.adding_date_time, coupon.holding_address, coupon.blocking"
            + " FROM coupon WHERE coupon.name = ? AND coupon.blocking=false "
            + "LIMIT ? OFFSET ?";
    private static final String GET_ALL_COUPONS_BY_PRICE_RANGE =
            "SELECT coupon.id, coupon.category_id, coupon.company_provider_id,"
            + " coupon.name, coupon.picture, coupon.description, coupon.price, "
            + "coupon.adding_date_time, coupon.holding_address, coupon.blocking"
            + " FROM coupon WHERE coupon.blocking=false AND coupon.price"
            + " BETWEEN ? AND ? LIMIT ? OFFSET ?";
    private static final String GET_ALL_COUPONS_GREATER_THAN_MIN_PRICE_RANGE =
            "SELECT coupon.id, coupon.category_id, coupon.company_provider_id,"
            + " coupon.name, coupon.picture, coupon.description, coupon.price, "
            + "coupon.adding_date_time, coupon.holding_address, coupon.blocking"
            + " FROM coupon WHERE coupon.blocking=false AND coupon.price >= ?"
            + "LIMIT ? OFFSET ?";
    private static final String GET_ALL_AVAILABLE_COUPONS =
            "SELECT coupon.id, coupon.category_id, coupon.company_provider_id,"
            + " coupon.name, coupon.picture, coupon.description, coupon.price, "
            + "coupon.adding_date_time, coupon.holding_address, coupon.blocking"
            + " FROM company_provider JOIN coupon"
            + " ON company_provider.id = coupon.company_provider_id"
            + " WHERE company_provider.blocking = false"
            + " AND coupon.blocking=false LIMIT ? OFFSET ?";
    private static final String GET_AMOUNT_OF_ALL_COUPON =
            "SELECT COUNT(coupon.id) FROM coupon WHERE coupon.blocking=false";
    private static final String GET_AMOUNT_BY_CATEGORY =
            "SELECT COUNT(coupon.id) FROM coupon JOIN category"
            + " ON(category.id = coupon.category_id)"
            + " WHERE category.id = ? AND coupon.blocking=false";
    private static final String GET_AMOUNT_BY_PRICE_RANGE =
            "SELECT COUNT(coupon.id) FROM coupon"
            + " WHERE coupon.blocking=false AND coupon.price BETWEEN ? AND ?";
    private static final String GET_AMOUNT_BY_PRICE_GREATER_THAN_MIN =
            "SELECT COUNT(coupon.id) FROM coupon WHERE coupon.blocking=false "
            + "AND coupon.price>=?";
    private static final String GET_AMOUNT_BY_COMPANY_PROVIDER =
            "SELECT COUNT(coupon.id) FROM coupon INNER JOIN company_provider"
            + " ON(company_provider.id = coupon.id)"
            + " WHERE company_provider.name = ? AND coupon.blocking=false";
    private static final String GET_AMOUNT_BY_COUPON_NAME =
            "SELECT COUNT(coupon.id) FROM coupon WHERE coupon.name = ?"
            + " AND coupon.blocking=false";


    public CouponDaoImpl(final AbstractConnectionManager newConnection) {
        super(newConnection);
    }


    @Override
    public Coupon get(final long id) throws PersistentException {
        Coupon coupon = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_COUPON)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    coupon = getNewCoupon(resultSet);
                }
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
        return coupon;
    }

    //+
    @Override
    public List<Coupon> getAll(final int offset, final int limit)
            throws PersistentException {
        List<Coupon> coupons = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_ALL_COUPONS)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    coupons.add(getNewCoupon(resultSet));
                }
            }
            return coupons;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public Coupon getIfAvailable(final long id) throws PersistentException {
        Coupon coupon = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_IF_AVAILABLE)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    coupon = getNewCoupon(resultSet);
                }
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
        return coupon;
    }

    //+
    @Override
    public List<Coupon> getAllByCompanyProvider(final String name,
            final int offset, final int limit) throws PersistentException {
        List<Coupon> coupons = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_ALL_COUPONS_BY_COMPANY_NAME)) {
            preparedStatement.setNString(1, name);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    coupons.add(getNewCoupon(resultSet));
                }
            }
            return coupons;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }
    //?
    @Override
    public List<Coupon> getAllByCategory(final Category category,
                                         final int offset, final int limit)
            throws PersistentException {
        List<Coupon> coupons = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_ALL_COUPONS_BY_CATEGORY)) {
            preparedStatement.setLong(1, category.getId());
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    coupons.add(getNewCoupon(resultSet));
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
    public List<Coupon> getAllByName(final String name, final int offset,
                                     final int limit) throws PersistentException {
        List<Coupon> coupons = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_ALL_COUPONS_BY_NAME)) {
            preparedStatement.setNString(1, name);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    coupons.add(getNewCoupon(resultSet));
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
    public List<Coupon> getAllByPriceRange(final BigDecimal minBorder,
            final BigDecimal maxBorder, final int offset, int limit)
            throws PersistentException {
        List<Coupon> coupons = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_ALL_COUPONS_BY_PRICE_RANGE)) {
            preparedStatement.setBigDecimal(1, minBorder);
            preparedStatement.setBigDecimal(2, maxBorder);
            preparedStatement.setInt(3, limit);
            preparedStatement.setInt(4, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    coupons.add(getNewCoupon(resultSet));
                }
            }
            return coupons;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public List<Coupon> getAllGreaterThanCurrentPrice(final BigDecimal minBorder,
                                      final int offset, final int limit)
            throws PersistentException {
        List<Coupon> coupons = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
            .prepareStatement(GET_ALL_COUPONS_GREATER_THAN_MIN_PRICE_RANGE)) {
            preparedStatement.setBigDecimal(1, minBorder);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    coupons.add(getNewCoupon(resultSet));
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
    public List<Coupon> getAllAvailableCoupons(final int offset,
           final int limit) throws PersistentException {
        List<Coupon> coupons = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_ALL_AVAILABLE_COUPONS)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    coupons.add(getNewCoupon(resultSet));
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
    public int getAmountOfAllCoupons() throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_AMOUNT_OF_ALL_COUPON)) {
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
    public int getAmountByCategory(final Category category)
            throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_AMOUNT_BY_CATEGORY)) {
            preparedStatement.setLong(1, category.getId());
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
    public int getAmountByCompanyProvider(final String companyName)
            throws PersistentException {
        String query = GET_AMOUNT_BY_COMPANY_PROVIDER;
        return getAmountWithStringParameter(query, companyName);
    }
    //+
    @Override
    public int getAmountByName(final String name) throws PersistentException {
        String query = GET_AMOUNT_BY_COUPON_NAME;
        return getAmountWithStringParameter(query, name);
    }

    //+
    @Override
    public int getAmountByPriceRange(final BigDecimal minBorder,
             final BigDecimal maxBorder) throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_AMOUNT_BY_PRICE_RANGE)) {
            preparedStatement.setBigDecimal(1, minBorder);
            preparedStatement.setBigDecimal(2, maxBorder);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
        return 0;
    }

    @Override
    public int getAmountGreaterThanCurrentPrice(final BigDecimal minBorder)
            throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_AMOUNT_BY_PRICE_GREATER_THAN_MIN)) {
            preparedStatement.setBigDecimal(1, minBorder);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
        return 0;
    }

    //+
    @Override
    public boolean updateAvailableStatus(final long id)
            throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(UPDATE_COUPON_STATUS)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE);
            throw new PersistentException("Fail in updating coupon status!\n"
                    + newE.getMessage(), newE);
        }
    }

    @Override
    public int create(final Coupon newCoupon) throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(ADD_COUPON, PreparedStatement.RETURN_GENERATED_KEYS)) {
            setPreparedStatement(newCoupon, preparedStatement);
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
    //нужен ли?
    @Override
    public Coupon get() throws PersistentException {
        return get(1);
    }

    @Override
    public boolean update(final Coupon newCoupon)
            throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(UPDATE_COUPON)) {
            setPreparedStatement(newCoupon, preparedStatement);
            preparedStatement.setLong(10, newCoupon.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE);
            throw new PersistentException("Fail in updating coupon!\n"
                    + newE.getMessage(), newE);
        }
    }

    @Override
    public boolean delete(final Coupon newCoupon)
            throws PersistentException {
        LOGGER.log(Level.WARN, "Invalid operation to delete coupon.");
        throw new PersistentException("Invalid operation to delete coupon." +
               " You can change coupon status on unavailable.");
    }

    @Override
    public boolean delete(final long id) throws PersistentException {
        LOGGER.log(Level.WARN, "Invalid operation to delete coupon.");
        throw new PersistentException("Invalid operation to delete coupon." +
                " You can change coupon status on unavailable.");
    }

    private void setPreparedStatement(final Coupon newCoupon,
            final PreparedStatement newPreparedStatement) throws SQLException {
        //newPreparedStatement.setLong(1, newCoupon.getId());
        newPreparedStatement.setLong(1, newCoupon.getCategory().getId());
        newPreparedStatement.setLong(2, newCoupon.getCompanyProvider().getId());
        newPreparedStatement.setNString(3, newCoupon.getCouponName());
        newPreparedStatement.setNString(4, newCoupon.getPathToPicture());
        newPreparedStatement.setNString(5, newCoupon.getCouponDescription());
        newPreparedStatement.setBigDecimal(6, newCoupon.getCouponPrice());
        Date date = Date.valueOf(newCoupon.getCouponAddDate());
        newPreparedStatement.setDate(7, date);
        newPreparedStatement.setNString(8, newCoupon.getHoldingAddress());
        newPreparedStatement.setBoolean(9, newCoupon.isBlocking());
    }

    private Coupon getNewCoupon(final ResultSet newResultSet)
            throws SQLException {
        long id = newResultSet.getLong("id");
        Category category = new Category();
        CompanyProvider companyProvider = new CompanyProvider();
        long category_id = newResultSet.getLong("category_id");
        long company_provider_id = newResultSet.getLong("company_provider_id");
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

    private int getAmountWithStringParameter(final String query,
             final String byWhatSearch) throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(query)) {
            preparedStatement.setNString(1, byWhatSearch);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1);
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }
}
