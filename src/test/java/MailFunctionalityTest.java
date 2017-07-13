import framework.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;

import java.util.Date;
import static framework.Constants.LOGIN;
import static framework.Constants.PASSWORD;
import static org.openqa.selenium.support.ui.ExpectedConditions.attributeToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class MailFunctionalityTest extends BaseTest {

    private String getTimeStamp() {
        Date currentDate = new Date();
        String timeStamp = String.valueOf(currentDate.getTime());
        return timeStamp;
    }

    private String to = "enthao@gmail.com";
    private String subject = getTimeStamp();
    private String content = "message body";

    @Test
    public void loginTest() {
        sendKeys(loginPage.loginField, LOGIN);
        sendKeys(loginPage.passwordField, PASSWORD);
        click(loginPage.loginButton);
        assertThat(textToBePresentInElement(mainPage.loggedUserName, LOGIN));
    }

    @Test(dependsOnMethods = {"loginTest"}, priority = 1)
    public void newDraftMessageTest() {
        Assert.assertTrue(new MainPage.createDraft().goToDraft.isDraftSubjCorrect(subject));
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.composeButton));
        click(mainPage.composeButton);
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.contentField));
        sendKeys(mainPage.composeField, to);
        sendKeys(mainPage.subjectField, subject);
        sendKeys(mainPage.contentField, content);
        click(mainPage.composeCancelButton);
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.saveDraftButton));
        click(mainPage.saveDraftButton);
        click(mainPage.draftsMenuButton);
        wait.until(ExpectedConditions.elementToBeClickable(draftsPage.createTemplateButton));
        assertThat(attributeToBe(draftsPage.firstDraftSubject, "title", subject));
    }

    @Test(dependsOnMethods = {"loginTest", "newDraftMessageTest"}, priority = 2)
    public void verifyDraftIsSent() throws InterruptedException {
        click(draftsPage.firstDraftMessageArea);
        wait.until(ExpectedConditions.elementToBeClickable(draftsPage.sendButton));
        click(draftsPage.sendButton);
        click(mainPage.sentMenuButton);
        assertThat(attributeToBe(sentPage.firstSentMessageSubject, "title", subject));
    }

    @Test(dependsOnMethods = {"loginTest", "newDraftMessageTest", "verifyDraftIsSent"}, priority = 3)
    public void thereIsNoDraftLeftTest() {
        click(mainPage.draftsMenuButton);
        refresh();
        assertThat(ExpectedConditions.not(attributeToBe(draftsPage.firstDraftSubject, "title", subject)));
    }

    @Test(dependsOnMethods = {"loginTest"}, priority = 4)
    public void logoutTest() {
        click(mainPage.userMenuButton);
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.userMenuButton));
        click(mainPage.userMenuButton);
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.logoutMenuButton));
        click(mainPage.logoutMenuButton);
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.loginButton));
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
    }
}