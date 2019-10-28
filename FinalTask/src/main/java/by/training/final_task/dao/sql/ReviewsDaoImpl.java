package by.training.final_task.dao.sql;

import by.training.final_task.dao.interfases.AbstractConnectionManager;
import by.training.final_task.dao.interfases.ReviewsDAO;
import by.training.final_task.entity.Coupon;
import by.training.final_task.entity.Reviews;
import by.training.final_task.dao.PersistentException;
import by.training.final_task.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ReviewsDaoImpl extends AbstractDao<Reviews> implements ReviewsDAO {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String GET_ALL_REVIEWS_OF_CURRENT_USER =
            "SELECT reviews.id, reviews.review, reviews.coupon_id,"
                    + " reviews.user_id FROM reviews JOIN user"
                    + " ON reviews.user_id = user.id WHERE user.id = ?"
                    + " LIMIT ? OFFSET ?";
    private static final String GET_ALL_REVIEWS_FOR_CURRENT_COUPON =
            "SELECT  reviews.id, reviews.review, reviews.coupon_id,"
                    + " reviews.user_id FROM reviews JOIN coupon "
                    + "ON reviews.coupon_id = coupon.id WHERE coupon.id = ? "
                    + "LIMIT ? OFFSET ?";
    private static final String GET_ALL_AMOUNT_REVIEWS =
            "SELECT COUNT(reviews.id) FROM reviews";
    private static final String GET_AMOUNT_REVIEWS_FOR_CURRENT_USER =
            "SELECT COUNT(reviews.id) FROM reviews JOIN user "
                    + "ON reviews.user_id = user.id WHERE  user.id = ?";
    private static final String GET_AMOUNT_REVIEWS_FOR_CURRENT_COUPON =
            "SELECT COUNT(reviews.id) FROM reviews JOIN coupon "
                    + "ON reviews.coupon_id = coupon.id WHERE coupon.id = ?";
    private static final String ADD_REVIEW = "INSERT INTO reviews "
            + "(review, coupon_id, user_id) VALUES (?,?,?)";
    private static final String GET_REVIEW =
            "SELECT reviews.id, reviews.review, reviews.coupon_id,"
                    + " reviews.user_id FROM reviews WHERE reviews.id = ?";
    private static final String GET_ALL_REVIEWS =
            "SELECT reviews.id, reviews.review, reviews.coupon_id, "
                    + "reviews.user_id FROM reviews ORDER BY id"
                    + " LIMIT ? OFFSET ?";
    private static final String UPDATE_REVIEW = "UPDATE reviews SET "
            + "reviews.id=?, reviews.review=?, reviews.coupon_id=?,"
            + " reviews.user_id=? WHERE reviews.id = ?";
    private static final String DELETE_REVIEW = "DELETE FROM reviews "
            + "WHERE reviews.id = ?";

    public ReviewsDaoImpl(final AbstractConnectionManager newConnection) {
        super(newConnection);
    }

    @Override
    public List<Reviews> getAllReviewsCurrentUser(final long userId,
                                                  final int offset,
                                                  final int limit)
            throws PersistentException {
        List<Reviews> coupons = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_ALL_REVIEWS_OF_CURRENT_USER)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    coupons.add(takeNewReview(resultSet));
                }
            }
            return coupons;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public List<Reviews> getAllReviewsCurrentCoupon(final long couponId,
                                                    final int offset,
                                                    final int limit)
            throws PersistentException {
        List<Reviews> coupons = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_ALL_REVIEWS_FOR_CURRENT_COUPON)) {
            preparedStatement.setLong(1, couponId);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    coupons.add(takeNewReview(resultSet));
                }
            }
            return coupons;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public int getAllCount() throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_ALL_AMOUNT_REVIEWS)) {
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
    public int getCountReviewsCurrentUser(final long userId)
            throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_AMOUNT_REVIEWS_FOR_CURRENT_USER)) {
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

    @Override
    public int getCountReviewsCurrentCoupon(final long couponId)
            throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_AMOUNT_REVIEWS_FOR_CURRENT_COUPON)) {
            preparedStatement.setLong(1, couponId);
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
    public int create(final Reviews newReview) throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(ADD_REVIEW, PreparedStatement
                        .RETURN_GENERATED_KEYS)) {
            setPreparedStatement(newReview, preparedStatement);
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
    public Reviews get() throws PersistentException {
        return get(1);
    }

    @Override
    public Reviews get(final long id) throws PersistentException {
        Reviews review = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_REVIEW)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    review = takeNewReview(resultSet);
                }
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
        return review;
    }

    @Override
    public List<Reviews> getAll(final int offset, final int limit)
            throws PersistentException {
        List<Reviews> couponUsers = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_ALL_REVIEWS)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    couponUsers.add(takeNewReview(resultSet));
                }
            }
            return couponUsers;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public boolean update(final Reviews newReview) throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(UPDATE_REVIEW)) {
            setPreparedStatement(newReview, preparedStatement);
            preparedStatement.setLong(5, newReview.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE);
            throw new PersistentException("Fail in updating review!\n"
                    + newE.getMessage(), newE);
        }
    }

    @Override
    public boolean delete(final Reviews element) throws PersistentException {
        return delete(element.getId());
    }

    @Override
    public boolean delete(final long id) throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(DELETE_REVIEW)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    private Reviews takeNewReview(final ResultSet newResultSet)
            throws SQLException {
        long id = newResultSet.getLong("id");
        String review = newResultSet.getNString("review");
        long couponId = newResultSet.getLong("coupon_id");
        long userId = newResultSet.getLong("user_id");
        Coupon coupon = new Coupon();
        User user = new User();
        coupon.setId(couponId);
        user.setId(userId);
        return new Reviews(id, review, coupon, user);
    }

    private void setPreparedStatement(final Reviews newReview,
                                      final PreparedStatement preparedStatement)
            throws SQLException {
        preparedStatement.setNString(1, newReview.getReview());
        preparedStatement.setLong(2,
                newReview.getCoupon().getId());
        preparedStatement.setLong(3, newReview.getUser().getId());
    }
}
