package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

public abstract class Page {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private final int TIMEOUT = 5;
    public static final String baseUrl = "http://localhost/litecart/";
    public static final String adminUrl = baseUrl + "admin";


    public Page(PageManager pages) {
        driver = pages.getWebDriver();
        wait = new WebDriverWait(driver, TIMEOUT);
    }

    public void fillField(By path, String data) {
        driver.findElement(path).clear();
        driver.findElement(path).sendKeys(data);
    }

    public void uploadImage(By path, String imagePath) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(imagePath).getFile());
        driver.findElement(path).sendKeys(file.getAbsolutePath());
    }

    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void click(By path) {
        for (int i = 0; i < 5; i++) {
            try {
                wait.until(ExpectedConditions.visibilityOf(driver.findElement(path))).click();
                break;
            } catch (StaleElementReferenceException ex) {
            }
        }
    }

    public boolean isElementDisplayed(By path) {
        if (!driver.findElements(path).isEmpty()) {
            return driver.findElement(path).isDisplayed();
        }
        return false;
    }

    public boolean isElementPresent(By path) {
        return driver.findElements(path).size() > 0;
    }

    public boolean isElementNotPresent(By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            return driver.findElements(locator).size() == 0;
        } finally {
            driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        }
    }
}
