package by.training.final_task.dao.sql;

import by.training.final_task.dao.interfases.CouponDAO;
import by.training.final_task.entity.Category;
import by.training.final_task.entity.Coupon;
import by.training.final_task.exception.PersistentException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CouponDaoImpl extends BaseDaoImpl implements CouponDAO {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String ADD_COUPON = "INSERT INTO coupon (category_id, company_provider_id, name, picture, description, price, adding_date_time, holding_address) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_COUPON = "UPDATE coupon SET category_id=?, company_provider_id=?, name=?, picture=?, description=?, price=?, adding_date_time=?, holding_address=? WHERE coupon.id=?";
    private static final String DELETE_COUPON = "DELETE FROM coupon WHERE id = ?";
    private static final String GET_COUPON = "SELECT coupon.id, coupon.category_id, coupon.company_provider_id, coupon.name, coupon.picture, coupon.description, coupon.price, coupon.adding_date_time, coupon.holding_address FROM coupon WHERE coupon.id = ?";
    private static final String GET_ALL_COUPONS = "SELECT coupon.id, coupon.category_id, coupon.company_provider_id, coupon.name, coupon.picture, coupon.description, coupon.price, coupon.adding_date_time, coupon.holding_address FROM coupon ORDER BY id LIMIT ? OFFSET ?";
    private static final String GET_ALL_COUPONS_BY_COMPANY_NAME = "SELECT coupon.id, coupon.category_id, coupon.company_provider_id, coupon.name, coupon.picture, coupon.description, coupon.price, coupon.adding_date_time, coupon.holding_address FROM coupon WHERE coupon.name = ? ORDER BY id LIMIT ? OFFSET ?";
    private static final String GET_ALL_COUPONS_BY_CATEGORY = "SELECT coupon.id, coupon.category_id, coupon.company_provider_id, coupon.name, coupon.picture, coupon.description, coupon.price, coupon.adding_date_time, coupon.holding_address FROM coupon WHERE coupon.category_id = ? LIMIT ? OFFSET ?";
    private static final String GET_ALL_COUPONS_BY_NAME = "SELECT coupon.id, coupon.category_id, coupon.company_provider_id, coupon.name, coupon.picture, coupon.description, coupon.price, coupon.adding_date_time, coupon.holding_address FROM coupon WHERE coupon.name = ?";
    private static final String GET_ALL_COUPONS_BY_PRICE_RANGE = "SELECT coupon.id, coupon.category_id, coupon.company_provider_id, coupon.name, coupon.picture, coupon.description, coupon.price, coupon.adding_date_time, coupon.holding_address FROM coupon WHERE coupon.price BETWEEN ? AND ? LIMIT ? OFFSET ?";
    /**Check*/
    private static final String GET_ALL_AVAILABLE_COUPONS = "SELECT coupon.id, coupon.category_id, coupon.company_provider_id, coupon.name, coupon.picture, coupon.description, coupon.price, coupon.adding_date_time, coupon.holding_address FROM company_provider JOIN coupon ON company_provider.id = coupon.company_provider_id WHERE company_provider.blocking = false LIMIT ? OFFSET ?";
    private static final String GET_AMOUNT_OF_ALL_COUPON = "SELECT COUNT(coupon.id) FROM coupon";
    private static final String GET_AMOUNT_BY_CATEGORY = "SELECT COUNT(coupon.id) FROM coupon INNER JOIN category ON(category.id = coupon.id) WHERE category.name = ?";
    private static final String GET_AMOUNT_BY_COMPANY_PROVIDER = "SELECT COUNT(coupon.id) FROM coupon INNER JOIN company_provider ON(company_provider.id = coupon.id) WHERE company_provider.name = ?";
    private static final String GET_AMOUNT_BY_COMPANY_NAME = "SELECT COUNT(coupon.id) FROM coupon WHERE coupon.name = ?";


    public CouponDaoImpl(final Connection newConnection) {
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
                    coupon =  getNewCoupon(resultSet);
                }
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
        return coupon;
    }

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
    public List<Coupon> getAllByCompanyProvider(final String name,
                                                final int offset,
                                                final int limit)
            throws PersistentException {
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

    @Override
    public List<Coupon> getAllByCategory(Category category, int offset, int limit) throws PersistentException {
        List<Coupon> coupons = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_ALL_COUPONS_BY_CATEGORY)) {
            preparedStatement.setNString(1, category.getValue());
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

    @Override
    public List<Coupon> getAllByPriceRange(final BigDecimal minBorder,
                                           final BigDecimal maxBorder,
                                           final int offset, int limit)
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
    public List<Coupon> getAllAvailableCoupons(final int offset,
                                               final int limit)
            throws PersistentException {
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

    @Override
    public boolean delete(final long id) throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(DELETE_COUPON)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

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

    @Override
    public int getAmountByCategory(final Category category)
            throws PersistentException {
        return getAmountWithStringParameter(GET_AMOUNT_BY_CATEGORY, category.getValue());
    }

    @Override
    public int getAmountByCompanyProvider(final String companyName)
            throws PersistentException {
        String query = GET_AMOUNT_BY_COMPANY_PROVIDER;
        return getAmountWithStringParameter(query, companyName);
    }

    @Override
    public int getAmountByName(final String name) throws PersistentException {
        String query = GET_AMOUNT_BY_COMPANY_NAME;
        return getAmountWithStringParameter(query, name);
    }

    @Override
    public int getAmountByPriceRange(final BigDecimal minBorder,
                                     final BigDecimal maxBorder)
            throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_ALL_COUPONS_BY_PRICE_RANGE)) {
            preparedStatement.setBigDecimal(1, minBorder);
            preparedStatement.setBigDecimal(2, maxBorder);
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
    public int create(Coupon newCoupon) throws PersistentException {
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
            preparedStatement.setLong(9, newCoupon.getId());
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
        return delete(newCoupon.getId());
    }

    private void setPreparedStatement(final Coupon newCoupon,
                              final PreparedStatement newPreparedStatement)
            throws SQLException {
        //newPreparedStatement.setLong(1, newCoupon.getId());
        newPreparedStatement.setLong(1, newCoupon.getCategoryId());
        newPreparedStatement.setLong(2, newCoupon.getCompanyProviderId());
        newPreparedStatement.setNString(3, newCoupon.getCouponName());
        newPreparedStatement.setNString(4, newCoupon.getPathToPicture());
        newPreparedStatement.setNString(5, newCoupon.getCouponDescription());
        newPreparedStatement.setBigDecimal(6, newCoupon.getCouponPrice());
        newPreparedStatement.setDate(7, newCoupon.getCouponAddDate());
        newPreparedStatement.setNString(8, newCoupon.getHoldingAddress());
    }

    private Coupon getNewCoupon(final ResultSet newResultSet)
            throws SQLException {
        long id = newResultSet.getLong("id");
        long category_id = newResultSet.getLong("category_id");
        long company_provider_id = newResultSet.getLong("company_provider_id");
        String name = newResultSet.getNString("name");
        String pathToPicture = newResultSet.getNString("picture");
        String description = newResultSet.getNString("description");
        BigDecimal price = newResultSet.getBigDecimal("price");
        Date adding_date_time = newResultSet.getDate("adding_date_time");
        String holding_address = newResultSet.getNString("holding_address");
        return new Coupon(id, name, pathToPicture, description, price,
            adding_date_time, holding_address, category_id, company_provider_id);
    }

    private int getAmountWithStringParameter(final String query,
                                          final String byWhatSearch)
            throws PersistentException {
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
