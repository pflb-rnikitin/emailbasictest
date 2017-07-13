package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Nikitin Ruslan on 07.07.2017.
 */
public class DraftsPage {
    WebDriver driver;

    @FindBy(how= How.CSS, using = "div.ns-view-toolbar-button-add-template")
    public WebElement createTemplateButton;

    @FindBy (how=How.CSS, using = "div.ns-view-messages-item-wrap:first-child span.mail-MessageSnippet-FromText")
    public WebElement firstDraftRecipient;

    @FindBy (how=How.CSS, using = "div.ns-view-messages-item-wrap:first-child span.mail-MessageSnippet-Item_subject > span")
    public WebElement firstDraftSubject;

    @FindBy (how=How.CSS, using = "div.ns-view-messages-item-wrap:first-child span.mail-MessageSnippet-Item_firstline > span")
    public WebElement firstDraftContent;

    @FindBy (how=How.CSS, using = "div.ns-view-messages div.ns-view-messages-item-wrap:first-child")
    public WebElement firstDraftMessageArea;

    @FindBy (how=How.CSS, using = "button.js-send-button")
    public WebElement sendButton;

    public DraftsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}

