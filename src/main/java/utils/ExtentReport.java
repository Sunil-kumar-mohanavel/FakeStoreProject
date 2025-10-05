package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReport {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            // Add timestamp so every run creates a unique file
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportPath = "target/FakeStoreAPI_Report_" + timestamp + ".html";

            // Enable rich dashboard + charts
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath)
                    .viewConfigurer()
                    .viewOrder()
                    .as(new ViewName[]{ViewName.DASHBOARD, ViewName.TEST, ViewName.AUTHOR, ViewName.DEVICE})
                    .apply();

           
            spark.config().setTheme(Theme.STANDARD);  
            spark.config().setDocumentTitle("Fake Store API Test Results");
            spark.config().setReportName("Fake Store API Automation Test Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            // System info section
            extent.setSystemInfo("Project", "Fake Store API Automation");
            extent.setSystemInfo("Tester", "Test");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Generated On", timestamp);
        }
        return extent;
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}
