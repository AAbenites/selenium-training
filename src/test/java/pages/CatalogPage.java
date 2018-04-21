package pages;

import model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import static util.LogHelper.logMe;

public class CatalogPage extends Page {

    public CatalogPage(PageManager pages) {
        super(pages);
    }

    public void addNewProduct(Product product) {
        logMe();
        click(By.cssSelector("[href*='doc=edit_product']"));
        fillField(By.name("name[en]"), product.getName());
        fillField(By.name("code"), product.getCode());
        click(By.cssSelector("[name='status'][value='1']"));
        fillField(By.name("quantity"), String.valueOf(product.getQuantity()));
        uploadImage(By.name("new_images[]"), product.getImagePath());
        click(By.cssSelector("[href='#tab-information']"));
        new Select(driver.findElement(By.name("manufacturer_id"))).selectByVisibleText(product.getManufacturer());
        fillField(By.name("keywords"), String.join(",", product.getKeywords()));
        fillField(By.name("short_description[en]"), product.getDescription());
        click(By.cssSelector("[href='#tab-prices']"));
        product.getPriceCurrency().forEach((k, v) -> {
            fillField(By.name("purchase_price"), String.valueOf(k));
            fillField(By.name("prices["+ v +"]"), String.valueOf(k));
            new Select(driver.findElement(By.name("purchase_price_currency_code"))).selectByValue(v);
        });
    }


    public void saveNewProduct() {
        logMe();
        click(By.name("save"));
    }

    public boolean isProductPresent(Product product) {
        return isElementDisplayed(By.xpath("//a[text()='"+product.getName()+"']"));
    }
}
