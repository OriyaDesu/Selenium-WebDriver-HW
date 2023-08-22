package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatedTicket extends AbstractPage{
    @FindBy(xpath = "//th[text()='Submitter E-Mail']//following::td[1]")
    private WebElement emailField;

    @FindBy(xpath = "//th/h3")
    private WebElement summaryTitle;

    @FindBy(xpath = "//td[@id='ticket-description']//p")
    private WebElement issueField;

    public CreatedTicket(){
        PageFactory.initElements(driver, this);
    }

    public String getEmailField(){
        return emailField.getText();
    }

    public String getSummaryTitle(){
        return summaryTitle.getText();
    }

    public String getIssueField(){
        return issueField.getText();
    }
}

