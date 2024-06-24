package utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class ExtentReportListner implements ITestListener {
    protected static ExtentReports reports;
    protected static ExtentTest test;

    private static String resultPath = getResultPath();

    /**
     * Deletes the specified directory and all its contents.
     * @param directory The directory to be deleted.
     */
    public static void deleteDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    System.out.println(file.getName());
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        file.delete();
                    }
                }
            }
        }
    }

    /**
     * Creates a directory for storing test results if it does not already exist.
     * @return The path to the results directory.
     */
    private static String getResultPath() {
        String resultPath = "test";
        File resultDir = new File(resultPath);
        if (!resultDir.exists()) {
            resultDir.mkdirs();
        }
        return resultPath;
    }

    String reportLocation = "test-output/Report/" + resultPath + "/";

    /**
     * This method is invoked when a test starts.
     * @param result The result of the test method that is being executed.
     */
    @Override
    public void onTestStart(ITestResult result) {
        test = reports.startTest(result.getMethod().getMethodName());
        test.log(LogStatus.INFO, result.getMethod().getMethodName() + " test is starting.");
        System.out.println(result.getTestClass().getName());
        System.out.println(result.getMethod().getMethodName());
    }

    /**
     * This method is invoked when a test succeeds.
     * @param result The result of the test method that was executed.
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        if (test != null) {
            test.log(LogStatus.PASS, result.getMethod().getMethodName() + " test is passed.");
        }
    }

    /**
     * This method is invoked when a test fails.
     * @param result The result of the test method that was executed.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        if (test != null) {
            test.log(LogStatus.FAIL, result.getMethod().getMethodName() + " test is failed.");
        }
    }

    /**
     * This method is invoked when a test is skipped.
     * @param result The result of the test method that was executed.
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        if (test != null) {
            test.log(LogStatus.SKIP, result.getMethod().getMethodName() + " test is skipped.");
        }
    }

    /**
     * This method is invoked when a test fails but is within the success percentage.
     * @param result The result of the test method that was executed.
     */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Optional to implement
    }

    /**
     * This method is invoked before any test methods are executed.
     * @param context The context in which the tests are being executed.
     */
    @Override
    public void onStart(ITestContext context) {
        System.out.println(reportLocation + "  ReportLocation");
        reports = new ExtentReports(reportLocation + "ExtentReport.html");
    }

    /**
     * This method is invoked after all the test methods have been executed.
     * @param context The context in which the tests were executed.
     */
    @Override
    public void onFinish(ITestContext context) {
        if (reports != null) {
            reports.endTest(test);
            reports.flush();
        }
    }
}
