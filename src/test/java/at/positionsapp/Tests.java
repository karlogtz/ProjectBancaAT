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

    @BeforeClass
    public static void beforeClass() {
        Report.setupReport();
    }

    @Before
    public void setUp(){
        login();
    }

    public void login() {
        try {
            Report.test = Report.extentRpt.createTest("Login test.");
            launchBrowser("Chrome", "https://positionsapp-uat.azurewebsites.net/#");
            emailLogin();
            pwdLogin();
            activeSessionPrompt(true,false);
            Report.log(Status.PASS, "Login successful.", false);
        } catch (Exception e) {
            Report.log(Status.FAIL, "Test failed with exception: \n"
                    + e.getMessage(), false);
        }
    }

    @Test
    public void addClient() {
        try {
            Report.test = Report.extentRpt.createTest("Add client test.");
            client.addClient("{{{karlo");
            Report.log(Status.PASS, "Client added successfully.", true);
        } catch (Exception e) {
            Report.log(Status.FAIL, "Test failed with exception: \n"
                    + e.getMessage(), false);
        }

    }

    @Test
    public void cancelAddNewClient() {
        client.cancelAddClient("{{karlo");
    }

    @Test
    public void deleteClient() {
        client.deleteClient("{{{karlo", true);
    }

    @Test
    public void closeAddPosition () throws InterruptedException {
        client.selectClient("{{{karlo");
        position.closeAddPosition();
    }

    @Test
    public void addPosition() {
        client.selectClient("{{{karlo");
        position.addPosMandatoryOnly("QA", "Test", "04/29/2022", "3", "99",
                "Digital", "FSI", "FSQE2M - Title - Automation Tester", "Test Automation");
    }

    @Test
    public void addInternalAutocompleteCandidate () {
        client.selectClient("{{{karlo");
        position.addCandidate("QA", "Karlo Urbano Gutierrez Olmedo", true, "");
    }

    @Test
    public void addInternalManualCandidate () {
        client.selectClient("{{{karlo");
        position.addCandidate("QA", "Karlo Gutierrez", true, "karlo.g@at.com");
    }

    @Test
    public void addExternalCandidate () {
        client.selectClient("{{{karlo");
        position.addCandidate("QA", "QA Intern - External", false, "internN@at.com");
    }

    @After
    public void tearDown() {
        closeBrowser();
        Report.closeReport();
    }

}
