package pages;

import framework.BaseTest;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;


import static framework.Constants.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class MainPage extends BaseTest {
    WebDriver driver;

    @FindBy(how = How.CSS, using = "div.mail-User-Name")
    public WebElement loggedUserName;

    @FindBy(how = How.CSS, using = "a.mail-ComposeButton")
    public WebElement composeButton;

    @FindBy(how = How.CSS, using = "div[name=to]")
    public WebElement toField;

    @FindBy(how = How.CSS, using = "input[name=subj]")
    public WebElement subjectField;

    @FindBy(how = How.CSS, using = "div.cke_wysiwyg_div")
    public WebElement contentField;

    @FindBy(how = How.CSS, using = "div.ns-view-compose-cancel-button")
    public WebElement composeCancelButton;

    @FindBy(how = How.CSS, using = "button[data-action=\"save\"] span._nb-button-text")
    public WebElement saveDraftButton;

    @FindBy(how = How.CSS, using = "a[href='#draft']")
    public WebElement draftsMenuButton;

    @FindBy(how = How.CSS, using = "a[href=\"#sent\"]")
    public WebElement sentMenuButton;

    @FindBy(how = How.CSS, using = "div.mail-User")
    public WebElement userMenuButton;

    @FindBy(how = How.CSS, using = "a[href*=\"action=logout\"]")
    public WebElement logoutMenuButton;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getLoggedUserName() {
        return loggedUserName.getText();
    }

    public void createDraftMessage(String to, String subject, String content) {
        click(composeButton, composeButtonTitle);
        sendKeys(toField, to, toFieldTitle);
        sendKeys(subjectField, subject, subjectFieldTitle);
        sendKeys(contentField, content, contentFieldTitle);
        click(composeCancelButton, composeCancelButtonTitle);
        wait.until(ExpectedConditions.elementToBeClickable(saveDraftButton));
        click(saveDraftButton, saveDraftButtonTitle);
    }

    public DraftsPage goToDrafts() {
        click(draftsMenuButton, draftsMenuButtonTitle);
        return new DraftsPage(driver);
    }

    public LoginPage logout() {
        click(userMenuButton, userMenuButtonTitle);
        wait.until(ExpectedConditions.elementToBeClickable(logoutMenuButton));
        click(logoutMenuButton, logoutMenuButtonTitle);
        return new LoginPage(driver);
    }
}
