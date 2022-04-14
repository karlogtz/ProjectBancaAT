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
    public void closeAddPosition () throws InterruptedException {
        login();
        client.selectClient("{{{karlo");
        position.closeAddPosition();
    }

    @Test
    public void addPosition() {
        login();
        client.selectClient("{{{karlo");
        position.addPosMandatoryOnly("QA", "Test", "04/29/2022", "3", "99",
                "Digital", "FSI", "FSQE2M - Title - Automation Tester", "Test Automation");
    }

    @Test
    public void addInternalAutocompleteCandidate () {
        login();
        client.selectClient("{{{karlo");
        position.addCandidate("QA", "Karlo Urbano Gutierrez Olmedo", true, "");
    }

    @Test
    public void addInternalManualCandidate () {
        login();
        client.selectClient("{{{karlo");
        position.addCandidate("QA", "Karlo Gutierrez", true, "karlo.g@at.com");
    }

    @Test
    public void addExternalCandidate () {
        login();
        client.selectClient("{{{karlo");
        position.addCandidate("QA", "QA Intern - External", false, "internN@at.com");
    }

}
