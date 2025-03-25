package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import utils.ExtentReportManager;
import utils.ScreenshotUtil;

import java.io.IOException;
import java.time.Duration;

public class LoginBusquedaTest {
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setUpReport() {
        extent = ExtentReportManager.getInstance();
    }

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless=new"); // ✅ Modo headless compatible con
        // Actions
        // options.addArguments("--no-sandbox"); // ✅ Necesario en entornos CI
        // options.addArguments("--disable-dev-shm-usage"); // ✅ Para evitar errores de
        // memoria compartida

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().setSize(new Dimension(1920, 1080)); // Fija tamaño para capturas legibles
    }

    @Test
    public void testLoginAndSearchProduct() throws IOException {
        test = extent.createTest("Login y búsqueda de producto");
        driver.get("https://www.saucedemo.com/");
        test.info("Página abierta: saucedemo.com");

        // Ingresar credenciales manualmente para capturar antes del click
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        String screenshot1 = ScreenshotUtil.takeScreenshot(driver, "login");
        test.info("Datos de login ingresados", MediaEntityBuilder.createScreenCaptureFromPath(screenshot1).build());

        // Hacer login
        driver.findElement(By.id("login-button")).click();

        // Validar que cargó página de productos
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isOnProductsPage(), "No se mostró la página de productos");

        String screenshot2 = ScreenshotUtil.takeScreenshot(driver, "productos");
        test.pass("Página de productos visible", MediaEntityBuilder.createScreenCaptureFromPath(screenshot2).build());

        // Validar que el producto está en la lista
        Assert.assertTrue(homePage.isProductVisible("Sauce Labs Backpack"), "Producto no visible");

        Assert.assertTrue(homePage.isProductVisible("Sauce Labs Backpack"), "Producto no visible");

        homePage.clickOnBackpackProduct();

        Assert.assertTrue(homePage.isOnProductDetailPage(), "No se abrió la página del detalle del producto");

        String screenshot3 = ScreenshotUtil.takeScreenshot(driver, "detalle-producto");
        test.pass("Detalle del producto abierto", MediaEntityBuilder.createScreenCaptureFromPath(screenshot3).build());

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @AfterClass
    public void tearDownReport() {
        extent.flush();
    }
}
