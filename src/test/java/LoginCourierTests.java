import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.CourierCredentials;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@DisplayName("Log In courier")
public class LoginCourierTests {

    private CourierClient courierClient;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Log In as non-existing courier")
    public void cannotLoginWithInvalidData() {
        String name = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(10);
        CourierCredentials courierCredentials = new CourierCredentials(name, password);
        Response loginResponse = courierClient.loginAsCourier(courierCredentials);

        assertEquals("Courier is logged with invalid data", 404, loginResponse.getStatusCode());
    }
}
