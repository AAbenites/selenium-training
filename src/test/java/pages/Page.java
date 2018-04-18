package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public abstract class Page {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private final int TIMEOUT = 5;

    public Page(PageManager pages) {
        driver = pages.getWebDriver();
        wait = new WebDriverWait(driver, TIMEOUT);
    }

    public void fillField(By path, String data) {
        driver.findElement(path).clear();
        driver.findElement(path).sendKeys(data);
    }

    public void click(By path) {
        wait.until(ExpectedConditions.elementToBeClickable(path)).click();
    }

    public void click(WebElement element) {
        for (int i = 0; i < 5; i++) {
            try {
                wait.until(ExpectedConditions.visibilityOf(element)).click();
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
