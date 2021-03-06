package framework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


import java.util.concurrent.TimeUnit;


public class BaseTest {
    protected static final Logger log = LogManager.getLogger(BaseTest.class);
    protected static WebDriver driver = new ChromeDriver();
    protected static WebDriverWait wait = new WebDriverWait(driver, 30);


    @BeforeClass
    public void setUp() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(Constants.BASE_URL);
    }

    protected static void click(WebElement element, String title) {
        String message = String.format("Клик по элементу %s", title);
        log.info(message);
        element.click();
    }

    protected static void sendKeys(WebElement element, String text, String title) {
        String message = String.format("Ввод %s в элемент %s", text, title);
        log.info(message);
        element.sendKeys(text);
    }

    protected static void refresh() {
        driver.navigate().refresh();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
