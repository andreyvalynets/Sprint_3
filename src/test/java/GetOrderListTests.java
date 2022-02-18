import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@DisplayName("Get orders")
public class GetOrderListTests {

    private OrderClient orderClient;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Get orders list")
    public void getOrdersListTest() {
        Response response = orderClient.getOrders();
        List<Object> orders = response.getBody().path("orders");

        assertEquals("Status Code is not as expected",200, response.getStatusCode());
        assertNotNull("There is no orders", orders);
    }
}
