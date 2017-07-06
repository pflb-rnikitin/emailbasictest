package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    @FindBy (how=How.CSS, using = "input[name=login]")
    public WebElement loginField;

    @FindBy (how=How.CSS, using = "input[name=passwd]")
    public WebElement passwordField;

    @FindBy (how=How.CSS, using = "button.auth__button")
    public WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
