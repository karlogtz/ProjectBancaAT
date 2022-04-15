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
    private String clientName = ".:karlo";

    @BeforeClass
    public static void beforeClass() {
        Report.setupReport();
    }

    @Before
    public void setUp() {
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

    //Pass
    @Test
    public void addClient() {
        try {
            Report.test = Report.extentRpt.createTest("Add client test.");
            client.addClient(clientName);
            Report.log(Status.PASS, "Client added successfully.", true);
        } catch (Exception e) {
            Report.log(Status.FAIL, "Test failed with exception: \n"
                    + e.getMessage(), false);
        }
    }

    @Test
    public void cancelAddNewClient() {
        try {
            Report.test = Report.extentRpt.createTest("Cancel add client test.");
            client.cancelAddClient(clientName);
            Report.log(Status.PASS, "Client not added successfully.", true);
        } catch (Exception e) {
            Report.log(Status.FAIL, "Test failed with exception: \n"
                    + e.getMessage(), false);
        }
    }

    //Pass
    @Test
    public void deleteClient() {
        try {
            Report.test = Report.extentRpt.createTest("Delete client test.");
            client.deleteClient(clientName, true);
        } catch (Exception e) {
            Report.log(Status.FAIL, "Test failed with exception: \n"
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
            Report.log(Status.FAIL, "Test failed with exception: \n"
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
            Report.log(Status.FAIL, "Test failed with exception: \n"
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
            Report.log(Status.FAIL, "Test failed with exception: \n"
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
            Report.log(Status.FAIL, "Test failed with exception: \n"
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
            Report.log(Status.FAIL, "Test failed with exception: \n"
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
            Report.log(Status.FAIL, "Browser did not close successfully. \n"
                    + e.getMessage(), false);
        }
    }

}
