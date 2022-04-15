package at.positionsapp.browser;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Report extends Browser {

    public static String reportLocation = "C:\\Report\\"+ reportName() + ".html";
    public static ExtentReports extentRpt;
    public static ExtentSparkReporter sparkRpt;
    public static ExtentTest test;

    /**
     * Setup the Report in the path specified
     */
    public static void setupReport () {
        extentRpt = new ExtentReports();
        sparkRpt = new ExtentSparkReporter(reportLocation);
        sparkRpt.config(
                ExtentSparkReporterConfig.builder()
                        .theme(Theme.DARK)
                        .documentTitle("Selenium Report")
                        .build()
        );
        extentRpt.attachReporter(sparkRpt);
    }

    /**
     * Close the Report Generated
     */
    public static void closeReport () {
        extentRpt.flush();
    }

    /**
     * Log the steps during execution time
     * @param status
     * @param description
     * @param takeScreenshot
     */
    public static void log (Status status, String description, Boolean takeScreenshot) {
        if(takeScreenshot) {
            test.log(status, description, MediaEntityBuilder.createScreenCaptureFromPath(screenshot()).build());
        }
        else {
            test.log(status, description);
        }
    }


    /**
     * Takes an screenshot in the step
     * @return
     */
    private static String screenshot() {
        String strFileLocation;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("MMddyyy_hhmmss");
            Date date = new Date(System.currentTimeMillis());
            String strSSName = "SS_" + formatter.format(date);
            File scrFile = ((TakesScreenshot)Report.driver).getScreenshotAs(OutputType.FILE);
            strFileLocation = "C:\\Report\\images\\"+ strSSName + ".png";
            FileUtils.copyFile(scrFile, new File(strFileLocation));
            return strFileLocation;
        } catch(Exception e) {
            strFileLocation = "";
        }
        return strFileLocation;
    }

    private static String reportName() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMddyyy_hhmmss");
        Date date = new Date(System.currentTimeMillis());
        return "TestRun_" + formatter.format(date);
    }

}
