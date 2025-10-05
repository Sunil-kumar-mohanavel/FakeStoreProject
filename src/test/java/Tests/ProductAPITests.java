package Tests;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Base.BaseTest;
import io.restassured.response.Response;
import com.aventstack.extentreports.Status;

public class ProductAPITests extends BaseTest {

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

    //  Test 1: Add Product
    @Test(priority = 1)
    public void PC_01_AddProduct() {
        test = extent.createTest("PC_01_AddProduct - Add New Product");

        String productJson = "{ \"title\": \"Test Product\", \"price\": 87.99, " +
                "\"description\": \"Test Desc\", \"category\": \"electronics\", " +
                "\"image\": \"https://i.pravatar.cc\" }";

        test.log(Status.INFO, "Request Payload: " + productJson);

        Response response = given()
                .contentType("application/json")
                .body(productJson)
                .post("/products");

        test.log(Status.INFO, "Response Body: " + response.getBody().asString());
        test.log(Status.INFO, "Status Code: " + response.getStatusCode());

        assertEquals(response.getStatusCode(), 201);
        createdProductId = response.jsonPath().getInt("id");
        assertEquals(response.jsonPath().getString("title"), "Test Product");

        test.log(Status.PASS, "Product created successfully with ID: " + createdProductId);
        System.out.println("AddProduct Response: " + response.getBody().asString());
    }

    //  Test 2: Edit Product
    @Test(priority = 2)
    public void PC_02_EditProduct() {
        test = extent.createTest("PC_02_EditProduct - Edit Existing Product");

        String updateJson = "{ \"price\": 92.99 }";
        test.log(Status.INFO, "Updating product with ID: " + createdProductId + " using payload: " + updateJson);

        Response response = given()
                .contentType("application/json")
                .body(updateJson)
                .put("/products/" + createdProductId);

        test.log(Status.INFO, "Response Body: " + response.getBody().asString());
        test.log(Status.INFO, "Status Code: " + response.getStatusCode());

        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.jsonPath().getFloat("price"), 92.99f);

        test.log(Status.PASS, "Product updated successfully with new price: 92.99");
        System.out.println("EditProduct Response: " + response.getBody().asString());
    }

    // Test 3: Get All Products
    @Test(priority = 3)
    public void PC_03_GetAllProduct() {
        test = extent.createTest("PC_03_GetAllProduct - Get All Products");

        Response response = given()
                .get("/products");

        test.log(Status.INFO, "Response Body: " + response.getBody().asString());
        test.log(Status.INFO, "Status Code: " + response.getStatusCode());

        assertEquals(response.getStatusCode(), 200);
        assertTrue(response.jsonPath().getList("$").size() >= 0);

        test.log(Status.PASS, "Fetched all products successfully.");
        System.out.println("SearchProduct Response: " + response.getBody().asString());
    }

    //  Test 4: Invalid Search
    @Test(priority = 4)
    public void PC_04_InvalidSearch() {
        test = extent.createTest("PC_04_InvalidSearch - Invalid Product Filter");

        Response response = given()
                .get("/products");

        test.log(Status.INFO, "Response Body: " + response.getBody().asString());
        test.log(Status.INFO, "Filtering manually for 'invalidproduct'");

        assertEquals(response.getStatusCode(), 200);

        List<Map> filtered = response.jsonPath().getList("$", Map.class)
                .stream()
                .filter(p -> ((String)((Map)p).get("title")).toLowerCase().contains("invalidproduct"))
                .toList();

        assertEquals(filtered.size(), 0);
        test.log(Status.PASS, "No product found for invalid search term as expected.");

        System.out.println("InvalidSearch Response: " + filtered);
    }

    //  Test 5: Delete Product
    @Test(priority = 5)
    public void PC_05_DeleteProduct() {
        test = extent.createTest("PC_05_DeleteProduct - Delete Created Product");

        Response response = given()
                .delete("/products/" + createdProductId);

        test.log(Status.INFO, "Deleting product with ID: " + createdProductId);
        test.log(Status.INFO, "Response Body: " + response.getBody().asString());
        test.log(Status.INFO, "Status Code: " + response.getStatusCode());

        assertEquals(response.getStatusCode(), 200);

        test.log(Status.PASS, "Product deleted successfully.");
        System.out.println("DeleteProduct Response: " + response.getBody().asString());
    }
}
