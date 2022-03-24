package at.positionsapp;

import org.junit.Test;
import at.positionsapp.browser.Browser;

public class Tests extends Browser {

    Clients client = new Clients();

    @Test
    public void login() throws Exception {
        launchBrowser("Chrome");
        emailLogin();
        pwdLogin();
        activeSessionPrompt(true,false);
    }

    @Test
    public void addClient() throws Exception {
        login();
        client.addClient("{{{karlo");
    }

    @Test
    public void deleteClient() throws Exception {
        login();
        client.deleteClient("{{{karlo", true);
    }

}
