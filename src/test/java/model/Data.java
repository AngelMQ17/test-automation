package model;

public class Data {
    private String url;

    private User user;

    private String articleNumber;

    private Article article;

    public Data(String url, User user, String articleNumber, Article article) {
        this.url = url;
        this.user = user;
        this.articleNumber = articleNumber;
        this.article = article;
    }

    public String getUrl() { return url; }

    public User getUser() { return user; }

    public String getArticleNumber() { return articleNumber; }

    public Article getArticle() { return article; }
}
