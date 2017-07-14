import framework.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DraftsPage;
import pages.LoginPage;
import pages.MainPage;
import pages.SentPage;

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
    private LoginPage loginPage;

    @Test
    public void loginTest() {
        loginPage = new LoginPage(driver);
        Assert.assertEquals(loginPage.login(LOGIN, PASSWORD).getLoggedUserName(), LOGIN);
    }

    @Test(dependsOnMethods = {"loginTest"}, priority = 1)
    public void newDraftMessageTest() {
        mainPage = new MainPage(driver);
        mainPage.createDraftMessage(to, subject, content);
        mainPage.goToDrafts().verifyFirstDraftSubject(subject);
    }

    @Test(dependsOnMethods = {"loginTest", "newDraftMessageTest"}, priority = 2)
    public void verifyDraftIsSent() throws InterruptedException {
        draftsPage = new DraftsPage(driver);
        draftsPage.sendFirstDraft();
        draftsPage.goToSent().verifyFirstSentMessageSubject(subject);
    }

    @Test(dependsOnMethods = {"loginTest", "newDraftMessageTest", "verifyDraftIsSent"}, priority = 3)
    public void thereIsNoDraftLeftTest() {
        sentPage = new SentPage(driver);
        sentPage.goToDrafts().verifyFirstDraftSubjectHasChanged(subject);
    }

    @Test(dependsOnMethods = {"loginTest"}, priority = 4)
    public void logoutTest() {
        mainPage = new MainPage(driver);
        mainPage.logout().verifyUserIsLoggedOut();
    }
}