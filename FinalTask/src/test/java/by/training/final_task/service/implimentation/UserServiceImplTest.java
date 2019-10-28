package by.training.final_task.service.implimentation;

import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.Role;
import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.ServiceFactory;
import by.training.final_task.service.interfaces.UserService;
import org.testng.annotations.*;


import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import static org.testng.Assert.*;

public class UserServiceImplTest {
    private ServiceFactory serviceFactory;
    private UserService userService;

    @BeforeSuite
    public void init() {
        ResourceBundle bundle = ResourceBundle.getBundle("database");
        ServiceInitializer.init(bundle);
    }

    @BeforeClass
    public void setUp() throws ServiceException {
        serviceFactory = new ServiceFactoryImpl();
        userService = (UserService) serviceFactory.createService(DAOEnum.USER);
    }

    @AfterClass
    public void tearDown() throws ServiceException {
        serviceFactory.close();
    }

    /*@AfterTest
    public void setDown() throws ServiceException {
        serviceFactory.close();
    }*/

    @DataProvider(name = "Correct user creating")
    public Object[][] createCorrectUser() {
        return new Object[][]{
                {"antonUser", "1234567890", Role.USER, "test1@mail.ru",
                        "pathToAvatar", "Anton", "Soveliev", 296458210, LocalDate.now()},
                {"sergStaff", "1234567890", Role.STAFF, "test2@mail.ru",
                        "pathToAvatar", "Serg", "Guk", 336452019, LocalDate.now()}
        };
    }

    @DataProvider(name = "Test user by id")
    public Object[][] getByIdUser() {
        return new Object[][]{
                {1, new User(1, "olegAdmin", "$argon2id$v=19$m=65536,t=2,p=4$ivR5jACDbtSDwV8pEFAkpQ$0GwdQV9NOa1nVrNS2G4nDcAzG/DYA6pogFYzJ4TR7Gk",
                        Role.ADMIN, "oleg@mail.ru", "img/user/user_profile.jpg", "Oleg", "Bondarev", 336494933,
                        LocalDate.of(2017, 10, 14), false)}
        };
    }

    @DataProvider(name = "Test by login positive")
    public Object[][] getByLoginPositive() {
        return new Object[][]{
                {"olegAdmin", new User(1, "olegAdmin",
                        "$argon2id$v=19$m=65536,t=2,p=4$ivR5jACDbtSDwV8pEFAkpQ$0GwdQV9NOa1nVrNS2G4nDcAzG/DYA6pogFYzJ4TR7Gk",
                        Role.ADMIN, "oleg@mail.ru", "img/user/user_profile.jpg",
                        "Oleg", "Bondarev", 336494933,
                        LocalDate.of(2017, 10, 14), false)}
        };
    }

    @DataProvider(name = "Test by login negative")
    public Object[] getByLoginNegative() {
        return new String[]{
                "admin",
                "user",
                "ksjfkj"
        };
    }

    @DataProvider(name = "Test to update")
    public Object[][] getToUpdate() {
        return new Object[][]{
                {"ostapUser", new User(5, "ostapTopUser",
                        "$argon2id$v=19$m=65536,t=2,p=4$ivR5jACDbtSDwV8pEFAkpQ$0GwdQV9NOa1nVrNS2G4nDcAzG/DYA6pogFYzJ4TR7Gk",
                        Role.ADMIN, "ostap-berg@mail.ru", "img/user/user_profile.jpg",
                        "Ostap", "Berg", 333031529,
                        LocalDate.of(2018, 05, 01), false)}
        };
    }

    @DataProvider(name = "Test to invalid update")
    public Object[][] getToInvalidUpdate() {
        return new Object[][]{
                {"ostapUser", new User(5, "olegAdmin",
                        "$argon2id$v=19$m=65536,t=2,p=4$ivR5jACDbtSDwV8pEFAkpQ$0GwdQV9NOa1nVrNS2G4nDcAzG/DYA6pogFYzJ4TR7Gk",
                        Role.ADMIN, "ostap-berg@mail.ru", "img/user/user_profile.jpg",
                        "Ostap", "Berg", 333031529,
                        LocalDate.of(2018, 05, 01), false)},
                {"ostapUser", new User(5, "ostapUser",
                        "$argon2id$v=19$m=65536,t=2,p=4$ivR5jACDbtSDwV8pEFAkpQ$0GwdQV9NOa1nVrNS2G4nDcAzG/DYA6pogFYzJ4TR7Gk",
                        Role.ADMIN, "oleg@mail.ru", "img/user/user_profile.jpg",
                        "Ostap", "Berg", 333031529,
                        LocalDate.of(2018, 05, 01), false)}
        };
    }

