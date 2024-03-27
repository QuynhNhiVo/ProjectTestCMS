package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import helpers.PropertiesHelpers;

public class ExtentReportManager {

    private static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports getExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("reports/extentreport/extentreport.html");
        reporter.config().setReportName(PropertiesHelpers.getValue("FRAMEWORK"));
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Framework Name", PropertiesHelpers.getValue("FRAMEWORK"));
        extentReports.setSystemInfo("Author", PropertiesHelpers.getValue("AUTHOR"));
        return extentReports;
    }
}
