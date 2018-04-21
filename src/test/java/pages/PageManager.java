package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageManager {

    private WebDriver driver;

    public AdminMainPage adminMainPage;
    public CatalogPage catalogPage;
    public MainPage mainPage;
    public CartPage cartPage;
    public ViewItemPage viewItemPage;

    public PageManager(WebDriver driver) {
        this.driver = driver;
        adminMainPage = initElements(new AdminMainPage(this));
        catalogPage = initElements(new CatalogPage(this));
        mainPage = initElements(new MainPage(this));
        cartPage = initElements(new CartPage(this));
        viewItemPage = initElements(new ViewItemPage(this));
    }

    private <T extends Page> T initElements(T page) {
        PageFactory.initElements(driver, page);
        return page;
    }

    public WebDriver getWebDriver() {
        return driver;
    }
}
