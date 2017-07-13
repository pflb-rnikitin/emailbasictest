package framework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;


public class BaseTest {
    protected static WebDriver driver = new ChromeDriver();
    protected static WebDriverWait wait = new WebDriverWait(driver, 30);
    protected static final Logger log = LogManager.getLogger(BaseTest.class);


    @BeforeClass
    public void setUp() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(Constants.BASE_URL);
    }

    protected static void click(WebElement element) {
        // String title = element.getClass().getAnnotation(ElementTitle.class).title();
        // String message = String.format("Click on element %d", title);
        String message = "hi";
        log.info(message);
        element.click();
    }

    protected static void sendKeys(WebElement element, String text) {
        //String title = element.getClass().getAnnotation(ElementTitle.class).title();
        //  String message = String.format("Enter %d in element %d",text,title);
        String message = "hi";
        log.info(message);
        element.sendKeys(text);
    }

    protected static void assertThat(ExpectedCondition<Boolean> condition){
        wait.until(condition);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
