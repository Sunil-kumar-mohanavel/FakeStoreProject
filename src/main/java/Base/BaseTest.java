package Base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected RequestSpecification request;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://fakestoreapi.com"; // Fake Store API
        request = RestAssured.given().log().all().header("Content-Type", "application/json");
    }
}
