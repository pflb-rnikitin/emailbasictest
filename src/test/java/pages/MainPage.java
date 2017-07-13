package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    WebDriver driver;

    @FindBy (how=How.CSS, using="div.mail-User-Name")
    public WebElement loggedUserName;

    @FindBy (how=How.CSS, using="a.mail-ComposeButton")
    public WebElement composeButton;

    @FindBy (how=How.CSS, using="div[name=to]")
    public WebElement composeField;

    @FindBy (how=How.CSS, using="input[name=subj]")
    public WebElement subjectField;

    @FindBy (how=How.CSS, using="div.cke_wysiwyg_div")
    public WebElement contentField;

    @FindBy (how=How.CSS, using="div.ns-view-compose-cancel-button")
    public WebElement composeCancelButton;

    @FindBy (how=How.CSS, using="button[data-action=\"save\"] span._nb-button-text")
    public WebElement saveDraftButton;

    @FindBy (how=How.CSS, using="a[href='#draft']")
    public WebElement draftsMenuButton;

    @FindBy (how=How.CSS, using="a[href=\"#sent\"]")
    public WebElement sentMenuButton;

    @FindBy (how=How.CSS, using="div.mail-User")
    public WebElement userMenuButton;

    @FindBy (how=How.CSS, using="a[href*=\"action=logout\"]")
    public WebElement logoutMenuButton;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
