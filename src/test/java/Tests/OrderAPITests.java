package Tests;

import Base.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class OrderAPITests extends BaseTest {

    @Test
    public void CO_01_PlaceOrder() {
        String orderJson = "{ \"userId\": 1, \"paymentMethod\": \"credit_card\" }";

        Response response = given()
                .body(orderJson)
                .post("/carts/1/checkout");

        assertEquals(response.getStatusCode(), 200);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void CO_02_ViewOrderHistory() {
        Response response = given()
                .get("/carts/user/1");

        assertEquals(response.getStatusCode(), 200);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void CO_03_ReOrder() {
        Response response = given()
                .post("/carts/reorder/1");

        assertEquals(response.getStatusCode(), 200);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void CO_04_ReturnOrder() {
        Response response = given()
                .post("/carts/return/1");

        assertEquals(response.getStatusCode(), 200);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void CO_05_InvalidCheckout() {
        String incompleteOrder = "{ \"userId\": 1 }"; // missing payment info

        Response response = given()
                .body(incompleteOrder)
                .post("/carts/1/checkout");

        assertEquals(response.getStatusCode(), 400);
        System.out.println(response.getBody().asString());
    }
}
