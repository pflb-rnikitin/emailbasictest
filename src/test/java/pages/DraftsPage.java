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

import javax.xml.bind.Element;

import static framework.Constants.firstDraftMessageAreaTitle;
import static framework.Constants.sendButtonTitle;
import static framework.Constants.sentMenuButtonTitle;

/**
 * Created by Nikitin Ruslan on 07.07.2017.
 */
public class DraftsPage extends BaseTest {
    WebDriver driver;

    @FindBy(how = How.CSS, using = "div.ns-view-toolbar-button-add-template")
    public WebElement createTemplateButton;

    @FindBy(how = How.CSS, using = "div.ns-view-messages-item-wrap:first-child span.mail-MessageSnippet-Item_subject > span")
    public WebElement firstDraftSubject;

    @FindBy(how = How.CSS, using = "div.ns-view-messages div.ns-view-messages-item-wrap:first-child")
    public WebElement firstDraftMessageArea;

    @FindBy(how = How.CSS, using = "button.js-send-button")
    public WebElement sendButton;

    @FindBy(how = How.CSS, using = "a[href=\"#sent\"]")
    public WebElement sentMenuButton;

    public DraftsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyFirstDraftSubject(String subject) throws TimeoutException {
        try {
            wait.until(ExpectedConditions.attributeToBe(firstDraftSubject, "title", subject));
        } catch (TimeoutException e) {
            throw e;
        }
    }

    public void verifyFirstDraftSubjectHasChanged(String subject) {
        refresh();
        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(firstDraftSubject, "title", subject)));
        } catch (TimeoutException e) {
            throw e;
        }
    }

    public void sendFirstDraft() {
        click(firstDraftMessageArea, firstDraftMessageAreaTitle);
        click(sendButton, sendButtonTitle);
    }

    public SentPage goToSent() {
        click(sentMenuButton, sentMenuButtonTitle);
        return new SentPage(driver);
    }
}