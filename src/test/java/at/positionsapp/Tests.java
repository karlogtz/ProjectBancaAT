package at.positionsapp;

import at.positionsapp.actions.Clients;
import at.positionsapp.actions.Positions;
import at.positionsapp.browser.Browser;
import org.junit.Test;

public class Tests {

    Clients client = new Clients();
    Positions position = new Positions();

    @Test
    public void login() {
        Browser browser = new Browser();
        browser.launchBrowser("Chrome");
        browser.emailLogin();
        browser.pwdLogin();
        browser.activeSessionPrompt(true,false);
    }

    @Test
    public void addClient() {
        login();
        client.addClient("{{{karlo");
    }

    @Test
    public void cancelAddNewClient() {
        login();
        client.cancelAddClient("{{karlo");
    }

    @Test
    public void deleteClient() {
        login();
        client.deleteClient("{{{karlo", true);
    }

    @Test
    public void addPosition() {
        login();
        client.selectClient("{{{karlo");
        position.addPosMandatoryOnly("Mandatory", "Test", "04/29/2022", "3", "99",
                "Digital", "FSI", "FSQE2M - Title - Automation Tester", "Test Automation");
        /* To test all elements within the Add position frame
        position.elementsTest();
         */
    }

}
