package pages;

import model.Article;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyArticlesPage extends BasePage {

    @FindBy(xpath = "//nav[@class='navbar navbar-light']//a[contains(@href,'@')]")
    private WebElement profileLink;

    @FindBy(xpath = "//a[@aria-label='Next']")
    private WebElement paginationNextButton;

    By titleLocator = By.xpath(".//h1");
    By descriptionLocator = By.xpath(".//p");
    By tagLocator = By.xpath(".//li");

    String articleFirstId;

    List<Article> articlesChecked; //List where all the correctly checked articles will be stored

    public MyArticlesPage(WebDriver driver) {
        super(driver);
    }

    public void checkArticles(List<Article> articleContent) {
        profileLink.click();

        //Store the 'href' attribute which contains the article id of the first article, to check it later when the next page is displayed
        articleFirstId = getDriver().findElement(By.xpath("//a[@class='preview-link']")).getAttribute("href");

        articlesChecked = new ArrayList<>();

        List<WebElement> paginationButtons = getDriver().findElements(By.xpath("//a[@class='page-link']"));
        if(paginationButtons.size()>0) { //Check if the pagination buttons exists in the page

            //Will keep checking the Articles displayed in each page until the 'Next' button is disabled
            while (!getDriver().findElement(By.xpath("//a[@aria-label='Next']/parent::li")).getAttribute("class").contains("disabled")) {
                checkArticleContent(articleContent);
                paginationNextButton.click();
                waitUntilArticleHasChanged();
            }
        }
        checkArticleContent(articleContent);

        //Assert if all previously created articles have been checked correctly
        Assert.assertTrue(articlesChecked.containsAll(articleContent));
    }

    //Checks, for each of the displayed articles, if the elements of the article are present inside of our generated Article List
    public void checkArticleContent(List<Article> articleContent) {
        List<WebElement> articlesDisplayed = getDriver().findElements(By.className("article-preview"));

        for(WebElement articlePreview : articlesDisplayed) {
            Iterator<Article> it = articleContent.iterator();

            while(it.hasNext() && articlesChecked.size() < articleContent.size()) {
                Article art = it.next();

                if(art.getTitle().equalsIgnoreCase(articlePreview.findElement(titleLocator).getText()) &&
                   art.getDescription().equalsIgnoreCase(articlePreview.findElement(descriptionLocator).getText()) &&
                   art.getTags().equalsIgnoreCase(articlePreview.findElement(tagLocator).getText())) {
                    articlesChecked.add(art);

                }
            }
        }
    }

    //Since the page does not reload when going to the next page, we wait until the first article 'href' attribute is different for the one stored in 'articleFirstId'
    public void waitUntilArticleHasChanged() {
        wait.until((ExpectedCondition<Boolean>) driver -> {
            String articleId = driver.findElement(By.xpath("//a[@class='preview-link']")).getAttribute("href");
            if (!articleId.equals(articleFirstId))
                return true;
            else
                return false;
        });
    }
}