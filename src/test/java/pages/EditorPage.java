package pages;

import model.Article;
import model.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class EditorPage extends BasePage {

    @FindBy(xpath = "//a[@href='#/editor']")
    private WebElement newArticleLink;

    @FindBy(name = "title")
    private WebElement titleInput;

    @FindBy(name = "description")
    private WebElement descriptionInput;

    @FindBy(name = "body")
    private WebElement bodyTextArea;

    @FindBy(name = "tagList")
    private WebElement tagsInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement publishArticleButton;

    @FindBy(xpath = "//div[contains(text(), 'Created successfully')]")
    private WebElement articleCreatedMessage;

    @FindBy(xpath = "//div[contains(@class,'close-button')]")
    private WebElement closePopupButton;

    public EditorPage(WebDriver driver) {
        super(driver);
    }

    public void addArticle(List<Article> articles, int number) {
        for (int i = 0; i < number; i++) {
            newArticleLink.click();
            typeInto(titleInput, articles.get(i).getTitle());
            typeInto(descriptionInput, articles.get(i).getDescription());
            typeInto(bodyTextArea, articles.get(i).getBody());
            typeInto(tagsInput, articles.get(i).getTags());

            publishArticleButton.click();
            checkCreatedMessage();
        }
    }

    //Create a List of Articles with Randomly Generated Fields with the size indicated in the 'articleNumber' property of testdata.properties
    public List<Article> generateArticles(Data data) {
        List<Article> articles = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(data.getArticleNumber()); i++) {
            articles.add(new Article(
                    data.getArticle().getTitle() + RandomStringUtils.random(5, false, true),
                    data.getArticle().getDescription() + RandomStringUtils.random(5, false, true),
                    data.getArticle().getBody(),
                    data.getArticle().getTags() + RandomStringUtils.random(5, false, true)
            ));
        }
        return articles;
    }

    public void checkCreatedMessage() {
        Assert.assertTrue(articleCreatedMessage.isDisplayed());
        closePopupButton.click();
    }
}
