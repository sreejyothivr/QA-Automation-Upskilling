package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportUtils {

    public static ExtentReports getReportObject() {

        String path = System.getProperty("user.dir") + "/reports/spark.html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        //test
        reporter.config().setDocumentTitle("Automation Report");
        reporter.config().setReportName("Selenium Automation");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Sreejyothi");

        return extent;
    }
}
