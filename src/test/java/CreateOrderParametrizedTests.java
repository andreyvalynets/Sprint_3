import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
@DisplayName("Create order")
public class CreateOrderParametrizedTests {

    private OrderClient orderClient;
    private final String[] color;

    public CreateOrderParametrizedTests(String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getColor() {
        return new Object[][]{
                {new String[]{"BLACK", "GREY"}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK"}},
                {null}
        };
    }

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Create order with different colors")
    public void createOrderWithColors2() {
        Order order = Order.getRandom(color);
        Response response = orderClient.createOrder(order);

        assertEquals(201, response.getStatusCode());
    }
}
