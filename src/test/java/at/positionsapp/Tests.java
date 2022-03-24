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
    public void addPosition() throws InterruptedException {
        login();
        client.selectClient("{{{karlo");
        //position.addPosMandatory("Karlo", "Karlo", "04/01/2022", "3", "99",
        //        "E", "E", "E", "A");
        position.elementsTest();
    }

}
