package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;

    By inventoryTitle = By.className("title");
    By backpackLink = By.xpath("//div[text()='Sauce Labs Backpack']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOnProductsPage() {
        return driver.findElement(inventoryTitle).getText().equalsIgnoreCase("Products");
    }

    public boolean isProductVisible(String productName) {
        return driver.getPageSource().contains(productName);
    }

    public void clickOnBackpackProduct() {
        driver.findElement(backpackLink).click();
    }

    public boolean isOnProductDetailPage() {
        return driver.getCurrentUrl().contains("inventory-item.html");
    }
}
