package ua.com.vertex.logic;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import ua.com.vertex.beans.User;
import ua.com.vertex.beans.UserFormRegistration;
import ua.com.vertex.context.MainTestContext;
import ua.com.vertex.dao.UserDaoRealization;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("Duplicates")
@ContextConfiguration(classes = MainTestContext.class)
public class RegistrationUserLogicImplTest {

    @Mock
    private UserFormRegistration userFormRegistration;

//    todo: use it - @InjectMocks
    @Mock
    private RegistrationUserLogicImpl registrationUserLogic;

    @Mock
    private UserDaoRealization userDaoRealization;

    @Before
    public void setUp() {
//       todo: @RunWith(MockitoJUnitRunner.class) could be used instead
        MockitoAnnotations.initMocks(this);
        //todo: is it a Mock or not?
        registrationUserLogic = new RegistrationUserLogicImpl();
    }

    @Test
    public void userFormRegistrationShouldNotBeNull() {
        //todo: oO are you going to test java machine? so why don't you test 1 + 1 == 2?
        UserFormRegistration userFormRegistration = new UserFormRegistration();
        assertNotNull(userFormRegistration);
    }

    @Test
    public void UserShouldNotBeNull() {
//        todo: oO
        User user = new User();
        assertNotNull(user);
    }

    @Test
    public void isMatchPassword_True() {
        UserFormRegistration userFormRegistration = new UserFormRegistration();
        userFormRegistration.setPassword("1");
        userFormRegistration.setVerifyPassword("1");
        Boolean result = registrationUserLogic.isMatchPassword(userFormRegistration);
//        todo: result == true ????? why don't you use result != false ?? =)
        assertTrue("Result is (" + result + ")", result == true);
    }

    @Test
    public void isMatchPassword_False() {
        UserFormRegistration userFormRegistration = new UserFormRegistration();
        userFormRegistration.setPassword("1");
        userFormRegistration.setVerifyPassword("2");
        Boolean result = registrationUserLogic.isMatchPassword(userFormRegistration);
        assertTrue("Result is (" + result + ")", result == false);
    }

    @Test
    @Ignore
    //todo: repair test
    public void encryptPassword_True() {
//        UserFormRegistration userFormRegistration = new UserFormRegistration();
//        userFormRegistration.setPassword("111111");
//        userFormRegistration = registrationUserLogic.encryptPassword(userFormRegistration);
//        assertEquals(userFormRegistration.getPassword(), "96e79218965eb72c92a549dd5a330112");
    }

    @Test
    @Ignore
    //todo: repair test
    public void encryptPassword_False() {
//        UserFormRegistration userFormRegistration = new UserFormRegistration();
//        userFormRegistration.setPassword("222222");
//        userFormRegistration = registrationUserLogic.encryptPassword(userFormRegistration);
//        //todo: please change order of variables. Moreover, please change unexpected value so it would be obvious that it is not expected.
//        assertNotEquals(userFormRegistration.getPassword(), "96e79218965eb72c92a549dd5a330112");
    }

    @Test
    public void convertUserFormRegistrationToUser_True() {
        UserFormRegistration userFormRegistration = new UserFormRegistration();

        userFormRegistration.setEmail("1");
        userFormRegistration.setPassword("2");
        userFormRegistration.setVerifyPassword("3");
        userFormRegistration.setFirstName("4");
        userFormRegistration.setLastName("5");
        userFormRegistration.setPhone("6");

        User user = registrationUserLogic.userFormRegistrationToUser(userFormRegistration);

        assertEquals(user.getEmail(), "1");
        assertEquals(user.getPassword(), "2");
        assertEquals(user.getFirstName(), "4");
        assertEquals(user.getLastName(), "5");
        assertEquals(user.getPhone(), "6");
    }

    @Test
    public void convertUserFormRegistrationToUser_False() {
        UserFormRegistration userFormRegistration = new UserFormRegistration();

        userFormRegistration.setEmail("1");
        userFormRegistration.setPassword("2");
        userFormRegistration.setVerifyPassword("3");
        userFormRegistration.setFirstName("4");
        userFormRegistration.setLastName("5");
        userFormRegistration.setPhone("6");

        User user = registrationUserLogic.userFormRegistrationToUser(userFormRegistration);

        assertNotEquals(user.getEmail(), "");
        assertNotEquals(user.getPassword(), "");
        assertNotEquals(user.getFirstName(), "");
        assertNotEquals(user.getLastName(), "");
        assertNotEquals(user.getPhone(), "");
    }

    @Test
    public void checkEmailAlreadyExists_True() {
        when(userFormRegistration.getEmail()).thenReturn("chewed.mole@gmail.com");
        when(userDaoRealization.isRegisteredEmail(userFormRegistration.getEmail())).thenReturn(true);

        userDaoRealization.isRegisteredEmail("chewed.mole@gmail.com");

        verify(userFormRegistration).getEmail();
        verify(userDaoRealization).isRegisteredEmail("chewed.mole@gmail.com");
    }
}
