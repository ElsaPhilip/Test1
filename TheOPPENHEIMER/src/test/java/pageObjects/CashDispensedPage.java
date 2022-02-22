package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CashDispensedPage {
	public WebDriver driver;

	public CashDispensedPage(WebDriver driver) {
		this.driver = driver;

	}

	By CashDispensed = By.cssSelector("div.display-4.font-weight-bold");

	public String getTitleLandingPage() {
		return driver.getTitle();
	}

	public String GetCashDispensedMessage() {
		String CashDispensedText = driver.findElement(CashDispensed).getText();
		return CashDispensedText;

	}

}
