package utils;

import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RequestUtil {

    public static RequestSpecification getRequest() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all();
    }
}
