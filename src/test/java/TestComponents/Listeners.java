package TestComponents;

import Resources.ExtentReoprtNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {
    ExtentTest test;
    ExtentReports extent = ExtentReoprtNG.getReportObject();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result)
    {

        // create test for each testcases Beacuse each test come in this block at the of starting
       test = extent.createTest(result.getMethod().getMethodName());
       extentTest.set(test); // Thread safe for unique thread id

    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        extentTest.get().log(Status.PASS , "Test Passed");

    }

    @Override
    public void onTestFailure(ITestResult result)
    {
        extentTest.get().fail(result.getThrowable());

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
                    .get(result.getInstance());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        // Attached Screenshot
        String filePath = null;
        try {
            filePath = String.valueOf(GetScreenshot(result.getMethod().getMethodName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        extentTest.get().addScreenCaptureFromPath(filePath , result.getMethod().getMethodName());

    }

    @Override
    public void onTestSkipped(ITestResult result)
    {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result)
    {

    }

    @Override
    public void onStart(ITestContext context)
    {

    }

    @Override
    public void onFinish(ITestContext context)
    {
        extent.flush();

    }
}
