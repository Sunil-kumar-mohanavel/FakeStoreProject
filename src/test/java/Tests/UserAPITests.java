package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseTest;
import io.restassured.response.Response;


public class UserAPITests extends BaseTest {

    @Test(priority=1)
   
    public void UM_01_ValidLogin() {
        String payload = "{ \"username\": \"johnd\", \"password\": \"m38rmF$\" }";
        Response response = request.body(payload)
                .post("/auth/login")
                .then().log().all().extract().response();

       
        Assert.assertEquals(response.getStatusCode(), 201);
        
        System.out.println("NewUserRegistration : "+response.asString());
    }

    @Test(priority=2)
    
    public void UM_02_InvalidLogin() {
        String payload = "{ \"username\": \"wrong\", \"password\": \"wrong\" }";
        Response response = request.body(payload)
                .post("/auth/login")
                .then().log().all().extract().response();

      
        Assert.assertEquals(response.getStatusCode(), 401);
        
        System.out.println("NewUserRegistration : "+response.asString());
    }

    @Test(priority=3)
   
    public void UM_03_NewUserRegistration() {
        String payload = "{ \"email\": \"test@test.com\", \"username\": \"test\", \"password\": \"test097\" }";
        Response response = request.body(payload)
                .post("/users")
                .then().log().all().extract().response();

       
        Assert.assertEquals(response.getStatusCode(), 201);
        System.out.println("NewUserRegistration : "+response.asString());
	
        
    }
}
