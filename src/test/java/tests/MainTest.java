package tests;

import model.Data;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SignInPage;
import utils.RestCalls;
import utils.TestData;

public class MainTest extends BaseTest {
	
	SignInPage signInPage;
	
	RestCalls restCalls;

    Data data;
	
	String userEmail;

    @Test
    public void checkArticles() {
        readTestData(); //Loads all the properties inside the 'testdata.properties' file and stores it in the 'data' object
		restCalls = new RestCalls();
        userEmail = restCalls.createNewUser(data); //Create a new user with a random email to login with it
        data.getUser().setEmail(userEmail); //Stores the generated email into the email variable of our User model
		
		getDriver().get(data.getUrl());

        signInPage = new SignInPage(getDriver());
        signInPage.signIn(data.getUser()); //Login with the generated user
        Assert.assertTrue(signInPage.checkUserLoggedIn());
        signInPage.closePopUp();
    }

    public void readTestData() {
        TestData testData = new TestData();
        data = testData.readTestDataProperties();
    }
}
