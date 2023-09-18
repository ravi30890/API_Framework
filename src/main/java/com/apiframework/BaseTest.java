package com.apiframework;

import com.apiframework.utils.reportMgmt.ExtentTestManager;
import com.apiframework.utils.reportMgmt.OutputUtil;
import com.aventstack.extentreports.Status;
import com.apiframework.utils.constants.PathConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTest {
    private final Logger _logger = LogManager.getLogger(BaseTest.class);

    @BeforeSuite(alwaysRun = true)
    public void initializeTestSuite(ITestContext context) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
        System.setProperty("current.date", LocalDateTime.now().format(format));
        OutputUtil.createOutputDirectory();

        System.setProperty("applogs.path", PathConfig.getApplogsPath() + "ExecutionLog.log");
        org.apache.logging.log4j.core.LoggerContext ctx =
                (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
        ctx.reconfigure();

        _logger.info("** Test Suite " + context.getName() + " started **");
    }

    @BeforeClass(alwaysRun = true)
    public void initializeTestClass(ITestContext context) {

    }

    @BeforeMethod(alwaysRun = true)
    public void config(ITestResult result) {
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult iTestResult) {
        if (iTestResult.getStatus() == ITestResult.FAILURE) {
            ExtentTestManager.log(_logger, Status.FAIL, iTestResult.getThrowable());
        } else if (iTestResult.getStatus() == ITestResult.SKIP) {
            ExtentTestManager.log(_logger, Status.SKIP, iTestResult.getThrowable());
        }
        ExtentTestManager.endTest();
    }

    @AfterSuite(alwaysRun = true)
    public void completeSuite(ITestContext context) {
        _logger.info("** Test Suite " + context.getName() + " ended **");
    }
}
