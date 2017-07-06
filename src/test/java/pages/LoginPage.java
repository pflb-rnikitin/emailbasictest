package pages;

import framework.ElementTitle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    @FindBy (how=How.CSS, using = "input[name=login]")
    @ElementTitle(title = "Login Field")
    public WebElement loginField;

    @FindBy (how=How.CSS, using = "input[name=passwd]")
    @ElementTitle(title = "Password Field")
    public WebElement passwordField;

    @FindBy (how=How.CSS, using = "button.auth__button")
    @ElementTitle(title = "Login Button")
    public WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
