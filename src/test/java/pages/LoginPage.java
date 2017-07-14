package pages;

import framework.BaseTest;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static framework.Constants.loginButtonTitle;
import static framework.Constants.loginFieldTitle;
import static framework.Constants.passwordFieldTitle;

public class LoginPage extends BaseTest {
    WebDriver driver;

    @FindBy(how = How.CSS, using = "input[name=login]")
    public WebElement loginField;

    @FindBy(how = How.CSS, using = "input[name=passwd]")
    public WebElement passwordField;

    @FindBy(how = How.CSS, using = "button.auth__button")
    public WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MainPage login(String login, String password) {
        sendKeys(loginField, login, loginFieldTitle);
        sendKeys(passwordField, password, passwordFieldTitle);
        click(loginButton, loginButtonTitle);
        return new MainPage(driver);
    }

    public void verifyUserIsLoggedOut() throws TimeoutException {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        } catch (TimeoutException e) {
            throw e;
        }
    }
}
