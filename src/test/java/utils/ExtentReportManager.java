package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance("reports/ExtentReport.html");
        }
        return extent;
    }

    public static ExtentReports createInstance(String fileName) {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);

        htmlReporter.config().setDocumentTitle("Demo Automation");
        htmlReporter.config().setReportName("Demo Selenium ");
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setOfflineMode(true); // No carga desde CDN

        // Inyectar el logo de BSide usando JS (logo.png debe estar en /reports/)
        htmlReporter.config().setJs(
                "document.addEventListener('DOMContentLoaded', function() {" +
                        "  var logo = document.createElement('img');" +
                        "  logo.src = 'logo.png';" +
                        "  logo.style.height = '40px';" +
                        "  logo.style.marginRight = '10px';" +
                        "  logo.style.verticalAlign = 'middle';" +
                        "  var title = document.querySelector('.report-name');" +
                        "  if (title) {" +
                        "    title.insertAdjacentElement('afterbegin', logo);" +
                        "  }" +
                        "});");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        return extent;
    }
}