    @DataProvider(name = "Test to update user statement")
    public Object[] getToUpdateUserStatement() {
        return new Object[]{
                "ostapTopUser"
        };
    }

    @DataProvider(name = "Test to get all")
    public Object[][] getToGetAll() {
        return new Object[][]{
                {0, 4, 4},
                {0, 7, 7},
                {0, 9, 8},
                {4, 3, 3},
                {6, 5, 2}
        };
    }

    @DataProvider(name = "Test get all users by role and name")
    public Object[][] getToGetAllByRoleAndName() throws ServiceException {
        userService.create(new User(0, "testUser", "1234567890", Role.USER,
                "leonidus@mail.ru", "path", "Leonid",
                "Davidov", 448962015,
                LocalDate.now(), false));
        userService.create(new User(0, "testStaff", "1234567890", Role.STAFF,
                "leonidus-staff@mail.ru", "path",
                "Leonid", "Dzernov", 448963420,
                LocalDate.now(), false));
        return new Object[][]{
                {Role.ADMIN, "Oleg", 1},
                {Role.USER, "Sergej", 0},
                {Role.USER, "Leonid", 2},
                {Role.STAFF, "Leonid", 1},
                {Role.STAFF, "Ivan", 0}
        };
    }

    @DataProvider(name = "Test get all users by role")
    public Object[][] getToGetAllByRole() throws ServiceException {
        return new Object[][]{
                {Role.ADMIN, 1},
                {Role.USER, 7},
                {Role.STAFF, 2}
        };
    }

    @DataProvider(name = "Test get all users by first and second name")
    public Object[][] getToGetAllByFirstSecondName() throws ServiceException {
        return new Object[][]{
                {"Leonid", "Davidov", Role.USER, 2},
                {"Leonid", "Davidov", Role.STAFF, 0},
                {"Leonid", "Davidov", Role.ADMIN, 0},
                {"Oleg", "Bondarev", Role.ADMIN, 1},
                {"Oleg", "Bondarev", Role.STAFF, 0},
                {"Oleg", "Bondarev", Role.USER, 0},
                {"Unknown", "Unknown", Role.ADMIN, 0},
                {"Unknown", "Unknown", Role.STAFF, 0},
                {"Unknown", "Unknown", Role.USER, 0},
        };
    }

    @DataProvider(name = "Test get amount users by role and name")
    public Object[][] getToGetAmountByRoleAndName() throws ServiceException {
        return new Object[][]{
                {Role.ADMIN, "Oleg", 1},
                {Role.USER, "Sergej", 0},
                {Role.USER, "Leonid", 2},
                {Role.STAFF, "Leonid", 1},
                {Role.STAFF, "Ivan", 0}
        };
    }

    @DataProvider(name = "Test get amount users by email")
    public Object[][] getToGetAmountByEmail() {
        return new Object[][]{
                {"oleg@mail.ru", 1},
                {"ggg@gmail.com", 0}
        };
    }


    @Test(dataProvider = "Correct user creating", groups = {"User service group"})
    public void testCreateCorrect(final String login, final String pass, final Role role,
                                  final String mail, final String path, final String fname,
                                  final String sname, final int phone, final LocalDate date)
            throws ServiceException {
        User user = new User(0, login, pass, role, mail, path, fname,
                sname, phone, date, false);
        long id = userService.create(user);
        assertNotEquals(id, 0);
    }

    @Test(dataProvider = "Test user by id", groups = {"User service group"},
            dependsOnMethods = {"testCreateCorrect"})
    public void testGetById(final long id, final User newUser) throws ServiceException {
        User expectUser = userService.get(id);
        assertEquals(expectUser, newUser);
    }

    @Test(dataProvider = "Test by login positive", groups = {"User service group"},
            dependsOnMethods = {"testGetById"})
    public void testGetByLoginPositive(final String login, final User user)
            throws ServiceException {
        User expect = userService.getByLogin(login);
        assertEquals(expect, user);
    }

    @Test(dataProvider = "Test by login negative", groups = {"User service group"},
            dependsOnMethods = {"testGetByLoginPositive"})
    public void testGetByLoginNegative(final String login) throws ServiceException {
        User expect = userService.getByLogin(login);
        assertNull(expect);
    }

    @Test(dataProvider = "Test to update", groups = {"User service group"},
            dependsOnMethods = {"testGetByLoginNegative"})
    public void testUpdate(final String login, final User expect)
            throws ServiceException {
        User oldUser = userService.getByLogin(login);
        boolean statement = userService.update(expect, oldUser);
        assertTrue(statement);
    }

