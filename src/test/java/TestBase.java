import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PageManager;

import java.util.concurrent.TimeUnit;

public class TestBase {

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public WebDriver driver;
    public WebDriverWait wait;
    private PageManager pages;

    @Before
    public void start() {
        ChromeDriverManager.getInstance().setup();

        if (tlDriver.get() != null) {
            driver = tlDriver.get();
            wait = new WebDriverWait(driver, 5);
            return;
        }

        driver = new ChromeDriver();
        initiateAllHelpers();
        tlDriver.set(driver);
        wait = new WebDriverWait(driver, 5);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    driver.quit();
                    driver = null;
                })
        );
    }

    private void initiateAllHelpers() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        pages = new PageManager(driver);
    }

    public PageManager getPages() {
        return pages;
    }

}
