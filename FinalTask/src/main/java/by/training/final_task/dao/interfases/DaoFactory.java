package by.training.final_task.dao.interfases;

public interface DaoFactory {
    UserDAO createUserDAO(AbstractConnectionManager newConnectionManager);
    ReviewsDAO createReviewsDAO(AbstractConnectionManager newConnectionManager);
    CouponUserDAO createCouponUserDAO(AbstractConnectionManager newConnectionManager);
    CouponDAO createCouponDAO(AbstractConnectionManager newConnectionManager);
    CompanyProviderDAO createCompanyProviderDAO(AbstractConnectionManager newConnectionManager);
    CategoryDAO createCategoryDAO(AbstractConnectionManager newConnectionManager);
}
