package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {

	public LandingPage landingPage;
	public CashDispensedPage cashDispensedPage;
	public WebDriver driver;

	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}

	public LandingPage getLandingPage() {
		landingPage = new LandingPage(driver);
		return landingPage;
	}

	public CashDispensedPage getCashDispensedPage() {
		cashDispensedPage = new CashDispensedPage(driver);
		return cashDispensedPage;
	}

}
