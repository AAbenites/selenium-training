package util;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.File;
import java.io.IOException;

public class Listener extends AbstractWebDriverEventListener {

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        System.out.println("Navigate to: " + url);
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println("Find element: " + by);
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        System.out.println("Click on: " + element);
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        System.out.println("dou!!!  : "+throwable.getMessage());

        File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tempFile, new File(System.currentTimeMillis()+ "screen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
