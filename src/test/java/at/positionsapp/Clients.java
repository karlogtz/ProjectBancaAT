package at.positionsapp;

import at.positionsapp.browser.Browser;
import org.openqa.selenium.By;

public class Clients extends Browser {

    // Locators
    By clients = By.tagName("//h2[text()='Clients']");
    By plusSignClient = By.xpath("//span[@class='button-add__plus']");
    By clientNameTxtBox = By.xpath("//span[@class='add-client__name']");
    By addClientBttn = By.xpath("//span[@class='add-client__submit']");

}
