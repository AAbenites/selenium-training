import model.Product;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.PageManager;
import util.Listener;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ThirdTest {

    EventFiringWebDriver edr;
    PageManager pages;

    @Before
    public void start() {
        ChromeOptions cap = new ChromeOptions();
        cap.setCapability("unexpectedAlertBehaviour", "ignore");
        edr = new EventFiringWebDriver(new ChromeDriver(cap));
        edr.register(new Listener());
        initiateAllHelpers();

    }

    @After
    public void stop() {
        edr.quit();
    }

    private void initiateAllHelpers() {
        edr.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        pages = new PageManager(edr);
    }


    @Test
    public void newProductAddToCartRemove() {
        Map<Float, String> pC = new HashMap<>();
        pC.put(18F, "USD");
        Product product = new Product.ProductBuilder().setPriceCurrency(pC)
                .setImagePath("sherlock.png").build();
        pages.adminMainPage.loginAsAdminUser();
        pages.adminMainPage.openCatalogMenu();
        pages.catalogPage.addNewProduct(product);
        pages.catalogPage.saveNewProduct();
        Assert.assertTrue(pages.catalogPage.isProductPresent(product));

        pages.mainPage.open();
        pages.mainPage.openLatestProduct(product);
        pages.viewItemPage.addToCart();
        pages.viewItemPage.openSimilarItem(3);
        pages.viewItemPage.addToCart();
        Assert.assertEquals(2, pages.viewItemPage.getInCartItemsValue());
        pages.viewItemPage.clickCheckout();
        pages.cartPage.removeAllOneByOne();
        Assert.assertTrue(pages.cartPage.isEmptyCart());
    }
}
