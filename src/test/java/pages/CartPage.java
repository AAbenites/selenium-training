package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.stream.Collectors;

import static util.LogHelper.logMe;

public class CartPage extends Page {
    public CartPage(PageManager pages) {
        super(pages);
    }

    public void removeAllOneByOne() {
        logMe();
        By path = By.cssSelector("[name=remove_cart_item]");
        while (driver.findElements(path).size() > 0) {
            WebElement q = driver.findElement(By.cssSelector(".quantity"));
            driver.findElements(path).stream().filter(d -> d.isDisplayed())
                    .collect(Collectors.toList()).get(0).click();
            wait.until(ExpectedConditions.stalenessOf(q));
        }
    }

    public boolean isEmptyCart() {
        return isElementNotPresent(By.cssSelector(".shortcut"));
    }
}
