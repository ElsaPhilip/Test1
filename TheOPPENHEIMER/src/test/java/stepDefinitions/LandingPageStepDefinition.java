package stepDefinitions;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CashDispensedPage;
import pageObjects.LandingPage;
import utils.TestContextSetup;

public class LandingPageStepDefinition {
	public WebDriver driver;
	TestContextSetup testContextSetup;
	public String getDispenseNowButtonTextDisplay;
	LandingPage landingPage;
	CashDispensedPage cashDispensedPage;
	String getCashDispensedMessage;
	String getDispenseNowButtonColour;
	public int BeforeAddgetWorkingHeroCount;
	double step2 = 0.00;
	double TaxRelief = 0.00;
	double FinalTaxRelief;
	String natId, dob;
	int salary, tax;
	private static final DecimalFormat df = new DecimalFormat("0.00");
	String gender;

	public LandingPageStepDefinition(TestContextSetup testContextSetup) {
		this.testContextSetup = testContextSetup;
		this.landingPage = testContextSetup.pageObjectManager.getLandingPage();
		this.cashDispensedPage = testContextSetup.pageObjectManager.getCashDispensedPage();
	}

	@Given("^User is on The Oppenheimer Project Landing page$")
	public void user_is_on_the_oppenheimer_project_landing_page() throws Throwable {
		// System.out.println(landingPage.getTitleLandingPage());
		// Assert.assertTrue(landingPage.getTitleLandingPage().contains("Technical
		// Challenge for CDS"));
	}

	@When("^User Check the Dispense Now button display and Select$")
	public void user_check_the_dispense_now_button_display_and_select() throws Throwable {
		getDispenseNowButtonTextDisplay = landingPage.GetDispenseNowButtonTextDisplay();
		getDispenseNowButtonColour = landingPage.GetDispenseNowButtonColour();
		//System.out.println(getDispenseNowButtonColour);
		Assert.assertEquals(getDispenseNowButtonColour, "#dc3545");
		Assert.assertEquals(getDispenseNowButtonTextDisplay, "Dispense Now");
		landingPage.ClickonDispenseNow();

	}

	@Then("^Validate the Cash dispensed message$")
	public void validate_the_cash_dispensed_message() throws Throwable {
		getCashDispensedMessage = cashDispensedPage.GetCashDispensedMessage();
		Assert.assertEquals("Cash dispensed", getCashDispensedMessage);

	}

	@When("^User select the Browse button to upload the \"([^\"]*)\" file$")
	public void user_select_the_browse_button_to_upload_the_something_file(String strArg1) throws Throwable {
		landingPage.CLickOnRefreshTRTButton();
		Thread.sleep(3000);
		BeforeAddgetWorkingHeroCount = landingPage.GetWorkingHeroCount();
		//System.out.println("count before adding" + BeforeAddgetWorkingHeroCount);
		landingPage.SelectBrowseButton();
		Thread.sleep(3000);
		testContextSetup.genericUtils.SelectFileFromWindows(strArg1);
		Thread.sleep(3000);

	}

	@Then("^Validate \"([^\"]*)\" records are added$")
	public void validate_something_records_are_added(String strArg1) throws Throwable {
		int number = Integer.parseInt(strArg1);
		landingPage.CLickOnRefreshTRTButton();
		Thread.sleep(2000);
		int total = landingPage.GetWorkingHeroCount();
		//System.out.println("count after adding" + total);
		int t = BeforeAddgetWorkingHeroCount + number;
		//System.out.println(t);
		Assert.assertTrue((total == t));

	}

	@Then("^Verify the natid is masked from the 5th cahracter$")
	public void verify_the_natid_is_masked_from_the_5th_cahracter() throws Throwable {

		landingPage.checkNatIDMasking();
	}

	@When("^User details (.+) (.+) (.+) (.+) (.+)$")
	public void user_details(String natid, String salary, String tax, String gender, String dob) throws Throwable {

		long age = testContextSetup.genericUtils.getage(dob);

		//System.out.println("age is" + age);

		double FinalTaxRelief = landingPage.taxCalculation(natid, salary, tax, gender, dob, age);

		//System.out.println("tax calculated" + FinalTaxRelief);
		Thread.sleep(2000);
		String getNatidAndRelieffromGUI = landingPage.GetNatidAndRelief(natid);
		//System.out.println("finalTax after format df" + df.format(FinalTaxRelief));

		Assert.assertEquals(df.format(FinalTaxRelief), getNatidAndRelieffromGUI);
	}

	@When("^Find the taotal tax relief amount$")
	public void find_the_taotal_tax_relief_amount() throws Throwable {
		Thread.sleep(2000);

		String getTaxAmountFromTextString = landingPage.GetTaxAmountFromText();
		double TaxfromString = Double.parseDouble(getTaxAmountFromTextString);
		String formattedTaxfromText = df.format(TaxfromString);
		//System.out.println("TaxAmount from Text after parse and format" + formattedTaxfromText);

		double calculateTotalTaxRelievesAmount = landingPage.CalculateTotalTaxRelieves();
		// double getTaxAmountFromText = Double.parseDouble(TaxfromString);
		String formatgetTaxAmountFromAdd = df.format(calculateTotalTaxRelievesAmount);
		//System.out.println("TaxAmount after add add and df format" + formatgetTaxAmountFromAdd);

		Assert.assertEquals(formattedTaxfromText, formatgetTaxAmountFromAdd);
	}

}
