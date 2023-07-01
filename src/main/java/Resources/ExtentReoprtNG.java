package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReoprtNG {

        public  static ExtentReports getReportObject()
        {
           String path= System.getProperty("user.dir")+ "\\reptrs\\index.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(path);
            reporter.config().setReportName("Web Automation Testing");
            reporter.config().setDocumentTitle("Test Result");

            ExtentReports extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester" , "Sanjeev");
            return extent;

        }
}
