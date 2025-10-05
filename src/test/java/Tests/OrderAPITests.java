package Tests;

import Base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import com.aventstack.extentreports.Status;

public class OrderAPITests extends BaseTest {

    private final int userId = 1;
    private final int orderId = 1;

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
    public void CO_01_ViewOrderHistory() {
        test = extent.createTest("CO_01_ViewOrderHistory - View User Order History");

        Response response = given()
                .get("/carts/user/" + userId);

        test.log(Status.INFO, "Response Body: " + response.getBody().asString());
        test.log(Status.INFO, "Status Code: " + response.getStatusCode());

        assertEquals(response.getStatusCode(), 200);
        test.log(Status.PASS, "Order history retrieved successfully.");

        System.out.println("CO_01_ViewOrderHistory : " + response.getBody().asString());
    }

    @Test(priority = 2)
    public void CO_02_PlaceOrder() {
        test = extent.createTest("CO_02_PlaceOrder - Simulated Place Order");

        String orderJson = "{ \"userId\": " + userId + ", \"paymentMethod\": \"credit_card\" }";
        test.log(Status.INFO, "Request Payload: " + orderJson);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(orderJson)
                .post("/carts/" + userId + "/checkout");

        test.log(Status.INFO, "Response Body: " + response.getBody().asString());
        test.log(Status.INFO, "Status Code: " + response.getStatusCode());

        assertEquals(response.getStatusCode(), 404);
        test.log(Status.PASS, "Simulated place order returned expected status 404.");

        System.out.println("CO_02_PlaceOrder (Simulated): " + response.getBody().asString());
    }

    @Test(priority = 3)
    public void CO_03_ReOrder() {
        test = extent.createTest("CO_03_ReOrder - Simulated Reorder");

        Response response = given()
                .contentType(ContentType.JSON)
                .post("/carts/reorder/" + orderId);

        test.log(Status.INFO, "Response Body: " + response.getBody().asString());
        test.log(Status.INFO, "Status Code: " + response.getStatusCode());

        assertEquals(response.getStatusCode(), 404);
        test.log(Status.PASS, "Simulated reorder returned expected status 404.");

        System.out.println("CO_03_ReOrder (Simulated): " + response.getBody().asString());
    }

    @Test(priority = 4)
    public void CO_04_ReturnOrder() {
        test = extent.createTest("CO_04_ReturnOrder - Simulated Return Order");

        Response response = given()
                .contentType(ContentType.JSON)
                .post("/carts/return/" + orderId);

        test.log(Status.INFO, "Response Body: " + response.getBody().asString());
        test.log(Status.INFO, "Status Code: " + response.getStatusCode());

        assertEquals(response.getStatusCode(), 404);
        test.log(Status.PASS, "Simulated return order returned expected status 404.");

        System.out.println("CO_04_ReturnOrder (Simulated): " + response.getBody().asString());
    }

    @Test(priority = 5)
    public void CO_05_InvalidCheckout() {
        test = extent.createTest("CO_05_InvalidCheckout - Simulated Invalid Checkout");

        String incompleteOrder = "{ \"userId\": " + userId + " }";
        test.log(Status.INFO, "Request Payload: " + incompleteOrder);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(incompleteOrder)
                .post("/carts/" + userId + "/checkout");

        test.log(Status.INFO, "Response Body: " + response.getBody().asString());
        test.log(Status.INFO, "Status Code: " + response.getStatusCode());

        assertEquals(response.getStatusCode(), 404);
        test.log(Status.PASS, "Simulated invalid checkout returned expected status 404.");

        System.out.println("CO_05_InvalidCheckout (Simulated): " + response.getBody().asString());
    }
}
