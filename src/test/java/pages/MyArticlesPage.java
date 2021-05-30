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

    List<Article> articlesChecked;

    public MyArticlesPage(WebDriver driver) {
        super(driver);
    }

    public void checkArticles(List<Article> articleContent) {
        profileLink.click();

        articleFirstId = getDriver().findElement(By.xpath("//a[@class='preview-link']")).getAttribute("href");

        articlesChecked = new ArrayList<>();

        List<WebElement> paginationButtons = getDriver().findElements(By.xpath("//a[@class='page-link']"));
        if(paginationButtons.size()>0) {

            while (!getDriver().findElement(By.xpath("//a[@aria-label='Next']/parent::li")).getAttribute("class").contains("disabled")) {
                checkArticleContent(articleContent);
                paginationNextButton.click();
                waitUntilArticleHasChanged();
            }
        }
        checkArticleContent(articleContent);

        Assert.assertTrue(articlesChecked.containsAll(articleContent));
    }

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