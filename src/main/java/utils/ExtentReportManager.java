package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ExtentTest test;
    public static String reportPath;

    public static ExtentReports getReportInstance() {
        Log.info("Initializing Extent Reports...");
        if (extent == null) {
            Log.info("Creating new Extent Report instance...");
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            reportPath = "reports/ExtentReport_" + timestamp + ".html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

            reporter.config().setDocumentTitle("Automation Test Report");
            reporter.config().setReportName("Test Execution Report");
            // reporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }

    public static ExtentTest createTest(String testName) {
        test = getReportInstance().createTest(testName);
        return test;
    }

    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        Log.info("Capturing screenshot...");
        try {
            Log.info("Taking screenshot...");
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String path = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";
            // path = "/screenshots/" + screenshotName + ".png";
            System.out.println("Path for screenshot is : " + path);
            Log.info("Path for screenshot is : " + path);
            FileUtils.copyFile(src, new File(path));
            return path;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