    @Test(dataProvider = "Test to invalid update",
            groups = {"User service group"},
            dependsOnMethods = {"testUpdate"},
            expectedExceptions = ServiceException.class)
    public void testInvalidUpdate(final String login, final User expect)
            throws ServiceException {
        User oldUser = userService.getByLogin(login);
        userService.update(expect, oldUser);
    }

    @Test(dataProvider = "Test to update user statement",
            groups = {"User service group"},
            dependsOnMethods = {"testInvalidUpdate"})
    public void testUpdateUserStatement(final String login)
            throws ServiceException {
        User user = userService.getByLogin(login);
        boolean statement = userService.updateUserState(user.getId());
        assertTrue(statement);
    }

    @Test(dataProvider = "Test to get all", groups = {"User service group"},
            dependsOnMethods = {"testUpdateUserStatement"})
    public void testGetAllUsers(final int offset, final int limit,
                                final int expectCount)
            throws ServiceException {
        List<User> userList = userService.getAll(offset, limit);
        assertEquals(userList.size(), expectCount);
    }

    @Test(dataProvider = "Test get all users by role and name",
            groups = {"User service group"},
            dependsOnMethods = {"testGetAllUsers"})
    public void testGetAllUsersByRoleAndName(final Role role, final String name,
                                             final int countUsers)
            throws ServiceException {
        List<User> userList = userService.getAllUsersByRoleAndName(name, role,
                0, 15);
        assertEquals(userList.size(), countUsers);
    }

    @Test(dataProvider = "Test get all users by role",
            groups = {"User service group"},
            dependsOnMethods = {"testGetAllUsersByRoleAndName"})
    public void testGetAllUsersByRole(final Role role, final int expectCount)
            throws ServiceException {
        List<User> userList = userService.getAllUsersByRole(role, 0, 15);
        assertEquals(userList.size(), expectCount);
    }

    @Test(dataProvider = "Test get all users by first and second name",
            groups = {"User service group"},
            dependsOnMethods = {"testGetAllUsersByRole"})
    public void testGetAllUsersByFirstAndSecondName(final String firstName,
                                                    final String secondName,
                                                    final Role role,
                                                    final int expectCount)
            throws ServiceException {
        List<User> userList = userService.getAllUsersByFirstAndSecondName(
                firstName, secondName, role);
        assertEquals(userList.size(), expectCount);
    }

    @Test(dependsOnMethods = {"testGetAllUsersByFirstAndSecondName"})
    public void testGetAllActiveUsers() throws ServiceException {
        List<User> userList = userService.getAllActiveUsers(0, 15);
        assertEquals(userList.size(), 10);
    }


    @Test(dataProvider = "Test get all users by role",
            groups = {"User service group"},
            dependsOnMethods = {"testGetAllActiveUsers"})
    public void testGetAmountOfAllUsersByRole(final Role newRole,
                                              final int expectCount)
            throws ServiceException {
        int actualCount = userService.getAmountOfAllUsersByRole(newRole);
        assertEquals(actualCount, expectCount);
    }

    @Test(dataProvider = "Test get amount users by role and name",
            groups = {"User service group"},
            dependsOnMethods = {"testGetAmountOfAllUsersByRole"})
    public void testGetAmountOfAllUsersByFirstNameAndRole(final Role role,
                                                          final String name,
                                                          final int countUsers)
            throws ServiceException {
        int actualCount = userService
                .getAmountOfAllUsersByFirstNameAndRole(name, role);
        assertEquals(actualCount, countUsers);
    }

    @Test(dataProvider = "Test get all users by first and second name",
            groups = {"User service group"},
            dependsOnMethods = {"testGetAmountOfAllUsersByFirstNameAndRole"})
    public void testGetAmountOfAllUsersByFirstAndSecondName(final String firstName,
                                                            final String secondName,
                                                            final Role role,
                                                            final int expectCount)
            throws ServiceException {
        int actualCount = userService.getAmountOfAllUsersByFirstAndSecondName(
                firstName, secondName, role);
    }

    @Test(dataProvider = "Test get amount users by email",
            groups = {"User service group"},
            dependsOnMethods = {"testGetAmountOfAllUsersByFirstAndSecondName"})
    public void testGetAmountOfAllUsersByEmail(final String email,
                                               final int expectCount)
            throws ServiceException {
        int actualCount = userService.getAmountOfAllUsersByEmail(email);
        assertEquals(actualCount, expectCount);
    }
}