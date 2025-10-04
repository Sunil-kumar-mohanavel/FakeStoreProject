package Tests;

import Base.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CartAPITests extends BaseTest {

    @Test
    public void SC_01_AddToCart() {
        String cartJson = "{ \"userId\": 1, \"products\": [{ \"id\": 5, \"quantity\": 1 }] }";

        Response response = given()
                .body(cartJson)
                .post("/carts");

        assertEquals(response.getStatusCode(), 200);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void SC_02_RemoveFromCart() {
        Response response = given()
                .delete("/carts/1");

        assertEquals(response.getStatusCode(), 200);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void SC_03_UpdateQuantity() {
        String updateJson = "{ \"products\": [{ \"id\": 5, \"quantity\": 2 }] }";

        Response response = given()
                .body(updateJson)
                .put("/carts/1");

        assertEquals(response.getStatusCode(), 200);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void SC_04_ApplyValidCoupon() {
        String couponJson = "{ \"coupon\": \"DISCOUNT10\" }";

        Response response = given()
                .body(couponJson)
                .post("/carts/1/apply-coupon");

        assertEquals(response.getStatusCode(), 200);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void SC_05_ApplyInvalidCoupon() {
        String couponJson = "{ \"coupon\": \"INVALID\" }";

        Response response = given()
                .body(couponJson)
                .post("/carts/1/apply-coupon");

        assertEquals(response.getStatusCode(), 400); // simulate error
        System.out.println(response.getBody().asString());
    }

    @Test
    public void SC_06_EmptyCartCheckout() {
        Response response = given()
                .post("/carts/1/checkout");

        assertEquals(response.getStatusCode(), 400); // simulate empty cart error
        System.out.println(response.getBody().asString());
    }
}
