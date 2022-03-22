package at.positionsapp;

import org.junit.Test;
import at.positionsapp.browser.Browser;

public class Tests extends Browser {

    @Test
    public void launchBrowser() throws Exception {
        setUp("Chrome");
        appLogin();
    }

}
