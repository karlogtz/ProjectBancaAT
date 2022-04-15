package at.positionsapp.actions;

import at.positionsapp.browser.Browser;
import at.positionsapp.browser.Report;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Clients extends Browser {

    // Variables
    private static By client;

    // Locators
    By plusSignAddClient = By.xpath("//span[@class='button-add__plus']");
    By clientNameTxtBox = By.xpath("//input[@class='add-client__name']");
    By addClientBttn = By.xpath("//button[@class='add-client__submit']");
    By cancelClientBttn = By.xpath("//span[@class='add-client__cancel']");
    By clientsList = By.xpath("//span[@class='sidebar__client-name']");
    By trashIcon = By.xpath("//span[@class='sidebar__client-delete']");
    By confirmDeleteOkBttn = By.xpath("//button[@type='button' and text()='Ok']");
    By confirmDeleteCancelBttn = By.xpath("//span[@role='button' and text()='Cancel']");
    By confirmDeletedText = By.xpath("//div[@class='confirm-close-position-modal__field']");
    By confirmDeletedOkBttn = By.xpath("//button[@type='button' and text()='Ok']");

    public void addClient(String clientName) {
        setClient(clientName);
        scrollToElement(find(plusSignAddClient));
        find(plusSignAddClient).click();
        find(clientNameTxtBox).sendKeys(clientName);
        find(addClientBttn).click();
        waitForElement(client, 5L);
        scrollToElement(find(client));
    }

    public void cancelAddClient(String clientName) {
        setClient(clientName);
        scrollToElement(find(plusSignAddClient));
        find(plusSignAddClient).click();
        find(clientNameTxtBox).sendKeys(clientName);
        find(cancelClientBttn).click();
        waitForElement(plusSignAddClient, 5L);
    }

    public void deleteClient(String clientName, boolean confirmation) {
        setClient(clientName);
        try {
            if (clientExist(clientName)) {
                Report.log(Status.INFO, "Client before deletion.", true);
                scrollToElement(find(client));
                List<WebElement> clients = driver.findElements(clientsList);
                if (clients.size() > 0) {
                    for (WebElement client : clients) {
                        if (client.getText().equalsIgnoreCase(clientName)) {
                            find(trashIcon).click();
                            break;
                        }
                    }
                }
                confirmDeletion(confirmation);
                if (confirmation) {
                    waitForElement(confirmDeletedText, 5L);
                    clearConfirmationPrompt();
                    Report.log(Status.PASS, "Client deleted successfully.", true);
                }
            }
        } catch (Exception e) {
            Report.log(Status.FAIL, "Client " + clientName + " does not exist.", false);
        }
    }

    public boolean clientExist(String clientName) {
        setClient(clientName);
        return find(client).getText().equalsIgnoreCase(clientName);
    }

    public void selectClient(String clientName) {
        setClient(clientName);
        scrollToElement(find(client));
        if (clientExist(clientName)) {
            find(client).click();
        }
    }

    public void confirmDeletion(boolean option) {
        if(option) {
            find(confirmDeleteOkBttn).click();
        } else {
            find(confirmDeleteCancelBttn).click();
        }
    }

    public void clearConfirmationPrompt() {
        find(confirmDeletedOkBttn).click();
    }

    public void setClient(String clientName) {
        client = By.xpath("//span[@class='sidebar__client-name'" +
                " and text()='" + clientName + "']");
    }

    public By getClient() {
        return client;
    }

}
