package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenshotUtil {
    public static String takeScreenshot(WebDriver driver, String name) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String fileName = name + ".png";
            String fullPath = "reports/" + fileName;
            FileUtils.copyFile(src, new File(fullPath));
            return fileName; // Solo el nombre, para que se embeba bien en el HTML
        } catch (Exception e) {
            return null;
        }
    }
}
