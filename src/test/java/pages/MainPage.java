package pages;

import model.Product;
import org.openqa.selenium.By;

import static util.LogHelper.logMe;

public class MainPage extends Page {
    public MainPage(PageManager pages) {
        super(pages);
    }

    public void open() {
        driver.get(baseUrl);
    }

    public void openLatestProduct(Product product) {
        logMe();
        click(By.xpath("//div[@id='box-latest-products']//a[@title='" + product.getName() + "'][1]"));
    }
}
