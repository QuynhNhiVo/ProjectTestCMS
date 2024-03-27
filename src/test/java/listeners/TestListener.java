package listeners;

import com.aventstack.extentreports.Status;
import helpers.CaptureHelpers;
import helpers.PropertiesHelpers;
import helpers.RecordHelpers;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.AllureManger;
import reports.ExtentReportManager;
import reports.ExtentTestManager;
import utils.LogUtils;

public class TestListener implements ITestListener {

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext result) {
        LogUtils.info("******************Start*******************");
        PropertiesHelpers.loadAllFiles();
    }

    @Override
    public void onFinish(ITestContext result) {
        LogUtils.info("******************Finish******************");
        if(PropertiesHelpers.getValue("EXTENT_REPORT").equals("true")){
            ExtentReportManager.getExtentReports().flush();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info("********Start Test Case: " + result.getName() + "********");

        if(PropertiesHelpers.getValue("RECORD").equals("true")){
            RecordHelpers.startRecord(result.getName());
        }
        if(PropertiesHelpers.getValue("EXTENT_REPORT").equals("true")){
            ExtentTestManager.saveToReport(getTestName(result), getTestDescription(result));
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("=====> " + " Test Success: " + result.getName());

        if(PropertiesHelpers.getValue("RECORD").equals("true")){
            RecordHelpers.stopRecord();
        }
        if(PropertiesHelpers.getValue("EXTENT_REPORT").equals("true")){
            ExtentTestManager.logMessage(Status.PASS, result.getName() + " is PASSED.");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.info("=====> " + " Test Failure: " + result.getName());
        if(PropertiesHelpers.getValue("SCREENSHOT").equals("true")){
            CaptureHelpers.takeScreenShot(result.getName());
        }
        if(PropertiesHelpers.getValue("RECORD").equals("true")){
            RecordHelpers.stopRecord();
        }
        if(PropertiesHelpers.getValue("EXTENT_REPORT").equals("true")){
            ExtentTestManager.addScreenShot(result.getName());
            ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());
            ExtentTestManager.logMessage(Status.FAIL, result.getName() + " is FAILED.");
        }
        AllureManger.saveTextLog(result.getName() + " is FAILED.");
        AllureManger.saveScreenshotPNG();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.warn("=====> " + " Skipped Test: " + result.getName());

        if(PropertiesHelpers.getValue("RECORD").equals("true")){
            RecordHelpers.stopRecord();
        }
        if(PropertiesHelpers.getValue("EXTENT_REPORT").equals("true")){
            ExtentTestManager.logMessage(Status.SKIP, result.getThrowable().toString());
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        LogUtils.info("=====> " + "on Test Failed But Within Success Percentage" + result.getName());
    }

}
