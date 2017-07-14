package pages;

import framework.BaseTest;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static framework.Constants.draftsMenuButtonTitle;

/**
 * Created by Nikitin Ruslan on 07.07.2017.
 */
public class SentPage extends BaseTest{
    WebDriver driver;

    @FindBy(how= How.CSS, using = " div.ns-view-messages div.ns-view-messages-item-wrap:first-child")
    public WebElement firstSentMessageArea;

    @FindBy (how=How.CSS, using = "div.ns-view-messages-item-wrap:first-child span.mail-MessageSnippet-Item_subject > span")
    public WebElement firstSentMessageSubject;

    @FindBy(how = How.CSS, using = "a[href='#draft']")
    public WebElement draftsMenuButton;

    public SentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyFirstSentMessageSubject(String subject) throws TimeoutException{
        try {
            wait.until(ExpectedConditions.attributeToBe(firstSentMessageSubject, "title", subject));
        } catch (TimeoutException e) {
            throw e;
        }
    }

    public DraftsPage goToDrafts() {
        click(draftsMenuButton, draftsMenuButtonTitle);
        return new DraftsPage(driver);
    }
}
