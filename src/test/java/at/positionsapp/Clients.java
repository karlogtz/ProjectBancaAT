package at.positionsapp;

import at.positionsapp.browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Clients extends Browser {

    // Locators
    By plusSignAddClient = By.xpath("//span[@class='button-add__plus']");
    By clientNameTxtBox = By.xpath("//span[@class='add-client__name']");
    By addClientBttn = By.xpath("//span[@class='add-client__submit']");

    public void addNewClient(String clientName) {
        WebElement addClient = driver.findElement(plusSignAddClient);
        scrollToElement(addClient);
        driver.findElement(plusSignAddClient).click();
        waitForElement(clientNameTxtBox);
        driver.findElement(clientNameTxtBox).sendKeys(clientName);
        driver.findElement(addClientBttn);
        waitForElement(By.xpath("//span[text()='"+clientName+"']"));
    }

}
