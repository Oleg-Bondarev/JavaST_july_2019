package by.training.final_task.dao.sql;

import by.training.final_task.dao.interfases.*;

public class DaoFactoryImpl implements DaoFactory {
    @Override
    public UserDAO createUserDAO(
            final AbstractConnectionManager newConnectionManager) {
        return new UserDaoImpl(newConnectionManager);
    }

    @Override
    public ReviewsDAO createReviewsDAO(
            final AbstractConnectionManager newConnectionManager) {
        return new ReviewsDaoImpl(newConnectionManager);
    }

    @Override
    public CouponUserDAO createCouponUserDAO(
            final AbstractConnectionManager newConnectionManager) {
        return new CouponUserDaoImpl(newConnectionManager);
    }

    @Override
    public CouponDAO createCouponDAO(
            final AbstractConnectionManager newConnectionManager) {
        return new CouponDaoImpl(newConnectionManager);
    }

    @Override
    public CompanyProviderDAO createCompanyProviderDAO(
            final AbstractConnectionManager newConnectionManager) {
        return new CompanyProviderDaoImpl(newConnectionManager);
    }

    @Override
    public CategoryDAO createCategoryDAO(
            final AbstractConnectionManager newConnectionManager) {
        return new CategoryDaoImpl(newConnectionManager);
    }
}
