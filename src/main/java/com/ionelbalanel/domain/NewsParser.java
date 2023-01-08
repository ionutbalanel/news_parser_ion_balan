package com.ionelbalanel.domain;

import com.ionelbalanel.data.NewsDataSource;
import com.ionelbalanel.data.models.Article;
import com.ionelbalanel.data.models.NewsData;

import java.util.ArrayList;
import java.util.List;

public class NewsParser {
    private final NewsData newsData;

    public NewsParser(NewsDataSource newsDataSource) {
        newsData = newsDataSource.readData();
    }

    /**
     * Output how many articles are present in the data set
     */
    public Integer getNumberOfArticles() {
        return newsData.totalResults;
    }

    /**
     * Print all articles titles to the console
     */
    public void printAllArticles() {
        for (Article article : newsData.articles) {
            System.out.println(article.title);
        }
    }

    /**
     * Display article title by ID
     *
     * @param id the index of the article for which to print the title
     */
    public void printArticleTitleById(Integer id) {
        Article article = newsData.articles.get(id);
        System.out.println(article.title);
    }

    /**
     * Display article description by ID
     *
     * @param id the index of the article for which to print the description
     */
    public void printArticleDescriptionById(Integer id) {
        Article article = newsData.articles.get(id);
        System.out.println(article.description);
    }

    /**
     * Display article full description by ID
     *
     * @param id the index of the article for which to print the description
     */
    public void printArticleFullDescriptionById(Integer id) {
        Article article = newsData.articles.get(id);
        System.out.println(article.fullDescription);
    }

    /**
     * Search by key-words
     */
    public List<Article> searchByKeywords(String searchQuery) {
        List<Article> results = new ArrayList<>();

        for (Article article : newsData.articles) {
            if (article.keywords != null){
                for (String keyword : article.keywords) {
                    if (keyword.contains(searchQuery)) {
                        results.add(article);
                        break;
                    }
                }
            }
        }
        return results;
    }


    /**
     * Search by title
     */
    public List<Article> searchByTitle(String searchQuery) {
        List<Article> results = new ArrayList<>();
        for (Article article : newsData.articles) {
            if (article.title.contains(searchQuery)) {
                results.add(article);
            }
        }
        return results;
    }


    /**
     * Search by description
     */
    public List<Article> searchByDescription(String searchQuery) {
        List<Article> results = new ArrayList<>();
        for (Article article : newsData.articles) {
            if (article.description != null){
                if (article.description.contains(searchQuery)) {
                    results.add(article);
                }
            }
        }
        return results;
    }


    public Integer getTotalWordsCount() {
        int sum = 0;
        for (Article article : newsData.articles) {
            sum = getWordsCountForArticle(article);
        }
        return sum;
    }

    public Integer getWordsCountForArticle(int articleId) {
        Article article = newsData.articles.get(articleId);
        return getWordsCountForArticle(article);
    }

    private Integer getWordsCountForArticle(Article article) {
        int sum = 0;
        sum += getWordsCount(article.title);
        sum += getWordsCount(article.description);
        sum += getWordsCount(article.fullDescription);
        return sum;
    }

    private Integer getWordsCount(String text) {
        if (text == null || text.isBlank()) {
            return 0;
        } else {
            return text.split(" ").length;
        }
    }
}
