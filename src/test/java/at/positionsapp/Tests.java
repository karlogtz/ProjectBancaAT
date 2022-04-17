package at.positionsapp;

import at.positionsapp.actions.Clients;
import at.positionsapp.actions.Positions;
import at.positionsapp.browser.Browser;
import at.positionsapp.browser.Report;
import com.aventstack.extentreports.Status;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Tests extends Browser {

    Clients client = new Clients();
    Positions position = new Positions();

    /**
     * Temporary variables for testing. These will be replace with
     */
    private final String clientName = ".:karlo";
    private String browser;
    private String URL;
    private String user = "iss@agilethought.com";
    private String pwd = "NewPassword!";

    @BeforeClass
    public static void beforeClass() {
        Report.setupReport();
    }

    @Before
    public void setUp() {
        login(user, pwd);
    }

    public void login(String user, String password) {
        browser = "Chrome";
        URL = "https://positionsapp-uat.azurewebsites.net/#";
        try {
            Report.test = Report.extentRpt.createTest("Login.");
            launchBrowser(browser, URL);
            emailLogin(user);
            pwdLogin(password);
            activeSessionPrompt(true,false);
        } catch (Exception e) {
            Report.log(Status.FAIL, "Login failed with exception: \n"
                    + e.getMessage(), false);
        }
    }

    @Test
    public void unsupportedBrowserTest() {
        try {
            Report.test = Report.extentRpt.createTest("Unsupported browser test.");
            browser = "Opera";
            launchBrowser(browser, URL);
        } catch (Exception e) {
            Report.log(Status.PASS, "Unsupported browser failed with exception: \n"
                    + e.getMessage(), true);
        }
    }

    @Test
    public void badUserLoginTest() {
        try {
            Report.test = Report.extentRpt.createTest("Bad user login test.");
            user = "mail@mail.com";
            login(user, pwd);
            Report.log(Status.PASS, "Login with invalid user failed - Expected.", true);
        } catch (Exception e) {
            Report.log(Status.PASS, "Login with invalid user failed with exception: \n"
                    + e.getMessage(), true);
        }
    }

    @Test
    public void badPwdLoginTest() {
        try {
            Report.test = Report.extentRpt.createTest("Bad password login test.");
            pwd = "passWord!";
            login(user, pwd);
            Report.log(Status.PASS, "Login with invalid password failed - Expected.", true);
        } catch (Exception e) {
            Report.log(Status.PASS, "Login with invalid password failed with exception: \n"
                    + e.getMessage(), true);
        }
    }

    @Test
    public void addClient() {
        try {
            Report.test = Report.extentRpt.createTest("Add client test.");
            client.addClient(clientName);
            Report.log(Status.PASS, "Client added successfully.", true);
        } catch (Exception e) {
            Report.log(Status.FAIL, "Add client failed with exception: \n"
                    + e.getMessage(), false);
        }
    }

    @Test
    public void cancelAddNewClient() {
        try {
            Report.test = Report.extentRpt.createTest("Cancel add client test.");
            client.cancelAddClient(clientName);
            Report.log(Status.PASS, "Client add canceled successfully.", true);
        } catch (Exception e) {
            Report.log(Status.FAIL, "Cancel add client failed with exception: \n"
                    + e.getMessage(), false);
        }
    }

    @Test
    public void deleteClient() {
        try {
            Report.test = Report.extentRpt.createTest("Delete client test.");
            client.deleteClient(clientName, true);
        } catch (Exception e) {
            Report.log(Status.FAIL, "Delete client failed with exception: \n"
                    + e.getMessage(), false);
        }
    }

    @Test
    public void closeAddPosition() {
        try {
            Report.test = Report.extentRpt.createTest("Close the add position frame test.");
            client.selectClient(clientName);
            position.closeAddPosition();
            Report.log(Status.PASS, "Closed the add position frame successfully.", false);
        } catch (Exception e) {
            Report.log(Status.FAIL, "Close add position frame failed with exception: \n"
                    + e.getMessage(), false);
        }
    }

    @Test
    public void addPosition() {
        try {
            Report.test = Report.extentRpt.createTest("Add new position test.");
            client.selectClient(clientName);
            position.addPosMandatoryOnly("QA", "Test", "04/29/2022", "3", "99",
                    "Digital", "FSI", "FSQE2M - Title - Automation Tester", "Test Automation");
            Report.log(Status.PASS, "New position added successfully.", true);
        } catch (Exception e) {
            Report.log(Status.FAIL, "Add new position failed with exception: \n"
                    + e.getMessage(), false);
        }
    }

    @Test
    public void addInternalAutocompleteCandidate() {
        try {
            Report.test = Report.extentRpt.createTest("Add new internal candidate using autocomplete selection test.");
            client.selectClient(clientName);
            position.addCandidate("QA", "Karlo Urbano Gutierrez Olmedo", true, "");
            Report.log(Status.PASS, "New internal candidate added successfully.", true);
        } catch (Exception e) {
            Report.log(Status.FAIL, "Add internal candidate using autocomplete failed with exception: \n"
                    + e.getMessage(), false);
        }
    }

    @Test
    public void addInternalManualCandidate() {
        try {
            Report.test = Report.extentRpt.createTest("Add new internal candidate without using autocomplete selection test.");
            client.selectClient(clientName);
            position.addCandidate("QA", "Karlo Gutierrez", true, "karlo.gutierrez@at.com");
            Report.log(Status.PASS, "New internal candidate added successfully.", true);
        } catch (Exception e) {
            Report.log(Status.FAIL, "Add internal candidate manually failed with exception: \n"
                    + e.getMessage(), false);
        }
    }

    @Test
    public void addExternalCandidate() {
        try {
            Report.test = Report.extentRpt.createTest("Add new external candidate test.");
            client.selectClient(clientName);
            position.addCandidate("QA", "QA Intern - External", false, "internN@at.com");
            Report.log(Status.PASS, "New external candidate added successfully.", true);
        } catch (Exception e) {
            Report.log(Status.FAIL, "Add external candidate failed with exception: \n"
                    + e.getMessage(), false);
        }
    }

    @After
    public void tearDown() {
        try {
            closeBrowser();
            Report.log(Status.INFO, "Browser closed successfully.", false);
            Report.closeReport();
        } catch (Exception e) {
            Report.log(Status.FAIL, "Browser did not close successfully with exception: \n"
                    + e.getMessage(), false);
        }
    }

}
