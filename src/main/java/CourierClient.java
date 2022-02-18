import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.Courier;
import model.CourierCredentials;

import static io.restassured.RestAssured.given;

public class CourierClient extends RestClient {

    public final String PATH = BASE_URL + "/api/v1/courier/";

    @Step("Create Courier {courier}")
    public Response createCourier(Courier courier) {
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(PATH);
    }

    @Step("Login  as  {courierCredentials}")
    public Response loginAsCourier(CourierCredentials courierCredentials) {
        return given()
                .spec(getBaseSpec())
                .body(courierCredentials)
                .when()
                .post(PATH + "login/");
    }

    @Step("Delete Courier {courierId}")
    public Response deleteCourier(int courierId) {
        return given()
                .spec(getBaseSpec())
                .when()
                .delete(PATH + courierId);
    }

}
