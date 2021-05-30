package tests;

import model.Data;

import org.testng.annotations.Test;
import utils.TestData;

public class MainTest extends BaseTest {

    Data data;

    @Test
    public void checkArticles() {
        readTestData(); //Loads all the properties inside the 'testdata.properties' file and stores it in the 'data' object
    }

    public void readTestData() {
        TestData testData = new TestData();
        data = testData.readTestDataProperties();
    }
}
