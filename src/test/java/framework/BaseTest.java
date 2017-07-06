package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.log4testng.Logger;

public class BaseTest {
    protected static final Logger log = Logger.getLogger(BaseTest.class);

    protected static void click(WebElement element){
        String title = element.getClass().getAnnotation(ElementTitle.class).title();
        String message = String.format("Click on element %d", title);
        log.info(message);
        element.click();
    }

    protected static void sendKeys(WebElement element, String text){
        String title = element.getClass().getAnnotation(ElementTitle.class).title();
        String message = String.format("Enter %d in element %d",text, title);
        log.info(message);
        element.sendKeys(text);
    }
}
