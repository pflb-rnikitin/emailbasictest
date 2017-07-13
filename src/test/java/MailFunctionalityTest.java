import framework.BaseTest;
import framework.Constants;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DraftsPage;
import pages.LoginPage;
import pages.MainPage;
import pages.SentPage;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
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
    private static MainPage mainPage;
    private static LoginPage loginPage;
    private static DraftsPage draftsPage;


    @Test
    public void loginTest() {
        loginPage = new LoginPage(driver);
        sendKeys(loginPage.loginField, LOGIN);
        sendKeys(loginPage.passwordField, PASSWORD);
        click(loginPage.loginButton);
        mainPage = new MainPage(driver);
        assertThat(textToBePresentInElement(mainPage.loggedUserName, "v.m.varga"));
    }

    @Test(dependsOnMethods = {"loginTest"}, priority = 1)
    public void newDraftMessageTest() {
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
        draftsPage = new DraftsPage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(draftsPage.createTemplateButton));
        assertThat(attributeToBe(draftsPage.firstDraftSubject, "title", subject));
    }

    @Test(dependsOnMethods = {"loginTest", "newDraftMessageTest"}, priority = 2)
    public void verifyDraftIsSent() throws InterruptedException {
        click(draftsPage.firstDraftMessageArea);
        wait.until(ExpectedConditions.elementToBeClickable(draftsPage.sendButton));
        click(draftsPage.sendButton);
        SentPage sentPage = new SentPage(driver);
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