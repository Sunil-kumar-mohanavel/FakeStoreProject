package Tests;

import Base.BaseTest;
import io.restassured.response.Response;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import com.aventstack.extentreports.Status;

public class CartAPITests extends BaseTest {
	
    private int createdProductId;

    // 🔹 Logs test result into Extent Report
    @AfterMethod
    public void getResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "❌ Test Failed: " + result.getName());
            test.log(Status.FAIL, "Reason: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "✅ Test Passed: " + result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "⚠️ Test Skipped: " + result.getName());
        }
        extent.flush();
    }

    @Test(priority = 1)
    public void SC_01_AddToCart() {
        test = extent.createTest("SC_01_AddToCart - Add Product to Cart");

        String cartJson = "{ \"userId\": 1, \"products\": [{ \"productId\": 5, \"quantity\": 1 }] }";
        test.log(Status.INFO, "Request Payload: " + cartJson);

        Response response = given()
                .body(cartJson)
                .post("/carts");

        test.log(Status.INFO, "Response Body: " + response.getBody().asString());
        test.log(Status.INFO, "Status Code: " + response.getStatusCode());

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, 201, "Expected 201 but got " + statusCode);
        createdProductId = response.jsonPath().getInt("id");

        test.log(Status.PASS, "Product added to cart successfully with ID: " + createdProductId);
        System.out.println("SC_01_AddToCart Response: " + response.getBody().asString());
    }

    @Test(priority = 2)
    public void SC_02_UpdateQuantity() {
        test = extent.createTest("SC_02_UpdateQuantity - Update Product Quantity in Cart");

        String updateJson = "{ \"products\": [{ \"productId\": 5, \"quantity\": 2 }] }";
        test.log(Status.INFO, "Request Payload: " + updateJson + " for cart ID: " + createdProductId);

        Response response = given()
                .body(updateJson)
                .put("/carts/"+createdProductId);

        test.log(Status.INFO, "Response Body: " + response.getBody().asString());
        test.log(Status.INFO, "Status Code: " + response.getStatusCode());

        assertEquals(response.getStatusCode(), 200);

        test.log(Status.PASS, "Cart quantity updated successfully.");
        System.out.println("SC_02_UpdateQuantity Response: " + response.getBody().asString());
    }

    @Test(priority = 3)
    public void SC_03_RemoveFromCart() {
        test = extent.createTest("SC_03_RemoveFromCart - Remove Product from Cart");

        Response response = given()
                .delete("/carts/" + createdProductId);

        test.log(Status.INFO, "Response Body: " + response.getBody().asString());
        test.log(Status.INFO, "Status Code: " + response.getStatusCode());

        assertEquals(response.getStatusCode(), 200);

        test.log(Status.PASS, "Product removed from cart successfully.");
        System.out.println("SC_03_RemoveFromCart Response: " + response.getBody().asString());
    }

    @Test(priority = 4)
    public void SC_04_ApplyValidCoupon() {
        test = extent.createTest("SC_04_ApplyValidCoupon - Simulate Applying Valid Coupon");

        String couponJson = "{ \"coupon\": \"DISCOUNT10\" }";
        test.log(Status.INFO, "Request Payload: " + couponJson + " for cart ID: " + createdProductId);

        Response response = given()
                .body(couponJson)
                .post("/carts/"+createdProductId);

        test.log(Status.INFO, "Response Body: " + response.getBody().asString());
        test.log(Status.INFO, "Status Code: " + response.getStatusCode());

        assertEquals(response.getStatusCode(), 404);
        test.log(Status.PASS, "Simulated valid coupon returned expected status 404.");
        System.out.println("SC_04_ApplyValidCoupon (Simulated): " + response.getBody().asString());
    }

    @Test(priority = 5)
    public void SC_05_ApplyInvalidCoupon() {
        test = extent.createTest("SC_05_ApplyInvalidCoupon - Simulate Applying Invalid Coupon");

        String couponJson = "{ \"coupon\": \"INVALID\" }";
        test.log(Status.INFO, "Request Payload: " + couponJson + " for cart ID: " + createdProductId);

        Response response = given()
                .body(couponJson)
                .post("/carts/"+createdProductId);

        test.log(Status.INFO, "Response Body: " + response.getBody().asString());
        test.log(Status.INFO, "Status Code: " + response.getStatusCode());

        assertEquals(response.getStatusCode(), 404);
        test.log(Status.PASS, "Simulated invalid coupon returned expected status 404.");
        System.out.println("SC_05_ApplyInvalidCoupon (Simulated): " + response.getBody().asString());
    }

    @Test(priority = 6)
    public void SC_06_EmptyCartCheckout() {
        test = extent.createTest("SC_06_EmptyCartCheckout - Simulate Empty Cart Checkout");

        Response response = given()
                .post("/carts/11/checkout");

        test.log(Status.INFO, "Response Body: " + response.getBody().asString());
        test.log(Status.INFO, "Status Code: " + response.getStatusCode());

        assertEquals(response.getStatusCode(), 415);
        test.log(Status.PASS, "Simulated empty cart checkout returned expected status 415.");
        System.out.println("SC_06_EmptyCartCheckout (Simulated): " + response.getBody().asString());
    }
}
