package at.positionsapp;

import at.positionsapp.browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Clients extends Browser {

    // Locators
    By plusSignAddClient = By.xpath("//span[@class='button-add__plus']");
    By clientNameTxtBox = By.xpath("//input[@class='add-client__name']");
    By addClientBttn = By.xpath("//button[@class='add-client__submit']");
    By cancelClientBttn = By.xpath("//button[@class='add-client__cancel']");

    public void addClient(String clientName) {
        By client = By.xpath("//span[@class='sidebar__client-name' and text()='"+clientName+"']");
        scrollToElement(driver.findElement(plusSignAddClient));
        driver.findElement(plusSignAddClient).click();
        driver.findElement(clientNameTxtBox).sendKeys(clientName);
        driver.findElement(addClientBttn).click();
        waitForElement(client);
        scrollToElement(driver.findElement(client));
    }

}
