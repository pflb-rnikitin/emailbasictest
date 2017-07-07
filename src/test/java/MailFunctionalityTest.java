import framework.Constants;
import framework.Steps;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.ArrayList;

public class MailFunctionalityTest extends Steps {
    private String to = "testingexperience@gmail.com";
    private String subject = "yet its not over";
    private String content = "message body";

    @BeforeClass
    public void setUp() {
        openBrowser();
    }

    @Test
    public void loginTest() {
        String loggedUsername = login(Constants.LOGIN, Constants.PASSWORD);
        Assert.assertEquals(loggedUsername, Constants.LOGIN);
    }

    @Test(dependsOnMethods = {"loginTest"}, priority = 1)
    public void newDraftMessageTest() {
        ArrayList<String> testList = createADraftMessage(to, subject, content);
        Assert.assertEquals(testList.get(0), to);
        Assert.assertEquals(testList.get(1), subject);
        Assert.assertEquals(testList.get(2), content);
    }

    @Test(dependsOnMethods = {"loginTest", "newDraftMessageTest"}, priority = 2)
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
    }

    @AfterClass
    public void tearDown() {
        closeBrowser();
    }
}