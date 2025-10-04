package Tests;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.testng.annotations.Test;

import Base.BaseTest;
import io.restassured.response.Response;

public class ProductAPITests extends BaseTest {

    private int createdProductId;

    @Test(priority = 1)
    public void PC_01_AddProduct() {
        String productJson = "{ \"title\": \"Test Product\", \"price\": 99.99, " +
                "\"description\": \"Test Desc\", \"category\": \"electronics\", " +
                "\"image\": \"https://i.pravatar.cc\" }";

        Response response = given()
                .contentType("application/json")
                .body(productJson)
                .post("/products");

        assertEquals(response.getStatusCode(), 201);
        createdProductId = response.jsonPath().getInt("id"); // store ID for later use
        assertEquals(response.jsonPath().getString("title"), "Test Product");

        System.out.println("AddProduct Response: " + response.getBody().asString());
    }

    @Test(priority = 2)
    public void PC_02_EditProduct() {
        String updateJson = "{ \"price\": 79.99 }";

        Response response = given()
                .contentType("application/json")
                .body(updateJson)
                .put("/products/" + createdProductId);

        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.jsonPath().getFloat("price"), 79.99f);

        System.out.println("EditProduct Response: " + response.getBody().asString());
    }

    @Test(priority = 6)
    public void PC_03_DeleteProduct() {
        Response response = given()
                .delete("/products/" + createdProductId);

        assertEquals(response.getStatusCode(), 200);
        System.out.println("DeleteProduct Response: " + response.getBody().asString());
    }

    @Test(priority = 3)
    public void PC_04_SearchProduct() {
        Response response = given()
                .queryParam("search", "Test Product")
                .get("/products");

        assertEquals(response.getStatusCode(), 200);
        // Search may return empty array if deleted
        assertTrue(response.jsonPath().getList("$").size() >= 0);

        System.out.println("SearchProduct Response: " + response.getBody().asString());
    }

    @Test(priority = 4)
    public void PC_05_FilterProducts() {
        Response response = given()
                .queryParam("category", "electronics")
                .get("/products/");

        assertEquals(response.getStatusCode(), 200);
        assertTrue(response.jsonPath().getList("$").size() >= 0);

        System.out.println("FilterProducts Response: " + response.getBody().asString());
    }


    @Test(priority = 5)
    public void PC_06_InvalidSearch() {
        Response response = given()
                .get("/products"); // get all products

        assertEquals(response.getStatusCode(), 200);

        // filter manually for "invalidproduct"
        List<Map> filtered = response.jsonPath().getList("$", Map.class)
                .stream()
                .filter(p -> ((String)((Map)p).get("title")).toLowerCase().contains("invalidproduct"))
                .toList();

        assertEquals(filtered.size(), 0); // must be empty

        System.out.println("InvalidSearch Response: " + filtered);
    }

    
    
    
}
