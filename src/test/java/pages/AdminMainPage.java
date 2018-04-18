package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static util.LogHelper.logMe;

public class AdminMainPage extends Page {

    public AdminMainPage(PageManager pages) {
        super(pages);
    }

    private By loginInput = By.name("username");

    private By passInput = By.name("password");

    private By submitLoginBtn = By.name("login");

    private By logoutIcon = By.cssSelector(".fa-sign-out");

    private By header = By.cssSelector("h1");

    private By subMenuLink = By.cssSelector("li[id*=doc]");

    public AdminMainPage fillLoginData(String name, String pass) {
        logMe(name + " :" + pass);
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginInput));
        fillField(loginInput, name);
        fillField(passInput, pass);
        return this;
    }

    public void submitLogin() {
        logMe();
        click(submitLoginBtn);
    }

    public boolean isLoggedIn() {
        logMe();
        return isElementDisplayed(logoutIcon);
    }

    public List<WebElement> getSideBarMenuLinks() {
        return driver.findElements(By.cssSelector("#app-"));
    }

    public boolean isHeaderPresent(WebElement element) {
        logMe();
        wait.until(ExpectedConditions.stalenessOf(element));
        return isElementPresent(header);
    }

    public boolean isSubMenuLinkPresent() {
        logMe();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> list = (List<WebElement>) js.executeScript("return document.getElementsByClassName('docs')");
        return list.size() > 0;
    }

    public List<WebElement> getSubMenuLinks() {
        logMe();
        return driver.findElements(subMenuLink);
    }

    public void clickSideBarLink(WebElement link) {
        logMe(link.getText());
        click(link);
    }
}
