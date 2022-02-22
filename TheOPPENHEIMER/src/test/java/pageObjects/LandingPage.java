package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;

public class LandingPage {
	public WebDriver driver;
	public Actions builder;
	public String st1;
	String TaxReliefFromGUI;
	double AddTaxAmount = 0.00;
	double step2 = 0.00;
	double TaxRelief = 0.00;
	double FinalTaxRelief;
	String natid, dob;
	int salary, tax;
	String gender;

	public LandingPage(WebDriver driver) {
		this.driver = driver;
		builder = new Actions(driver);
	}

	By DispenseNowButton = By.cssSelector("a.btn.btn-danger.btn-block");
	By BrowseButton = By.xpath("//input[@type='file']");
	By RefreshTaxReliefTableButton = By.cssSelector("button.btn.btn-primary");
	By WorkingClassHeroText = By.xpath("//div[@class='jumbotron jumbotron-fluid']/div/p");
	By WorkingClassNatID = By.xpath("//tbody/tr/td[1]");
	By WorkingClasTaxRelief = By.xpath("//tbody/tr/td[2]");

	public String getTitleLandingPage() {
		return driver.getTitle();
	}

	// Start
	public void ClickonDispenseNow() {
		// To click on the DispenseNowButton
		driver.findElement(DispenseNowButton).click();

	}

	public String GetDispenseNowButtonTextDisplay() {
		// To check the DispenseNowButton display
		String DisplayText = driver.findElement(DispenseNowButton).getText();
		return DisplayText;

	}

	public String GetDispenseNowButtonColour() throws InterruptedException {
		// To check the DispenseNowButtonColour is Red
		Thread.sleep(2000);
		// String s =
		// driver.findElement(DispenseNowButton).getCssValue("color");
		String s = driver.findElement(DispenseNowButton).getCssValue("background-color");
		String Displaycolour = Color.fromString(s).asHex();
		// --red: #dc3545 got from html styles
		return Displaycolour;

	}

	public void SelectBrowseButton()

	{
		// Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(BrowseButton)).click().build().perform();

	}

	public void CLickOnRefreshTRTButton() {
		builder.moveToElement(driver.findElement(RefreshTaxReliefTableButton)).click().build().perform();
		// driver.findElement(RefreshTaxReliefTableButton).click();

	}

	public String GetTaxAmountFromText() throws InterruptedException {
		// To get the tax amount from the text displayed in the page

		Thread.sleep(2000);
		String str1 = driver.findElement(WorkingClassHeroText).getText().split("will")[0].trim();
		String Amount = str1.split("£")[1].trim();
		System.out.println("Amount from  GetTaxAmountFromText()" + Amount);
		return Amount;
	}

	public int GetWorkingHeroCount() {

		// To get the working class hero count
		return driver.findElements(WorkingClassNatID).size();

	}

	public void checkNatIDMasking()

	// To check if the ID's are masked after the 4th character
	{
		List<WebElement> NatIds = driver.findElements(WorkingClassNatID);
		NatIds.iterator();
		for (WebElement Id : NatIds) {

			String idExtracted = Id.getText();
			// System.out.println(idExtracted);
			char check = '$';

			if (idExtracted.length() > 4) {
				st1 = idExtracted.substring(4);

				for (int i = 0; i < st1.length(); i++)
					Assert.assertTrue((st1.charAt(i) == check));

			}

		}

	}

	public double CalculateTotalTaxRelieves() {

		// To calculate the total tax amount by adding the taxrelief

		List<WebElement> webTax = driver.findElements(WorkingClasTaxRelief);
		// Iterator<WebElement> iterator = webTax.iterator();
		for (WebElement webElement : webTax) {

			String TaxString = webElement.getText();
			double TaxInt = Double.parseDouble(TaxString);
			AddTaxAmount = AddTaxAmount + TaxInt;
			System.out.println(AddTaxAmount);

		}
		return AddTaxAmount;
	}

	public String GetNatidAndRelief(String natid) {

		// To get the individual taxrelief based on the natid

		System.out.println(natid);
		List<WebElement> NatIds = driver.findElements(By.xpath("//tbody/tr/td[1]"));
		NatIds.iterator();
		for (WebElement Id : NatIds) {

			String idExtracted = Id.getText();
			String natidsubstring = natid.substring(0, 4);
			String substring = idExtracted.substring(0, 4);
			// System.out.println("Substring"+substring);
			if (natidsubstring.contains(substring)) {
				TaxReliefFromGUI = Id.findElement(By.xpath("following-sibling::td[1]")).getText();
			}
		}
		System.out.println("TaxReliefFromGUI from method" + TaxReliefFromGUI);
		return TaxReliefFromGUI;
	}

	public double taxCalculation(String natid, String salary, String tax, String gender, String dob, long age) {
		// Calculate the tax based on the data sent from scenario
		this.natid = natid;
		this.salary = Integer.parseInt(salary);
		this.tax = Integer.parseInt(tax);
		this.gender = gender;
		this.dob = dob;

		// TaxRelief=((salary-tax)*Variable)+Gender Bonus
		int step1 = this.salary - this.tax;

		if (age <= 18) {
			step2 = step1 * 1;
		} else if (age <= 35)

		{
			step2 = step1 * 0.80;
		} else if (age <= 50)

		{
			step2 = step1 * 0.50;
		} else if (age <= 75)

		{
			step2 = step1 * 0.367;
		} else {
			step2 = step1 * 0.05;
		}

		if (gender.equalsIgnoreCase("F")) {
			TaxRelief = step2 + 500.00;
		} else
			TaxRelief = step2;
		if (TaxRelief < 20)
			FinalTaxRelief = 50.00;
		else
			FinalTaxRelief = TaxRelief;

		return FinalTaxRelief;
	}

}
