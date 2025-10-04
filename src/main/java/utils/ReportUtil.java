package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportUtil {

    private static ExtentReports extent;

    public static ExtentReports getReportInstance() {
        if (extent == null) {
            // Create a Spark Reporter
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/FakeStoreReport.html");
            
            // Optional: Configure report title
            sparkReporter.config().setDocumentTitle("FakeStore API Automation Report");
            sparkReporter.config().setReportName("FakeStore API Test Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }
}
