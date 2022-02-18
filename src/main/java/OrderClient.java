import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.Order;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestClient{

    public final String PATH = BASE_URL + "/api/v1/orders/";

    @Step("Create Orders {order}")
    public Response createOrder(Order order) {
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(PATH);
    }

    @Step("Get Orders")
    public Response getOrders() {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(PATH);
    }
}
