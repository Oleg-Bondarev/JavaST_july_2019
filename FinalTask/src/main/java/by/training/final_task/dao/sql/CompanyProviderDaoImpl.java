package by.training.final_task.dao.sql;

import by.training.final_task.dao.interfases.AbstractConnectionManager;
import by.training.final_task.dao.interfases.CompanyProviderDAO;
import by.training.final_task.entity.CompanyProvider;
import by.training.final_task.dao.PersistentException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CompanyProviderDaoImpl extends AbstractDao<CompanyProvider>
        implements CompanyProviderDAO {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String GET_BY_PHONE = "SELECT company_provider.id,"
            + " company_provider.address, company_provider.name,"
            + " company_provider.mobile_phone, company_provider.blocking"
            + " FROM company_provider  WHERE company_provider.mobile_phone = ?";
    private static final String GET_ALL_COMPANY = "SELECT company_provider.id,"
            + " company_provider.address, company_provider.name,"
            + " company_provider.mobile_phone, company_provider.blocking"
            + " FROM company_provider ORDER BY id LIMIT ? OFFSET ?";
    private static final String GET_ALL_COMPANIES_BY_NAME =
            "SELECT company_provider.id,"
            + " company_provider.address, company_provider.name,"
            + " company_provider.mobile_phone, company_provider.blocking"
            + " FROM company_provider WHERE company_provider.blocking = false"
            + " AND company_provider.name=? LIMIT ? OFFSET ?";
    private static final String GET_ALL_AVAILABLE_COMPANY =
            "SELECT company_provider.id, company_provider.address,"
            + " company_provider.name, company_provider.mobile_phone,"
            + " company_provider.blocking FROM company_provider"
            + " WHERE company_provider.blocking = false"
            +" ORDER BY id LIMIT ? OFFSET ?";
    private static final String GET_ALL_AVAILABLE_COMPANY_LIST =
            "SELECT company_provider.id, company_provider.address," +
            " company_provider.name, company_provider.mobile_phone," +
            " company_provider.blocking FROM company_provider" +
            " WHERE company_provider.blocking = false ORDER BY id";
    private static final String GET_AMOUNT_BY_NAME =
            "SELECT COUNT(company_provider.id) FROM company_provider" +
            " WHERE company_provider.blocking = false" +
            " AND company_provider.name=?";
    private static final String GET_AMOUNT_OF_ALL_COMPANY =
            "SELECT COUNT(company_provider.id) FROM company_provider";
    private static final String GET_AMOUNT_OF_ALL_AVAILABLE_COMPANY =
            "SELECT COUNT(company_provider.id) FROM company_provider" +
            " WHERE company_provider.blocking = false";
    private static final String ADD_COMPANY = "INSERT INTO company_provider (address, name, mobile_phone, blocking) VALUES (?, ?, ?, ?)";
    private static final String GET_COMPANY = "SELECT company_provider.id, company_provider.address, company_provider.name, company_provider.mobile_phone, company_provider.blocking FROM company_provider  WHERE company_provider.id = ?";
    private static final String UPDATE_COMPANY = "UPDATE company_provider SET company_provider.id=?, company_provider.address=?, company_provider.name=?, company_provider.mobile_phone=?, company_provider.blocking=? WHERE company_provider.id=?";
    private static final String UPDATE_COMPANY_STATUS = "UPDATE company_provider SET company_provider.blocking = true WHERE company_provider.id=?";

    CompanyProviderDaoImpl(final AbstractConnectionManager newConnection) {
        super(newConnection);
    }

    @Override
    public CompanyProvider getByPhone(final int phone)
            throws PersistentException {
        CompanyProvider company = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_BY_PHONE)) {
            preparedStatement.setInt(1, phone);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    company =  getNewCompany(resultSet);
                }
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
        return company;
    }

    @Override
    public List<CompanyProvider> getByCompanyName(final String companyName,
                                          final int offset, final int limit)
            throws PersistentException {
        List<CompanyProvider> companiesList = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_ALL_COMPANIES_BY_NAME)) {
            preparedStatement.setNString(1, companyName);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    companiesList.add(getNewCompany(resultSet));
                }
            }
            return companiesList;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public List<CompanyProvider> getAll(final int offset, final int limit)
            throws PersistentException {
        return getListByQuery(offset, limit, GET_ALL_COMPANY);
    }

    @Override
    public List<CompanyProvider> getAllAvailableCompany(int offset, int limit)
            throws PersistentException {
        return getListByQuery(offset, limit, GET_ALL_AVAILABLE_COMPANY);
    }

    @Override
    public List<CompanyProvider> getAvailableCompanyList()
            throws PersistentException {
        List<CompanyProvider> companies = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_ALL_AVAILABLE_COMPANY_LIST)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    companies.add(getNewCompany(resultSet));
                }
            }
            return companies;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    @Override
    public int getAmountByName(final String name) throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_AMOUNT_BY_NAME)) {
            preparedStatement.setNString(1, name);

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
    public int getAmountOfCompany() throws PersistentException {
        return getAmountByQuery(GET_AMOUNT_OF_ALL_COMPANY);
    }

    @Override
    public int getAmountOfAvailableCompany() throws PersistentException {
        return getAmountByQuery(GET_AMOUNT_OF_ALL_AVAILABLE_COMPANY);
    }

    @Override
    public boolean updateCompanyStatus(final long id) throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(UPDATE_COMPANY_STATUS)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE);
            throw new PersistentException("Fail in updating company provider!\n"
                    + newE.getMessage(), newE);
        }
    }

    @Override
    public int create(final CompanyProvider newCompany)
            throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(ADD_COMPANY, PreparedStatement
                        .RETURN_GENERATED_KEYS)) {
            setPreparedStatement(newCompany, preparedStatement);
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
    public CompanyProvider get() throws PersistentException {
        return get(1);
    }

    @Override
    public CompanyProvider get(final long id) throws PersistentException {
        CompanyProvider coupon = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(GET_COMPANY)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    coupon =  getNewCompany(resultSet);
                }
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
        return coupon;
    }

    @Override
    public boolean update(final CompanyProvider newCompany)
            throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(UPDATE_COMPANY)) {
            setPreparedStatement(newCompany, preparedStatement);
            preparedStatement.setLong(6, newCompany.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE);
            throw new PersistentException("Fail in updating company provider!\n"
                    + newE.getMessage(), newE);
        }
    }

    @Override
    public boolean delete(final CompanyProvider newCompany)
            throws PersistentException {
        return delete(newCompany.getId());
    }

    @Override
    public boolean delete(final long id) throws PersistentException {
        throw new PersistentException("Incorrect operation for Company provider"
                + " type.");
    }

    private CompanyProvider getNewCompany(ResultSet newResultSet)
            throws SQLException {
        long id = newResultSet.getLong("id");
        String address = newResultSet.getNString("address");
        String name = newResultSet.getNString("name");
        int mobilePhone = newResultSet.getInt("mobile_phone");
        boolean blocking = newResultSet.getBoolean("blocking");
        return new CompanyProvider(id, address, name, mobilePhone, blocking);
    }

    private List<CompanyProvider> getListByQuery(final int offset,
                                                 final int limit,
                                                 final String query)
            throws PersistentException {
        List<CompanyProvider> companies = new LinkedList<>();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(query)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    companies.add(getNewCompany(resultSet));
                }
            }
            return companies;
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    private int getAmountByQuery(final String query)
            throws PersistentException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1);
            }
        } catch (SQLException newE) {
            LOGGER.log(Level.WARN, newE.getMessage(), newE);
            throw new PersistentException(newE.getMessage(), newE);
        }
    }

    private void setPreparedStatement(final CompanyProvider company,
                                      final PreparedStatement preparedStatement)
            throws SQLException {
        preparedStatement.setNString(1,
                company.getCompanyAddress());
        preparedStatement.setNString(2, company.getCompanyName());
        preparedStatement.setInt(3, company.getMobilePhone());
        preparedStatement.setBoolean(4, company.getBlocking());
    }
}
