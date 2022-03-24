package at.positionsapp.actions;

import at.positionsapp.browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Positions extends Browser {

    // Locators
    By addNewPosBttn = By.xpath("//div[@class='add-position']");
    By addPosCloseFrame = By.xpath("//span[@class='info-modal__close']");
    By addPosFrame = By.xpath("//div[@class='add_position_modal__position']");
    By addPosIntakeStatusDD = By.xpath("//div[@id='intakeStatus']/select");
    By addPosPriorityDD = By.xpath("//div[@id='priority']/select");
    By addPosDepartmentDD = By.xpath("//div[@id='department']/select");
    By addPosSegmentDD = By.xpath("//div[@id='segment']/select");
    By addPosName = By.xpath("//div[@id='name']/input");
    By addPosProject = By.xpath("//div[@id='project']/input");
    By addPosRequestor = By.xpath("//div[@id='requestor']/input");
    By addPosPositions = By.xpath("//div[@id='numberOfPositions']/input");
    By addPosRequestedOn = By.xpath("//div[@id='requestedDate']//input");
    By addPosStartDate = By.xpath("//div[@id='neededDate']//input");
    By addPosProjectDuration = By.xpath("//div[@id='projectDuration']//input");
    By addPosHireTypeDD = By.xpath("//div[@id='hireType']/select");
    By addPosSow = By.xpath("//div[@id='isSowSigned']//input");
    By addPosBackfill = By.xpath("//div[@id='isBackfill']//input");
    By addPosNearshore = By.xpath("//div[@id='isNearshore']//input");
    By addPosOnshore = By.xpath("//div[@id='isOnshore']//input");
    By addPosRFP = By.xpath("//div[@id='isRFP']//input");
    By addPosBidding = By.xpath("//div[@id='isBiddingWithVendors']//input");
    By addPosClientIntv = By.xpath("//div[@id='hasClientInterview']//input");
    By addPosRemoteDD = By.xpath("//div[@id='workRemote']/select");
    By addPosPrimaryLoc = By.xpath("//div[@id='primaryLocation']//input");
    By addPosBookingID = By.xpath("//div[@id='bookingId']//input");
    By addPosRate = By.xpath("//div[@id='rateToClient']//input");
    By addPosMaxPayRate = By.xpath("//label[@for='Maximum Pay Rate']/following-sibling::input");
    By addPosCitizenship = By.xpath("//div[@id='citizenshipRequirements']//input");
    By addPosOnsiteReqs = By.xpath("//div[@id='clientOnSiteRequirements']/textarea");
    By addPosTravel = By.xpath("//div[@id='hasTravelRequirement']//input");
    By addPosHowLong = By.xpath("//div[@id='travelRequirementHowOften']//input");
    By addPosLOS = By.xpath("//div[@id='lineOfService']//input");
    By addPosPractice = By.xpath("//div[@id='practice']//input");
    By addPosJobCode = By.xpath("//div[@id='jobCode']//input");
    By addPosMainTech = By.xpath("//div[@id='mainTechnology']//input");
    By addPosMgr = By.xpath("//div[@id='manager']//input");
    By addPosReqCompt = By.xpath("//div[@id='requiredCompetencyIds']//input");
    By addPosNiceToHave = By.xpath("//div[@id='niceToHaveCompetencyIds']//input");
    By addPosTechIntervwrs = By.xpath("//div[@id='technicalInterviewers']/textarea");
    By addPosMgtIntervwrs = By.xpath("//div[@id='managerialInterviewers']/textarea");
    By addPosJobDesc = By.xpath("//div[@id='jobDescription']/textarea");
    By addPosReqTechSkills = By.xpath("//div[@id='requiredTechnicalSkills']/textarea");
    By addPosNiceToHaveTechSkills = By.xpath("//div[@id='niceToHaveTechnicalSkills']/textarea");
    By addPosSaveBttn = By.xpath("//button[@type='submit']");
    By addPosCancelBttn = By.xpath("//span[@class='add-client__cancel']");

    public void addPosition() {
        waitForElement(addNewPosBttn);
        find(addNewPosBttn).click();
        waitForElement(addPosFrame);
    }

    public void addPosMandatory(String name, String requestor, String startDate, String bookingID,
                                           String rate, String LOS, String practice, String jobCode, String reqComp) {
        addPosition();
        find(addPosName).sendKeys(name);
        find(addPosRequestor).sendKeys(requestor);
        find(addPosStartDate).sendKeys(startDate);
        find(addPosBookingID).sendKeys(bookingID);
        find(addPosRate).sendKeys(rate);
        find(addPosLOS).sendKeys(LOS);
        find(addPosPractice).sendKeys(practice);
        find(addPosJobCode).sendKeys(jobCode);
        find(addPosReqCompt).sendKeys(reqComp);
    }

    public void elementsTest() throws InterruptedException {
        //find(addPosCloseFrame).click(); // org.openqa.selenium.ElementNotInteractableException: element not interactable
        find(addPosIntakeStatusDD).click();
        find(addPosPriorityDD).click();
        find(addPosDepartmentDD).click();
        find(addPosSegmentDD).click();
        find(addPosName).sendKeys("Karlo");
        find(addPosProject).sendKeys("Automation");
        find(addPosRequestor).sendKeys("Karlo");
        find(addPosPositions); // org.openqa.selenium.ElementNotInteractableException: element not interactable
        enterDates(addPosRequestedOn, "03/01/2022");
        enterDates(addPosStartDate, "04/29/2022");
        find(addPosProjectDuration).sendKeys("Undefined");
        find(addPosHireTypeDD).click();
        find(addPosSow).click();
        find(addPosBackfill).click();
        find(addPosNearshore).click();
        find(addPosOnshore).click();
        find(addPosRFP).click();
        find(addPosBidding).click();
        find(addPosClientIntv).click();
        find(addPosRemoteDD).click();
        find(addPosPrimaryLoc).sendKeys("WFH");
        find(addPosBookingID).sendKeys("5");
        find(addPosRate).sendKeys("999.99");
        find(addPosMaxPayRate).sendKeys("999.99");
        find(addPosCitizenship).sendKeys("No");
        find(addPosOnsiteReqs).sendKeys("Test");
        find(addPosTravel).click();
        find(addPosHowLong).sendKeys("6 months");
        enterAutocompleteList(addPosLOS, "E");
        enterAutocompleteList(addPosPractice, "E");
        enterAutocompleteList(addPosJobCode, "E");
        find(addPosMainTech).sendKeys("QA");
        find(addPosMgr).sendKeys("Karlo");
        enterAutocompleteList(addPosReqCompt, "A");
        enterAutocompleteList(addPosNiceToHave, "A");
        find(addPosTechIntervwrs).sendKeys("Test");
        find(addPosMgtIntervwrs).sendKeys("Test");
        find(addPosJobDesc).sendKeys("Test");
        find(addPosReqTechSkills).sendKeys("Test");
        find(addPosNiceToHaveTechSkills).sendKeys("Test");
        find(addPosSaveBttn).click();
        find(addPosCancelBttn);
    }

    public void enterDates(By element, String date) throws InterruptedException {
        // Need to fix the date selection
        find(element).clear();
        find(element).sendKeys(date);
        find(element).sendKeys(Keys.RETURN);
    }

    public void enterAutocompleteList(By element, String value) throws InterruptedException {
        find(element).sendKeys(value);
        // Need to change this wait for a fluent wait
        Thread.sleep(2000);
        find(element).sendKeys(Keys.RETURN);
    }

}
