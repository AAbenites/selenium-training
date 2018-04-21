package pages;

import org.openqa.selenium.By;

import static util.LogHelper.logMe;

public class ViewItemPage extends Page {
    public ViewItemPage(PageManager pages) {
        super(pages);
    }

    public void addToCart() {
        logMe();
        int count = getInCartItemsValue();
        click(By.cssSelector("[name=add_cart_product]"));
        wait.until(WebDriver -> getInCartItemsValue() != count);
    }

    public void openSimilarItem(int i) {
        logMe(i);
        click(driver.findElements(By.xpath("//div[@id='box-similar-products']//li")).get(i));
    }

    public int getInCartItemsValue() {
        logMe();
        return Integer.valueOf(driver.findElement(By.cssSelector("#cart>a>.quantity")).getText());
    }

    public void clickCheckout() {
        logMe();
        click(By.cssSelector(".link[href*='checkout']"));
    }
}
