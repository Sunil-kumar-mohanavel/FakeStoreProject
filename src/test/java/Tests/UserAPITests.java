package Tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.ITestResult;

import Base.BaseTest;
import io.restassured.response.Response;
import com.aventstack.extentreports.Status;

public class UserAPITests extends BaseTest {

    // Log result to Extent Report after each test
    @AfterMethod
    public void getResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Failed: " + result.getName());
            test.log(Status.FAIL, "Reason: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Passed: " + result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Skipped: " + result.getName());
        }
        extent.flush();
    }

    @Test(priority = 1)
    public void UM_01_ValidLogin() {
        test = extent.createTest("UM_01_ValidLogin - Valid Login Test");

        String payload = "{ \"username\": \"johnd\", \"password\": \"m38rmF$\" }";
        test.log(Status.INFO, "Request Payload: " + payload);

        Response response = request.body(payload)
                .post("/auth/login")
                .then().log().all().extract().response();

        test.log(Status.INFO, "Response Body: " + response.asString());
        test.log(Status.INFO, "Status Code: " + response.getStatusCode());

        Assert.assertEquals(response.getStatusCode(), 201);
        test.log(Status.PASS, "Assertion Passed: Status code is 201");
        
        System.out.println("NewUserRegistration : " + response.asString());
    }

    @Test(priority = 2)
    public void UM_02_InvalidLogin() {
        test = extent.createTest("UM_02_InvalidLogin - Invalid Login Test");

        String payload = "{ \"username\": \"wrong\", \"password\": \"wrong\" }";
        test.log(Status.INFO, "Request Payload: " + payload);

        Response response = request.body(payload)
                .post("/auth/login")
                .then().log().all().extract().response();

        test.log(Status.INFO, "Response Body: " + response.asString());
        test.log(Status.INFO, "Status Code: " + response.getStatusCode());

        Assert.assertEquals(response.getStatusCode(), 401);
        test.log(Status.PASS, "Assertion Passed: Status code is 401");
        
        System.out.println("NewUserRegistration : " + response.asString());
    }

    @Test(priority = 3)
    public void UM_03_NewUserRegistration() {
        test = extent.createTest("UM_03_NewUserRegistration - New User Registration");

        String payload = "{ \"email\": \"test@test.com\", \"username\": \"test\", \"password\": \"test097\" }";
        test.log(Status.INFO, "Request Payload: " + payload);

        Response response = request.body(payload)
                .post("/users")
                .then().log().all().extract().response();

        test.log(Status.INFO, "Response Body: " + response.asString());
        test.log(Status.INFO, "Status Code: " + response.getStatusCode());

        Assert.assertEquals(response.getStatusCode(), 201);
        test.log(Status.PASS, "Assertion Passed: Status code is 201");
        
        System.out.println("NewUserRegistration : " + response.asString());
    }
}
