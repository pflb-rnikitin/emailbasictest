package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Nikitin Ruslan on 07.07.2017.
 */
public class SentPage {
    WebDriver driver;

    @FindBy(how= How.CSS, using = " div.ns-view-messages div.ns-view-messages-item-wrap:first-child")
    public WebElement firstSentMessageArea;

    @FindBy (how=How.CSS, using = "div.ns-view-messages-item-wrap:first-child span.mail-MessageSnippet-FromText")
    public WebElement firstSentMessageRecipient;

    @FindBy (how=How.CSS, using = "div.ns-view-messages-item-wrap:first-child span.mail-MessageSnippet-Item_subject > span")
    public WebElement firstSentMessageSubject;

    @FindBy (how=How.CSS, using = "div.ns-view-messages-item-wrap:first-child span.mail-MessageSnippet-Item_firstline > span")
    public WebElement firstSentMessageContent;

    public SentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
