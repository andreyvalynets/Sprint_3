import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.Courier;
import model.CourierCredentials;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
@DisplayName("Log In courier")
public class LoginCourierWithoutLoginOrPasswordParametrizedTests {

    private final String login;
    private final String password;

    private int courierId;

    private CourierClient courierClient;

    public LoginCourierWithoutLoginOrPasswordParametrizedTests(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[][] getTextData() {
        return new Object[][]{
                {"validLogin", ""},
                {"", "validPassword"}
        };
    }

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @After
    public void tearDown() {
        courierClient.deleteCourier(courierId);
    }

    @Test
    @DisplayName("Log In courier without login or password")
    public void cannotLogInWithEmptyLoginOrPassword() {
        String loginValid = RandomStringUtils.randomAlphabetic(10);
        String passwordValid = RandomStringUtils.randomAlphabetic(10);
        Courier courier = new Courier(loginValid, passwordValid, "firstName");
        CourierCredentials courierCredentials = new CourierCredentials(login, password);
        Response createCourierResponse = courierClient.createCourier(courier);
        assertEquals(201, createCourierResponse.getStatusCode());

        Response loginCourierWithoutLoginOrPasswordResponse = courierClient.loginAsCourier(courierCredentials);
        assertEquals(400, loginCourierWithoutLoginOrPasswordResponse.getStatusCode());
        assertEquals("Недостаточно данных для входа", loginCourierWithoutLoginOrPasswordResponse.getBody().path("message"));

        Response loginCourierWithValidData = courierClient.loginAsCourier(courierCredentials.from(courier));
        assertEquals(201, createCourierResponse.getStatusCode());
        courierId = loginCourierWithValidData.getBody().path("id");
    }

}