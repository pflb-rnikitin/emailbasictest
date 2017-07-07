package framework;

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

public class Steps extends BaseTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private static MainPage mainPage;
    private static LoginPage loginPage;
    private static DraftsPage draftsPage;


    protected void openBrowser() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    protected String login(String username, String password) {
        driver.manage().window().maximize();
        driver.get(Constants.BASE_URL);
        loginPage = new LoginPage(driver);
        BaseTest.sendKeys(loginPage.loginField, username);
        BaseTest.sendKeys(loginPage.passwordField, password);
        BaseTest.click(loginPage.loginButton);
        mainPage = new MainPage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.loggedUserName));
        return mainPage.loggedUserName.getText();
    }

    protected ArrayList<String> createADraftMessage(String to, String subject, String content) {
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.composeButton));
        BaseTest.click(mainPage.composeButton);
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.contentField));
        BaseTest.sendKeys(mainPage.composeField, to);
        BaseTest.sendKeys(mainPage.subjectField, subject);
        BaseTest.sendKeys(mainPage.contentField, content);
        BaseTest.click(mainPage.composeCancelButton);
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.saveDraftButton));
        BaseTest.click(mainPage.saveDraftButton);
        BaseTest.click(mainPage.draftsMenuButton);
        draftsPage = new DraftsPage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(draftsPage.createTemplateButton));
        wait.until(ExpectedConditions.attributeToBe(draftsPage.firstDraftRecipient, "title", to));
        wait.until(ExpectedConditions.attributeToBe(draftsPage.firstDraftSubject, "title", subject));
        wait.until(ExpectedConditions.attributeToBe(draftsPage.firstDraftContent, "title", content));
        ArrayList<String> list = new ArrayList<String>();
        list.add(draftsPage.firstDraftRecipient.getAttribute("title"));
        list.add(draftsPage.firstDraftSubject.getAttribute("title"));
        list.add(draftsPage.firstDraftContent.getAttribute("title"));
        return list;
    }

    protected ArrayList<String> sendADraftMessage(String to, String subject, String content) {
        BaseTest.click(draftsPage.firstDraftMessageArea);
        wait.until(ExpectedConditions.elementToBeClickable(draftsPage.sendButton));
        BaseTest.click(draftsPage.sendButton);
        SentPage sentPage = new SentPage(driver);
        BaseTest.click(mainPage.sentMenuButton);
        wait.until(ExpectedConditions.attributeToBe(sentPage.firstSentMessageRecipient, "title", to));
        wait.until(ExpectedConditions.attributeToBe(sentPage.firstSentMessageSubject, "title", subject));
        wait.until(ExpectedConditions.attributeToBe(sentPage.firstSentMessageContent, "title", content));
        ArrayList<String> list = new ArrayList<String>();
        list.add(sentPage.firstSentMessageRecipient.getAttribute("title"));
        list.add(sentPage.firstSentMessageSubject.getAttribute("title"));
        list.add(sentPage.firstSentMessageContent.getAttribute("title"));
        return list;
    }

    protected ArrayList<String> getLastDraft(String to, String subject, String content) {
        BaseTest.click(mainPage.draftsMenuButton);
        wait.until(ExpectedConditions.elementToBeClickable(draftsPage.createTemplateButton));
        driver.navigate().refresh();
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(draftsPage.firstDraftRecipient, "title", to)));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(draftsPage.firstDraftSubject, "title", subject)));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(draftsPage.firstDraftContent, "title", content)));

        ArrayList<String> list = new ArrayList<String>();
        list.add(draftsPage.firstDraftRecipient.getAttribute("title"));
        list.add(draftsPage.firstDraftSubject.getAttribute("title"));
        list.add(draftsPage.firstDraftContent.getAttribute("title"));
        return list;
    }

    protected boolean logout() {
        BaseTest.click(mainPage.userMenuButton);
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.userMenuButton));
        BaseTest.click(mainPage.userMenuButton);
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.logoutMenuButton));
        BaseTest.click(mainPage.logoutMenuButton);
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.loginButton));
        boolean b = loginPage.loginButton.isDisplayed();
        return b;
    }

    protected void closeBrowser() {
        if (driver != null)
            driver.quit();
    }

}
