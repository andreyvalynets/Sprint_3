import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.Courier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
@DisplayName("Create courier")
public class CreateCourierParametrizeTests {

    private final String login;
    private final String password;

    private CourierClient courierClient;

    public CreateCourierParametrizeTests(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[][] getTextData() {
        return new Object[][]{
                {"kukla", ""},
                {"", "qwer1234"}
        };
    }

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Create courier without login or password")
    public void cannotBeCreatedCourierWithoutRequiredFields() {
        Courier courier = new Courier(login, password, "firstName");
        Response response = courierClient.createCourier(courier);

        assertEquals("Курьер создан без логина или пароля", 400, response.getStatusCode());
        assertEquals("Недостаточно данных для создания учетной записи", response.getBody().path("message"));
    }

}
