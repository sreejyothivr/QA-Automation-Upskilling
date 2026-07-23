package testComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentReportUtils;
import utils.ScreenshotUtil;

import java.io.IOException;

public class TestListener implements ITestListener {

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    ExtentReports extent = ExtentReportUtils.getReportObject();

    @Override
    public void onTestStart(ITestResult iTestResult) {

        ExtentTest test = extent.createTest(iTestResult.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {

        extent.flush();
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

        extentTest.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        Object currentClass = result.getInstance();
        WebDriver driver = ((BaseTest) currentClass).driver;

        String path = ScreenshotUtil.captureScreenshot(
                driver,
                result.getMethod().getMethodName());

        extentTest.get().log(Status.FAIL, result.getThrowable());

        extentTest.get().addScreenCaptureFromPath(path, "Failure Screenshot");
    }
}