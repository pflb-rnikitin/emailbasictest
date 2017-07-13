import framework.BaseTest;
import framework.Constants;
import framework.Steps;
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

    private String to = "testingexperience@gmail.com";
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

/*    @Test(dependsOnMethods = {"loginTest", "newDraftMessageTest"}, priority = 2)
    public void draftIsSentTest() throws InterruptedException {
        ArrayList<String> testList = sendADraftMessage(to, subject, content);
        Assert.assertEquals(testList.get(0), to);
        Assert.assertEquals(testList.get(1), subject);
        Assert.assertEquals(testList.get(2), content);
    }

    @Test(dependsOnMethods = {"loginTest", "newDraftMessageTest", "draftIsSentTest"}, priority = 3)
    public void thereIsNoDraftLeftTest() {
        ArrayList<String> testList = getLastDraft(to, subject, content);
        Assert.assertNotEquals(testList.get(0), to);
        Assert.assertNotEquals(testList.get(1), subject);
        Assert.assertNotEquals(testList.get(2), content);
    }

    @Test(dependsOnMethods = {"loginTest"}, priority = 4)
    public void logoutTest() {
        boolean b = logout();
        Assert.assertEquals(true, b);
    }*/
}