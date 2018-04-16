import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {

    @Test
    public void firstTest() {
        ChromeDriverManager.getInstance().setup();
        WebDriver drv = new ChromeDriver();
        drv.get("http://google.com");
        drv.quit();
    }
}
