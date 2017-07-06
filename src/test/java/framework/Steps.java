package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;

public class Steps extends BaseTest {
    public WebDriver driver = new ChromeDriver();
    private static MainPage mainPage;

    public void login(String username, String password){
        driver.manage().window().maximize();
        driver.get(Constants.BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        BaseTest.sendKeys(loginPage.loginField, username);
        BaseTest.sendKeys(loginPage.passwordField, password);
        BaseTest.click(loginPage.loginButton);
        //new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(mainPage.loggedUserName));
    }

/*    protected static void createPerson(String FirstName, String LastName) throws InterruptedException {
        BaseTest.click(mainPage.addPersonButton);
        BaseTest.sendKeys(mainPage.PersonFirstNameField, FirstName);
        BaseTest.sendKeys(mainPage.PersonLastNameField, LastName);
        BaseTest.click(mainPage.addPersonDialogButton);
        Thread.sleep(5000);

    }

    public static void logout() {
        BaseTest.click(mainPage.logoutButton);
    }*/
}
