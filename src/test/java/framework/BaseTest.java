package framework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class BaseTest {
    protected static final Logger log = LogManager.getLogger(BaseTest.class);

    protected static void click(WebElement element){
       // String title = element.getClass().getAnnotation(ElementTitle.class).title();
       // String message = String.format("Click on element %d", title);
        String message = "click";
        log.info(message);
        element.click();
    }

    protected static void sendKeys(WebElement element, String text){
        //String title = element.getClass().getAnnotation(ElementTitle.class).title();
      //  String message = String.format("Enter %d in element %d",text,title);
        String message = "hi";
        log.info(message);
        element.sendKeys(text);
    }
}
