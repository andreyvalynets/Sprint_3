import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

        assertEquals("Статус код не как ожидаемый",200, response.getStatusCode());
        assertNotNull("Заказы не найдены", orders);
    }
}
