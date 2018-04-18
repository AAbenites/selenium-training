import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.stream.IntStream;

public class SecondTest extends TestBase {


    @Test
    public void presenceOfHeaderForAllMenuAndSubMenu() {
        driver.get("http://localhost/litecart/admin");
        getPages().adminMainPage.fillLoginData("admin", "admin").submitLogin();
        Assert.assertTrue("has to be authorized", getPages().adminMainPage.isLoggedIn());
        IntStream.range(0, getPages().adminMainPage.getSideBarMenuLinks().size()).forEach(i -> {
            WebElement link = getPages().adminMainPage.getSideBarMenuLinks().get(i);
            getPages().adminMainPage.clickSideBarLink(link);
            Assert.assertTrue("header should be in h1", getPages().adminMainPage.isHeaderPresent(link));
            if (getPages().adminMainPage.isSubMenuLinkPresent()) {
                IntStream.range(1, getPages().adminMainPage.getSubMenuLinks().size()).forEach(s -> {
                    WebElement subLink = getPages().adminMainPage.getSubMenuLinks().get(s);
                    getPages().adminMainPage.clickSideBarLink(subLink);
                    Assert.assertTrue(getPages().adminMainPage.isHeaderPresent(subLink));
                });

            }
        });

    }
}
