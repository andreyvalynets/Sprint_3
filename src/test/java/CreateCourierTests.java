import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.Courier;
import model.CourierCredentials;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DisplayName("Create courier")
public class CreateCourierTests {

    private CourierClient courierClient;
    private int courierId;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @After
    public void tearDown() {
        courierClient.deleteCourier(courierId);
    }

    @Test
    @DisplayName("Create courier with valid data")
    public void courierCanBeCreatedWithValidData() {
        Courier courier = Courier.getRandom();
        Response response = courierClient.createCourier(courier);
        Response loginResponse = courierClient.loginAsCourier(CourierCredentials.from(courier));
        courierId = loginResponse.getBody().path("id");

        assertEquals("Курьер не создан", 201, response.getStatusCode());
        assertTrue(response.getBody().path("ok"));
        assertThat("Невалидный CourierId", courierId, is(not(0)));
    }


    @Test
    @DisplayName("Creating two identical couriers")
    public void cannotBeCreatedTwoIdenticalCouriers() {
        Courier courier = Courier.getRandom();
        Response firstCourierCreateResponse = courierClient.createCourier(courier);
        Response secondCourierCreateResponse = courierClient.createCourier(courier);
        Response loginResponse = courierClient.loginAsCourier(CourierCredentials.from(courier));
        courierId = loginResponse.getBody().path("id");

        assertEquals("Курьер не создан", 201, firstCourierCreateResponse.getStatusCode());
        assertEquals("Идентичный курьер создан", 409, secondCourierCreateResponse.getStatusCode());
    }

    @Test
    @DisplayName("Creating Courier with existing login")
    public void cannotBeCreatedCourierWithExistingLogin() {
        String name = RandomStringUtils.randomAlphabetic(10);
        Courier courier = new Courier(name, "pass123", "Adam");
        Courier courier2 = new Courier(name, "qwer321", "Alex");
        Response firstResponse = courierClient.createCourier(courier);
        Response secondResponse = courierClient.createCourier(courier2);

        Response loginResponse = courierClient.loginAsCourier(CourierCredentials.from(courier));
        courierId = loginResponse.getBody().path("id");
        assertEquals(201, firstResponse.getStatusCode());
        assertEquals("Курьер создан с существующим логином", 409, secondResponse.getStatusCode());
    }
}
