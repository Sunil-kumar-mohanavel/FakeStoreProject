package Base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import utils.ExtentReport;

public class BaseTest {
    protected RequestSpecification request;
    protected static ExtentReports extent;
    protected static ExtentTest test;

    @BeforeClass
    public void setup() {
        // Initialize RestAssured
        RestAssured.baseURI = "https://fakestoreapi.com";
        request = RestAssured.given().log().all().header("Content-Type", "application/json");

        // Use the configured instance from ExtentReportManager
        extent = ExtentReport.getInstance();
    }

    @AfterSuite
    public void tearDownReport() {
        ExtentReport.flush();
    }
}
