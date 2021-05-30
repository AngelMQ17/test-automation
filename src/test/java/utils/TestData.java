package utils;

import model.Article;
import model.Data;
import model.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestData {
    private static final String PROPS_PATH = "testdata.properties";

    private Properties props;

    public Data readTestDataProperties() {
        props = new Properties();

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPS_PATH);
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Data(
                props.getProperty("url", ""),
                getUser(),
                props.getProperty("articleNumber", ""),
                getArticle()
        );
    }

    public User getUser() {
        return new User(
                props.getProperty("user.name",""),
                props.getProperty("user.email",""),
                props.getProperty("user.password","")
        );
    }

    public Article getArticle() {
        return new Article(
                props.getProperty("article.title", ""),
                props.getProperty("article.description", ""),
                props.getProperty("article.body", ""),
                props.getProperty("article.tags", "")
        );
    }
}
